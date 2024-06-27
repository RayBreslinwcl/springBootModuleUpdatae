package com.us.example.service;


import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.us.example.Application;
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

    @Test
    void testtemp(){
        String jsonString="{\n" +
                "  \"store\": {\n" +
                "    \"book\": [\n" +
                "      {\n" +
                "        \"author\": \"Nigel Rees\",\n" +
                "        \"title\": \"Sayings of the Century\",\n" +
                "        \"price\": 8.95\n" +
                "      },\n" +
                "      {\n" +
                "        \"author\": \"Evelyn Waugh\",\n" +
                "        \"title\": \"Sword of Honour\",\n" +
                "        \"price\": 12.99,\n" +
                "        \"category\": \"fiction\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"author\": \"Herman Melville\",\n" +
                "        \"title\": \"Moby Dick\",\n" +
                "        \"isbn\": \"0-553-21311-3\",\n" +
                "        \"price\": 8.99,\n" +
                "        \"category\": \"fiction\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"author\": \"J. R. R. Tolkien\",\n" +
                "        \"title\": \"The Lord of the Rings\",\n" +
                "        \"isbn\": \"0-395-19395-8\",\n" +
                "        \"price\": 22.99,\n" +
                "        \"category\": \"fiction\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"bicycle\": {\n" +
                "      \"color\": \"red\",\n" +
                "      \"price\": 19.95\n" +
                "    }\n" +
                "  }\n" +
                "}";
        List<String> authors = JsonPath.read(jsonString, "$.store.book[*].author");
        List<Double> prices = JsonPath.read(jsonString, "$.store.book[*].price");


        for (int i = 0; i < authors.size(); i++) {
            System.out.println(authors.get(i) + ": " + prices.get(i));
        }

        String json = "{\"name\": \"Alice\", \"age\": 30}";
        DocumentContext parse = JsonPath.parse(json);
        JsonPath parseJsonPath = (JsonPath) JsonPath.parse(json);
        parse.read("$.name");

    }

    @Test
    void findAllByAddressInSet() {
//        String address="songjiang";
        String address="test.minhang";

        System.out.println(userService.findAllByAddressInSet(address));
    }
}
