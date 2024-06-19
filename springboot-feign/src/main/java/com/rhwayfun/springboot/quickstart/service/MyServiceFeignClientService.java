//package com.rhwayfun.springboot.quickstart.service;
//
//import com.rhwayfun.springboot.quickstart.client.ServiceFeignClient;
//import com.rhwayfun.springboot.quickstart.pojo.Dog;
//import com.rhwayfun.springboot.quickstart.vo.Result;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
///**
// * @ClassDescription:
// * @Author:
// * @Created: 2024/6/18 19:21
// */
//@Service
//public class MyServiceFeignClientService {
//
//    @Resource
//    private ServiceFeignClient serviceFeignClient;
//
//    public Result send(Dog dog){
//        return serviceFeignClient.getData(dog);
//    }
//
//}
