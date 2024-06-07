package com.us.example.service;


import com.us.example.Application;
import com.us.example.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/7 17:42
 */
//@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Test
    private void testfindAll(){

        System.out.println(userService.findAll());
    }


    @Test
    void testGetUserByName1() {
        System.out.println(userService.getUserByName("admin"));
    }
}
