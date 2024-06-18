package com.ray.jdbc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/17 18:45
 */
@SpringBootTest
class HikariHologServiceTest {

    @Autowired
    HikariHologService hikariHologService;


    @Test
    void getAllData() {
        System.out.println(hikariHologService.getAllData());
    }

    @Test
    void getAllData_orderdetail() {
        System.out.println(hikariHologService.getAllData_orderdetail().toString());
    }
}