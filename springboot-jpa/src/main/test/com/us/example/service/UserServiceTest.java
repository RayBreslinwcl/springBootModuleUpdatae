package com.us.example.service;


import com.us.example.Application;
import com.us.example.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

//    @Test
//    void testfindAll(){
//
//        System.out.println(userService.findAll());
//    }


    @Test
    void testGetUserByName1() {
        System.out.println(userService.getUserByName("admin"));
    }

    @Test
    void findAll() {
        System.out.println(userService.findAll());
    }


    @Test
    void findAllByAddress() {
        System.out.println(userService.findAllByAddress("minhang"));
    }

    @Test
    void findAllByAddressAndName() {
        System.out.println(userService.findAllByAddressAndName("minhang", "admin6"));
    }

    @Test
    void findAllByInAddress() {
        String address="minhang,jingan";
        //strings和arrayList都可以
        List<String> strings = Arrays.asList(address.split(","));
        ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(address.split(",")));

        System.out.println(userService.findAllByInAddress(strings));
    }
}
