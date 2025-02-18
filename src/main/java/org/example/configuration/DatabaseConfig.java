package org.example.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource(){

        var hikariConfig = new HikariConfig();
        hikariConfig.setPoolName("postgresPool");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("localhost:5432/testDb");
        hikariConfig.setUsername("user");
        hikariConfig.setPassword("password");

        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setIdleTimeout(30000);
        hikariConfig.setMaxLifetime(60000);
        hikariConfig.setConnectionTimeout(3000);

        return new HikariDataSource(hikariConfig);
    }
}
