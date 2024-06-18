package com.ray.jdbc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class HikariHologConfig {

    @Value("${spring.datasource.postgresql.username}")
    private String username;
    @Value("${spring.datasource.postgresql.password}")
    private String password;
    @Value("${spring.datasource.postgresql.jdbc-url}")
    private String url;
    @Value("${spring.datasource.postgresql.driver-class-name}")
    private String driver;

    //连接池配置
    private Integer maximumPoolSize = 50;
    private Integer minimumIdle = 1;
    private Integer keepaliveTime = 60000;
    private Integer connectionTimeout = 60000;

    @Bean()
    public DataSource hologDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        //设置连接池参数
        config.setMinimumIdle(minimumIdle); //最小连接数
        config.setMaximumPoolSize(maximumPoolSize);  //最大连接
        config.setConnectionTimeout(keepaliveTime); //保持时间
        config.setIdleTimeout(connectionTimeout); //过时时间
        config.setMaxLifetime(1800000);
        config.setPoolName("HikariPool-holog");
        return new HikariDataSource(config);
    }

    @Bean()
    public JdbcTemplate hologJdbcTemplate(@Qualifier("hologDataSource") DataSource hologDataSource) {
        return new JdbcTemplate(hologDataSource);
    }
}
