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
@MapperScan(basePackages = "com.mee.xml2", sqlSessionTemplateRef = "db2SqlSessionTemplate")
public class DB2Configuration {

    // 读取yml数据源配置，自动注入到DataSource
    @ConfigurationProperties(prefix = "spring.datasource.db2")
    @Bean(name = "db2DataSource")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().build();
    }

    // 读取yml Mybatis配置，自动注入到Configuration
    @Bean(name = "db2Config")
    @ConfigurationProperties(prefix = "mybatis.configuration.db2")
    public org.apache.ibatis.session.Configuration db2MybatisConfig() {
        return new org.apache.ibatis.session.Configuration();
    }

    // 多数据源需要在各自的SessionFactory中设置配置Mybatis属性
    @Bean(name = "db2SqlSessionFactory")
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource dataSource,
                                                  @Qualifier("db2Config") org.apache.ibatis.session.Configuration config)throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        // 加入sql语句拦截器
        bean.setPlugins(new Interceptor[] {new PageInterceptor()});
        bean.setDataSource(dataSource);
        bean.setConfiguration(config);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:dao2/*/*.xml"));
        return bean.getObject();
    }
    @Bean(name = "db2TransactionManager")
    public PlatformTransactionManager db2TransactionManager(@Qualifier("db2DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean(name = "db2SqlSessionTemplate")
    public SqlSessionTemplate db2SqlSessionTemplate(@Qualifier("db2SqlSessionFactory") SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
