package com.rhwayfun.springboot.quickstart.web;

import com.rhwayfun.springboot.quickstart.pojo.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chubin on 2017/9/10.
 */
@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    Dog dog;


    @PostMapping("/now")
    public String now(){
        return "hello";
    }


    @RequestMapping("/dog")
    public String dog(){
        return dog.toString();
    }


//    @PostMapping("/senddog")
//    public Result createUser(@RequestBody Dog dogReq) {
//        return myServiceFeignClientService.send(dogReq);
//
//    }

}
