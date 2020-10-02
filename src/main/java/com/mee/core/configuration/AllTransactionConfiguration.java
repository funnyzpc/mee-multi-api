package com.mee.core.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author funnyzpc
 * @description 多数据源事务管理
 */
@Configuration
public class AllTransactionConfiguration {

    /** 统一事务管理 **/
    @Bean(name = "allTransactionManager")
    public ChainedTransactionManager transactionManager(@Qualifier("db1TransactionManager") PlatformTransactionManager ds1,
                                                        @Qualifier("db2TransactionManager") PlatformTransactionManager ds2,
                                                        @Qualifier("db3TransactionManager") PlatformTransactionManager ds3) {
       return new ChainedTransactionManager(ds1, ds2,ds3);
    }
}
