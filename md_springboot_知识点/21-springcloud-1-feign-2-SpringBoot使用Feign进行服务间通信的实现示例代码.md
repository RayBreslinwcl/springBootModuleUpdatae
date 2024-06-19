



# [SpringBoot使用Feign进行服务间通信的实现示例代码](https://www.jb51.net/program/313516uzp.htm#_lab2_1_1)

 更新时间：2024年01月19日 11:10:03  作者：wx59bcc77095d22  

Feign是一个开源的Java HTTP客户端,可以帮助我们在SpringBoot应用中快速构建和使用HTTP客户端,方便实现服务间的通信,本文就来介绍一下SpringBoot使用Feign进行服务间通信的实现示例代码,感兴趣的可以了解一下



# 目录

- [一、前言](https://www.jb51.net/program/313516uzp.htm#_label0)

- [二、SpringBoot集成](https://www.jb51.net/program/313516uzp.htm#_label1)

- - [1.添加依赖](https://www.jb51.net/program/313516uzp.htm#_lab2_1_0)
  - [2.启用Feign客户端](https://www.jb51.net/program/313516uzp.htm#_lab2_1_1)
  - [3.定义Feign客户端接口](https://www.jb51.net/program/313516uzp.htm#_lab2_1_2)
  - [4.定义目标控制层接口](https://www.jb51.net/program/313516uzp.htm#_lab2_1_3)
  - [5.创建Feign测试控制层](https://www.jb51.net/program/313516uzp.htm#_lab2_1_4)
  - [6.测试](https://www.jb51.net/program/313516uzp.htm#_lab2_1_5)



## 一、前言

在分布式系统中，服务间通信是非常常见的情况。Feign是一个开源的Java HTTP客户端，可以帮助我们在SpringBoot应用中快速构建和使用HTTP客户端，方便实现服务间的通信。与其他HTTP客户端相比，Feign具有简化 HTTP API定义、支持多种HTTP请求方法、支持请求和响应的压缩、支持请求和响应的日志记录、支持多种负载均衡器、支持自定义拦截器和错误处理器等特点。



## 二、SpringBoot集成



### 1.添加依赖

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
    <version>3.1.5</version>
</dependency>
```



### 2.启用Feign客户端

我们需要在启动类上添加@EnableFeignClients注解，启用Feign客户端。

```java
package com.example.nettydemo;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
 
 
@SpringBootApplication
@EnableFeignClients
public class NettyDemoApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(NettyDemoApplication.class, args);
    }
}
```



### 3.定义Feign客户端接口

@FeignClient注解里面的url指定需要请求的URL地址，name指定客户端的名称。

```java
package com.example.nettydemo.feign;
 
import com.example.nettydemo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
 
/**
 * @author qx
 * @date 2023/12/28
 * @des Feign客户端
 */
@FeignClient(url = "http://127.0.0.1:8090/user", name = "user")
public interface UserFeignClient {
 
 
    @GetMapping("/{id}")
    User selectUserById(@PathVariable("id") Long id);
 
}
```



### 4.定义目标控制层接口

```java
package com.example.nettydemo.controller;
 
import com.example.nettydemo.entity.User;
import com.example.nettydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * @author qx
 * @date 2023/12/28
 * @des
 */
@RestController
@RequestMapping("/user")
public class UserController {
 
    @Autowired
    private UserService userService;
 
 
    @GetMapping("/{id}")
    public User selectUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
}
```



### 5.创建Feign测试控制层

```java
package com.example.nettydemo.controller;
 
import com.example.nettydemo.entity.User;
import com.example.nettydemo.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * @author qx
 * @date 2023/12/28
 * @des Feign测试
 */
@RestController
@RequestMapping("/userFeign")
public class UserFeignController {
 
    @Autowired
    private UserFeignClient userFeignClient;
 
    @GetMapping("/{id}")
    public User getUserInfo(@PathVariable("id") Long id) {
        return userFeignClient.selectUserById(id);
    }
}
```



### 6.测试

我们先测试目标请求接口是否正确。

![SpringBoot使用Feign进行服务间通信_通信](https://img.jbzj.com/file_images/article/202401/2024011911051558.png)

然后我们再使用Feign的方式请求接口的方式进行测试。

![SpringBoot使用Feign进行服务间通信_通信_02](https://img.jbzj.com/file_images/article/202401/2024011911051559.png)

这样我们使用Feign方式请求，成功请求目的地址获取到了一样的数据。

到此这篇关于SpringBoot使用Feign进行服务间通信的实现示例代码的文章就介绍到这了,更多相关SpringBoot Feign服务间通信 内容请搜索脚本之家以前的文章或继续浏览下面的相关文章希望大家以后多多支持脚本之家！