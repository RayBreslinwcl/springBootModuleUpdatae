//package com.rhwayfun.springboot.quickstart.client;
//
//import com.rhwayfun.springboot.quickstart.pojo.Dog;
//import com.rhwayfun.springboot.quickstart.service.MyServiceFeignClientService;
//import junit.framework.TestCase;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.annotation.Resource;
//
///**
// * @ClassDescription:
// * @Author:
// * @Created: 2024/6/18 18:29
// */
//@SpringBootTest
//public class ServiceFeignClientTest extends TestCase {
//
//    @Resource
//    MyServiceFeignClientService myServiceFeignClientService;
//
//    @Test
//    public void testGetData() {
//        Dog dog=new Dog("Sam9527",20);
//        System.out.println(myServiceFeignClientService.send(dog));
//    }
//}