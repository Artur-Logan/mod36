package com.arturlogan.mod36.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@EnableJpaRepositories(
        basePackages = "com.arturlogan.mod36.segundobanco.repositories",
        entityManagerFactoryRef = "segundoEntityManager",
        transactionManagerRef = "segundoTransactionManager"
)
@Configuration
public class SegundoBancoConfig {

    @Primary
    @Bean
    @ConfigurationProperties("app")
    public DataSourceProperties segundoDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource segundoDataSource() {
        return segundoDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean segundoEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(segundoDataSource());
        em.setPackagesToScan("com.arturlogan.mod36.segundobanco.entities");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        return em;
    }

    @Primary
    @Bean
    public PlatformTransactionManager segundoTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(segundoEntityManager().getObject());
        return transactionManager;
    }
}
