https://www.cnblogs.com/AvavaAva/p/17654435.html

## 1.@SpringBootApplication

@SpringBootApplication：标识该类为Spring Boot应用程序的入口点，包含了多个注解，如@Configuration、@EnableAutoConfiguration和@ComponentScan，

声明它就可以让springboot自动给程序进行必要的配置（简单的说，开启组件扫描和自己配置的功能），一般用于SpringBoot项目的启动类；

![img](https://img2023.cnblogs.com/blog/2832901/202308/2832901-20230825162356966-25079080.png)

示例

```java
 package com.tzc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

}
```