package com.us.example.serviceImpl;

import com.us.example.Application;
import com.us.example.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/27 18:59
 */
@SpringBootTest(classes = Application.class)
class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    void addUser() {
        User user=new User();
        user.setId(100);
        user.setAddress("insertSongjiang");
        user.setRole(3);
        userService.addUser(user);
    }
}