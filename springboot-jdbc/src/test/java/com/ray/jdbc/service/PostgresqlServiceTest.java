package com.ray.jdbc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/15 16:50
 */
@SpringBootTest
class PostgresqlServiceTest {

    @Autowired
    PostgresqlService postgresqlService;

    @Test
    void getAllUsers() {
        System.out.println(postgresqlService.getAllUsers());
    }
}