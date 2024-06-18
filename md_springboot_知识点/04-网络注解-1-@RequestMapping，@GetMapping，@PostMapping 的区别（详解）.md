# [Springcloud 学习笔记09-常用注解01 @PostMapping、@GetMapping、@RequestMapping、@RestController、@ResponseBody、@RequestParam、@RequestPart、@PutMapping](https://www.cnblogs.com/luckyplj/p/15181405.html)

https://www.cnblogs.com/luckyplj/p/15181405.html

## 1.org.springframework.web.bind.annotation包下注解

### 1.1 @PostMapping、@GetMapping、@RequestMapping、@RestController、@ResponseBody、@RequestParam、@RequestPart、@PutMapping

#### 1.1.1 **@RequestMapping**

@RequestMapping如果没有指定请求方式，将接收Get、Post、Head、Options等所有的请求方式.

#### 1.1.2 **@GetMapping**

@GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。该注解将HTTP Get 映射到 特定的处理方法上。

![img](https://img2020.cnblogs.com/blog/1504684/202108/1504684-20210824145014229-150867584.png)

get是把参数数据队列加到提交表单的ACTION属性所指的URL中，值和表单内各个字段一一对应，**在URL中可以看到**。

get是从服务器上获取数据。

**若符合下列任一情况，则用GET方法：**

\* 请求是为了查找资源，HTML表单数据仅用来帮助搜索。
\* 请求结果无持续性的副作用。
\* 收集的数据及HTML表单内的输入字段名称的总长不超过1024个字符。

![img](https://img2020.cnblogs.com/blog/1504684/202109/1504684-20210923094010358-980326013.png)

#### 1.1.3 **@PostMapping**

@PostMapping是一个组合注解，是@RequestMapping(method = RequestMethod.POST)的缩写。

get方式的安全性较Post方式要差些，包含机密信息的话，建议用Post数据提交方式；

post是向服务器传送数据。

**若符合下列任一情况，则用POST方法：**

\* 请求的结果有持续性的副作用，例如，数据库内添加新的数据行。
\* 若使用GET方法，则表单上收集的数据可能让URL过长。
\* 要传送的数据不是采用7位的ASCII编码。

#### 1.1.4 **@requestBody(后端方法接收请求体)**

@requestBody注解常用来处理content-type不是默认的application/x-www-form-urlcoded编码的内容，比如说：**application/json**或者是application/xml等。一般情况下来说常用其来处理application/json类型。

 @RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；

![img](https://img2020.cnblogs.com/blog/1504684/202109/1504684-20210917165337948-1832976641.png)

 后端代码接收测试截图：

![img](https://img2020.cnblogs.com/blog/1504684/202109/1504684-20210917165408507-155678863.png)

#### 1.1.5 **@RequestParam(后端方法接收请求参数)**

注解@RequestParam接收的参数是**来自requestHeader**中，即**请求头**。**通常用于GET请求**，比如常见的url：http://localhost:8081/spring-boot-study/novel/findByAuthorAndType?author=唐家三少&type=已完结，其在`Controller` 层的写法如下图所示：

![img](https://ask.qcloudimg.com/http-save/yehe-4190565/sxb2kfq7dw.jpeg?imageView2/2/w/1620)

@RequestParam有三个配置参数：

- `required` 表示是否必须，默认为 `true`，必须。
- `defaultValue` 可设置请求参数的默认值。
- `value` 为接收url的参数名（相当于key值）。

***\*@RequestParam也可用于其它类型的请求，例如：POST、DELETE等请求\**。**

**![img](https://img2020.cnblogs.com/blog/1504684/202109/1504684-20210917163013855-120221985.png)**

 **@RequestParam接收参数，要求postman以表单的格式发送数据(即使用form-data)**

#### 1.1.6 **@RequestPart**

`@RequestPart`这个注解用在`multipart/form-data`表单提交请求的方法上。**多用于文件上传场景**。

前台请求：
`jsonData`为`Person`对象的`json`字符串
`uploadFile`为上传的图片
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210419220546241.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTI4OTQ2OTI=,size_16,color_FFFFFF,t_70)

#### 1.1.7 **@PutMapping**

和PostMapping作用等同，都是用来向服务器提交信息。如果是添加信息，倾向于用@PostMapping，如果是更新信息，倾向于用@PutMapping。两者差别不是很明显。

#### 1.1.8 **@RestController**

@RestController = @Controller + @ResponseBody组成，等号右边的两个注解，简单介绍两句，就明白我们@RestController的意义了：

- @Controller 将当前修饰的类注入SpringBoot IOC容器，使得从该类所在的项目跑起来的过程中，这个类就被实例化。当然也有语义化的作用，即代表该类是充当Controller的作用
- @ResponseBody 它的作用简短截说就是指该类中所有的API接口返回的数据，甭管你对应的方法返回Map或是其他Object，它会**以Json字符串的形式返回给客户端**，本人尝试了一下，如果返回的是String类型，则仍然是String。

#### 总之，用@Controller，返回的是页面；@Controller加上@ResponseBody，返回的是JSON、XML或其他文本。

**用@RestController，意味着这个Controller的所有方法上面都加了@ResponseBody**，不论你在每个方法前加、或不加@ResponseBody，都一样。所以这种Controller不会返回页面。

[![复制代码](https://assets.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
@Controller
@RequestMapping("/test")
public class MyController1 {
    
    @ResponseBody
    @GetMapping(path="/get1", produces = "text/plain;charset=utf-8")
    public String getMethod1(String str) {
        return str;
    }

    @GetMapping(path="/get2", produces = "text/plain;charset=utf-8")
    public String getMethod2(String str) {
        return str;
    }
}
```

[![复制代码](https://assets.cnblogs.com/images/copycode.gif)](javascript:void(0);)

访问 /test/get1，并携带参数 str="index" ，返回 index 字符串。
访问 /test/get2，并携带参数 str="index" ，返回名为 index 页面，如index.jsp。

 

### 1.2 案例分析

案例一：

[![复制代码](https://assets.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
package com.ttbank.flep.file.controller;

import com.ttbank.flep.file.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("用户信息管理")
@RestController
@RequestMapping("/user/*")
public class UserController {

    private final static List<User> userList = new ArrayList<>();

    {
        userList.add(new User(1, "Tom", "A fool cat"));
        userList.add(new User(2, "Jerry", "A clever mouse"));
    }

    @ApiOperation("获取用户列表")
    @GetMapping("list")
    public List userList() {
        return userList;
    }

    @ApiOperation("新增用户")
    @PostMapping("add")
    public boolean add(User user) {
        return userList.add(user);

    }

    @ApiOperation("更新用户")
    @ApiImplicitParam(name = "user", value = "单个用户信息", dataType = "User")
    @PutMapping("update")
    public boolean update(User user) {
        return userList.remove(user) && userList.add(user);
    }

    @ApiOperation("批量删除用户")
    @ApiImplicitParam(name = "users", value = "N个用户信息", dataType = "List<User>")
    @DeleteMapping("delete")
    public boolean delete(@RequestBody List<User> users) {
        return userList.removeAll(users);
    }
}
```

[![复制代码](https://assets.cnblogs.com/images/copycode.gif)](javascript:void(0);)

(1)利用PostMan访问，进行一个add添加操作，http://127.0.0.1:7003/flep/file/user/add

![img](https://img2020.cnblogs.com/blog/1504684/202108/1504684-20210824151959764-2049122974.png)

(2)利用PostMan访问，进行一个list添加操作，http://127.0.0.1:7003/flep/file/user/list

![img](https://img2020.cnblogs.com/blog/1504684/202108/1504684-20210824152531592-139048082.png)

案例二：

**通过@requestBody可以将请求体中的JSON字符串绑定到相应的bean上**，当然，也可以将其分别绑定到对应的字符串上。

[![复制代码](https://assets.cnblogs.com/images/copycode.gif)](javascript:void(0);)

```
　　$.ajax({
　　　　　　　　url:"/login",
　　　　　　　　type:"POST",
　　　　　　　　data:'{"userName":"admin","pwd","admin123"}',
　　　　　　　　content-type:"application/json charset=utf-8",
　　　　　　　　success:function(data){
　　　　　　　　　　alert("request success ! ");
　　　　　　　　}
　　　　});

　　　　@requestMapping("/login")
　　　　public void login(@requestBody String userName,@requestBody String pwd){
　　　　　　System.out.println(userName+" ："+pwd);
　　　　}
```

[![复制代码](https://assets.cnblogs.com/images/copycode.gif)](javascript:void(0);)

这种情况是将JSON字符串中的两个变量的值分别赋予了两个字符串，但是呢假如我有一个User类，拥有如下字段：
　　　　　　String userName;
　　　　　　String pwd;
那么上述参数可以改为以下形式：**@requestBody User user** 这种形式会将JSON字符串中的值赋予user中对应的属性上
需要注意的是，JSON字符串中的key必须对应user中的属性名，否则是请求不过去的。

 

参考文献：https://blog.csdn.net/justry_deng/article/details/80972817(推荐阅读)

https://cloud.tencent.com/developer/article/1414464

https://blog.csdn.net/qq_47443027/article/details/114696716(推荐阅读)