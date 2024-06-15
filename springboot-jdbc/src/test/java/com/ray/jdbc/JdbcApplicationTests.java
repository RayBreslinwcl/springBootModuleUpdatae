package com.ray.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class JdbcApplicationTests {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() throws SQLException {
        //查看默认数据源：class com.zaxxer.hikari.HikariDataSource
        System.out.println(dataSource.getClass());

        //获取数据库链接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        connection.close();
    }

    @Test
    public void queryDataTest() {
        Long userCount = jdbcTemplate.queryForObject("select count(*) from user", Long.class);
        System.out.println(userCount);
//        log.info("用户数量为{}", userCount);

        String sql = "SELECT * FROM user";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        System.out.println(users);
    }

}
