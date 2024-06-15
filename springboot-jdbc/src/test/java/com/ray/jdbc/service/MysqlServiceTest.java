package com.ray.jdbc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/15 15:07
 */
@SpringBootTest
class MysqlServiceTest {

    @Autowired
    MysqlService mysqlService;

    @Test
    void userList() {
        System.out.println(mysqlService.getAllUsers());
    }
}