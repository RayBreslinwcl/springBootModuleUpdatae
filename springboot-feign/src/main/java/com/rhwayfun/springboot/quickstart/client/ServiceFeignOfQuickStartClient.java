package com.rhwayfun.springboot.quickstart.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "myServiceNameOfOften", url = "http://localhost:8081")
public interface ServiceFeignOfQuickStartClient {

    @PostMapping("/api/now")
    String getNow();

}