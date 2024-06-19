package com.rhwayfun.springboot.quickstart.client;

import com.rhwayfun.springboot.quickstart.pojo.Dog;
import com.rhwayfun.springboot.quickstart.vo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "myServiceName", url = "http://localhost:8082")
public interface ServiceFeignClient {

//    @PostMapping("/api/create")
//    Result getData(@RequestBody Dog dog);

    @GetMapping("/api/now")
    String getNow();

}