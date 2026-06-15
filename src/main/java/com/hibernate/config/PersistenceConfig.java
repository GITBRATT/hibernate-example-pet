package com.hibernate.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/application.properties")
public class PersistenceConfig {

    @Value("${db.driver}")
    private String databaseDriver;

    @Value("${db.url}")
    private String databaseUrl;

    @Value("${db.username}")
    private String databaseUsername;

    @Value("${db.password}")
    private String databasePassword;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2DdlAuto;

    @Value("${db.connection-pool.initial-size}")
    private int databaseConnectionPoolInitialSize;

    @Value("${db.connection-pool.min-idle}")
    private int databaseConnectionPoolMinIdle;

    @Value("${db.connection-pool.max-idle}")
    private int databaseConnectionPoolMaxIdle;

    @Value("${db.connection-pool.max-total}")
    private int databaseConnectionPoolMaxTotal;


    // Устанавливаем соедение
    //    @Bean
    //    public DataSource dataSource() {
    //        DriverManagerDataSource ds = new DriverManagerDataSource();
    //        ds.setDriverClassName(databaseDriver);
    //        ds.setUrl(databaseUrl);
    //        ds.setUsername(databaseUsername);
    //        ds.setPassword(databasePassword);
    //        return ds;
    //    }

    // Устанавливаем соедение используя connections pool
    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(databaseDriver);
        ds.setUrl(databaseUrl);
        ds.setUsername(databaseUsername);
        ds.setPassword(databasePassword);

        ds.setInitialSize(databaseConnectionPoolInitialSize);    // соединений при создании
        ds.setMinIdle(databaseConnectionPoolMinIdle);   // соединений во время простоя
        ds.setMaxIdle(databaseConnectionPoolMaxIdle);  // соединений во время простоя
        ds.setMaxTotal(databaseConnectionPoolMaxTotal);  // сколько всего можно открыть одновременно
        return ds;
    }


    //Передаем настройки соединеия в EntityManagerFactory
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        var emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.hibernate.entity");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(getHibernateProperties());
        return emf;
    }

    // По
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(emf.getObject());
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.show_sql", hibernateShowSql);
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2DdlAuto);
        return properties;
    }
}