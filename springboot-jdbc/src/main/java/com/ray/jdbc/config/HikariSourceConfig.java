package com.ray.jdbc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class HikariSourceConfig {


    private Integer maximumPoolSize = 50;
    private Integer minimumIdle = 1;
    private Integer keepaliveTime = 60000;
    private Integer connectionTimeout = 60000;

//    @Bean
    public HikariConfig dataSource() {
        //对应数据库链接
        HikariConfig  config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://192.168.136.100:3306/test?userUnicode=true?characterEncodeing=utf-8");
        config.setUsername("root");
        config.setPassword("123456");
        config.setDriverClassName("com.mysql.jdbc.Driver");

        //设置连接池参数
        config.setMinimumIdle(minimumIdle); //最小连接数
        config.setMaximumPoolSize(maximumPoolSize);  //最大连接
        config.setConnectionTimeout(keepaliveTime); //保持时间
        config.setIdleTimeout(connectionTimeout); //过时时间
        config.setMaxLifetime(1800000);

        return config;
    }

    @Bean(name = "hikariDataSource")
    public HikariDataSource hikariDataSource() {
        HikariDataSource hikariDataSource = null;
        try {
            hikariDataSource = new HikariDataSource(dataSource());
        } catch (Exception ex) {
            log.error("create hikariDataSource failed--->", ex);
        }
        return hikariDataSource;
    }

    public static void main(String[] args) {
        SpringApplication.run(HikariSourceConfig.class, args);
    }
}