package com.ray.jdbc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/15 20:27
 */
@SpringBootTest
class HikariServiceTest {

    @Autowired
    HikariService hikariService;

    @Test
    void getAllUsers() throws SQLException {
        System.out.println(hikariService.getAllUsers());
    }
}