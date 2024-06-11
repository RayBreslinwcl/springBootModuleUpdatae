# 关于SpringbootTest注解和RunWith注解在测试类的作用

# 一、问题描述

单元测试，使用@Autowired，无法注入。报错：空指针异常。

```java
@SpringBootTest(classes = Application.class)
public class SplitServiceTest extends TestCase {

    @Autowired
    private SplitService splitService;

    @Test
    public void testSaveAndSplitLog() {
//        SplitService splitService=new SplitService();
        String json="{\"message\": \"小明\",\"age\": 18}";
        splitService.saveAndSplitLog(json);
    }
}
```



# 二、解决问题

发现添加@RunWith(SpringRunner.class)注解后即可。

```java
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SplitServiceTest extends TestCase {

    @Autowired
    private SplitService splitService;

    @Test
    public void testSaveAndSplitLog() {
//        SplitService splitService=new SplitService();
        String json="{\"message\": \"小明\",\"age\": 18}";
        splitService.saveAndSplitLog(json);
    }
}
```



# 三、SpringbootTest注解和RunWith注解在测试类的作用

## 1.@SpringbootTest

@SpringbootTest 这个注解相当于启动类的作用，加了这个注解后，当使用加了@Test注解的方法时，会加载Spring上下文，跟SpringbootApplication这个启动类一样，把bean加载进IOC容器。

其中参数classes 需指明启动类.class，如果不指明，需要保证启动类所在的包和加了SpringbootTest注解的类 在同一个包或者是启动类的子包下

## 2.@RunWith（SpringRunner.class）

@RunWith（SpringRunner.class），作用是与Spring环境整合，因为在测试类中我们可以需要用@Autowired自动装配IOC容器中的bean，所以需要与Spring环境进行整合，才能实现自动装配，否则会装配失败，导致bean为null。

## 3.迷幻点：有的测试类不添加@RunWith也能注入成功

有时候会发现，有的测试类不添加@RunWith也能注入成功，这是因为，如果导入@Test注解的包是org.junit.jupiter.api.Test，则不需要添加@RunWith注解，如果导入的是org.junit.Test,则需要添加，这点需要注意（排查了好久wwww）
————————————————

                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。

原文链接：https://blog.csdn.net/weixin_63022020/article/details/136093876

