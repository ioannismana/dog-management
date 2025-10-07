package com.dogs.management.persistence.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DbConfig {
    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.jpa.database-platform}")
    private String dialect;

    @Value("${hibernate.show_sql:false}")
    private boolean showSql;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
}
