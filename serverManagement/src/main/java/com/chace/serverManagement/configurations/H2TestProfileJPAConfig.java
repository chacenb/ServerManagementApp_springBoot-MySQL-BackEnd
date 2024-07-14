package com.chace.serverManagement.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * <a href = "https://www.baeldung.com/spring-testing-separate-data-source">Configuring Separate Spring DataSource for Tests</a><br>
 * Another way to configure the database to be used for tests
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.chace.serverManagement",})
@EnableTransactionManagement
public class H2TestProfileJPAConfig {

  @Bean
  @Profile("test")
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName("org.h2.Driver");
    dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
    dataSource.setUsername("test");
    dataSource.setPassword("test");

    return dataSource;
  }
}