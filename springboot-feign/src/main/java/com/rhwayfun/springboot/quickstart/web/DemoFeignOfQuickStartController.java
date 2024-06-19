package com.rhwayfun.springboot.quickstart.web;

import com.rhwayfun.springboot.quickstart.client.ServiceFeignOfQuickStartClient;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/19 10:25
 */
@RestController
@RequestMapping("/demoFeignOfQuickStart")
public class DemoFeignOfQuickStartController {
    @Autowired
    ServiceFeignOfQuickStartClient serviceFeignOfQuickStartClient;

    @PostMapping("/now")
    public String now(){
        return serviceFeignOfQuickStartClient.getNow();
    }
}
