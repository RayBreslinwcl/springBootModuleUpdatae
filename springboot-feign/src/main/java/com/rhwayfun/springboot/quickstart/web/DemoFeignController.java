package com.rhwayfun.springboot.quickstart.web;

import com.rhwayfun.springboot.quickstart.client.ServiceFeignClient;
import com.rhwayfun.springboot.quickstart.pojo.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chubin on 2017/9/10.
 */
@RestController
@RequestMapping("/demoFeign")
public class DemoFeignController {

    @Autowired
    Dog dog;

    @Autowired
    ServiceFeignClient serviceFeignClient;

    @RequestMapping("/now")
    public String now(){
       return serviceFeignClient.getNow();
    }


    @RequestMapping("/dog")
    public String dog(){
        return "dog";
    }

}
