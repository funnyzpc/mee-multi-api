package com.mee.core.configuration;


import com.mee.core.annotion.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.mee.xml2", sqlSessionTemplateRef = "db3SqlSessionTemplate")
public class DB3Configuration {

    // 读取yml数据源配置，自动注入到DataSource
    @ConfigurationProperties(prefix = "spring.datasource.db3")
    @Bean(name = "db3DataSource")
    public DataSource db3DataSource() {
        return DataSourceBuilder.create().build();
    }

    // 读取yml Mybatis配置，自动注入到Configuration
    @Bean(name = "db3Config")
    @ConfigurationProperties(prefix = "mybatis.configuration.db3")
    public org.apache.ibatis.session.Configuration db3MybatisConfig() {
        return new org.apache.ibatis.session.Configuration();
    }

    // 多数据源需要在各自的SessionFactory中设置配置Mybatis属性
    @Bean(name = "db3SqlSessionFactory")
    public SqlSessionFactory db3SqlSessionFactory(@Qualifier("db3DataSource") DataSource dataSource,
                                                  @Qualifier("db3Config") org.apache.ibatis.session.Configuration config)throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // 加入sql语句拦截器
        bean.setPlugins(new Interceptor[] {new PageInterceptor()});
        bean.setDataSource(dataSource);
        bean.setConfiguration(config);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:dao3/*/*.xml"));
        return bean.getObject();
    }
    @Bean(name = "db3TransactionManager")
    public PlatformTransactionManager db3TransactionManager(@Qualifier("db3DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "db3SqlSessionTemplate")
    public SqlSessionTemplate db3SqlSessionTemplate(@Qualifier("db3SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
