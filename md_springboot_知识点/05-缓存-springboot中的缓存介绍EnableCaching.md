## 【总结：这个文章原文非常好！】

# [springboot中的缓存介绍](https://www.cnblogs.com/ElloeStudy/p/17612422.html) 



**23/08/07 18:3360000543310:51 ~ 18:06**

### 前言

Spring框架支持透明地向应用程序添加缓存对缓存进行管理，其管理缓存的核心是**将缓存应用于操作数据的方法（包括增删查改等），从而减少操作数据的执行次数（主要是查询，直接从缓存中读取数据），同时不会对程序本身造成任何干扰**。

SpringBoot继承了Spring框架的缓存管理功能，**通过使用@EnableCaching注解开启基于注解的缓存支持，SpringBoot就可以启动缓存管理的自动化配置**

#### 常用注解介绍

> Spring Boot整合Redis缓存并使用注解
> 在Spring Boot应用程序中，您可以使用Spring Cache库与Redis缓存进行集成，以提高应用程序的性能和响应速度。Spring Cache库提供了一组注解，包括@Cacheable、@CachePut和@CacheEvict，可以方便地将方法的返回值缓存到Redis中，并根据需要进行刷新和清除。
>
> 本篇博客将向您展示如何在Spring Boot项目中整合Redis缓存，并使用注解来管理缓存操作

- @EnableCaching、@Cacheable

  这两个注解都是spring提供的，可以结合不同的缓存技术使用

  - @EnableCaching

    @EnableCaching是开启缓存功能,作用于缓存配置类上或者作用于springboot启动类上

  - @Cacheable

    @Cacheable 注解在方法上，表示该方法的返回结果是可以缓存的。也就是说，该方法的返回结果会放在缓存中，以便于以后使用相同的参数调用该方法时，会返回缓存中的值，而不会实际执行该方法。如果缓存过期，则重新执行。
    注解常用的几个属性：

    - cacheNames/value（二选一） ：用来指定缓存组件的名字
    - key ：缓存数据时使用的 key，可以用它来指定。默认是使用方法参数的值。（这个 key 你可以使用 spEL 表达式来编写）
    - keyGenerator ：key 的生成器。 key 和 keyGenerator 二选一使用
    - cacheManager ：可以用来指定缓存管理器。从哪个缓存管理器里面获取缓存。
    - condition ：可以用来指定符合条件的情况下才缓存
    - unless ：否定缓存。当 unless 指定的条件为 true ，方法的返回值就不会被缓存。当然你也可以获取到结果进行判断。（通过#result 获取方法结果）
    - sync ：是否使用异步模式

##### 步骤一：添加依赖项

首先，在您的Spring Boot项目的`pom.xml`文件中添加必要的依赖项以使用Redis和Spring Cache：



java

```java
<dependencies>
    <!-- 其他依赖项 -->

    <!-- Redis依赖项 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>

    <!-- Spring Cache依赖项 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
</dependencies>
```

##### 步骤二：配置Redis连接

接下来，您需要在`application.properties`或`application.yml`配置文件中添加Redis连接的相关配置信息：



java

```java
接下来，您需要在application.properties或application.yml配置文件中添加Redis连接的相关配置信息：
```

##### 步骤三：启用缓存和Redis支持

在您的Spring Boot应用程序主类上或者配置类redisConfig添加`@EnableCaching`注解，以启用缓存支持：



java

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class YourApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourApplication.class, args);
    }

}
```

##### 步骤四：在方法上使用缓存注解

现在，您可以在需要缓存的方法上使用`@Cacheable`、`@CachePut`和`@CacheEvict`注解：

- Cacheable（查询缓存）

`@Cacheable`注解用于缓存方法的返回值，并在后续调用时从缓存中获取结果，而不再执行实际的方法体。



java

```java
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class YourService {

    @Cacheable("books")
    public Book findBookById(String id) {
        // 从数据库或其他数据源获取书籍信息
        return book;
    }

}
```

在上述示例中，`findBookById`方法的返回值将被缓存到名为"books"的缓存中。当再次调用该方法时，将从缓存中获取结果，而不会执行方法体。

- @CachePut（更新缓存）

`@CachePut`注解用于将方法的返回值存储到缓存中，类似于`@Cacheable`注解，但它每次都会执行方法体。



java

```java
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class YourService {

    @CachePut("books")
    public Book updateBook(Book book) {
        // 更新数据库或其他数据源中的书籍信息
        return book;
    }

}
```

在上述示例中，每次调用`updateBook`方法时，都会执行方法体，并将返回的书籍信息存储到名为"books"的缓存中。

- @CacheEvict（使缓存失效）

`@CacheEvict`注解用于从缓存中移除指定的条目，可以在方法调用之前、之后或同时触发。

示例：



java

```java
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class YourService {

    @CacheEvict("books")
    public void deleteBook(String id) {
        // 从数据库或其他数据源中删除书籍信息
    }

}
```

在上述示例中，每次调用`deleteBook`方法时，都会从名为"books"的缓存中移除相应的条目。

##### 步骤5：使用缓存注解进行方法缓存

在步骤4中，我们已经介绍了@Cacheable、@CachePut和@CacheEvict注解的基本用法。现在，让我们更详细地了解这些注解的使用方法。

- @Cacheable

@Cacheable注解可用于方法级别，用于指定方法的返回值应该被缓存起来。可以使用value属性指定要使用的缓存名称，还可以使用key属性来定义缓存的键。

示例：



java

```java
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class YourService {

    @Cacheable(value = "books", key = "#id")
    public Book findBookById(String id) {
        // 从数据库或其他数据源获取书籍信息
        return book;
    }

}
```

在上述示例中，`findBookById`方法的返回值将被缓存到名为"books"的缓存中，并且使用`id`作为缓存的键。

- @CachePut

`@CachePut`注解可用于方法级别，用于将方法的返回值存储到缓存中。与`@Cacheable`注解不同的是，`@CachePut`注解每次都会执行方法体。

示例：



java

```java
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class YourService {

    @CachePut(value = "books", key = "#book.id")
    public Book updateBook(Book book) {
        // 更新数据库或其他数据源中的书籍信息
        return book;
    }
}
```

在上述示例中，每次调用`updateBook`方法时，都会执行方法体，并将返回的书籍信息存储到名为"books"的缓存中，使用`book.id`作为缓存的键。

- @CacheEvict

`@CacheEvict`注解可用于方法级别，用于从缓存中移除指定的条目。可以使用`value`属性指定要清除的缓存名称，还可以使用`key`属性来定义要清除的缓存键。

示例：



java

```java
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class YourService {

    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(String id) {
        // 从数据库或其他数据源中删除书籍信息
    }

}
```

在上述示例中，每次调用`deleteBook`方法时，将从名为"books"的缓存中移除具有给定`id`的条目。

##### 结论

通过使用`@Cacheable`、`@CachePut`和`@CacheEvict`注解，您可以方便地使用Redis缓存来提高Spring Boot应用程序的性能和响应速度。这些注解使得方法的结果可以被缓存、更新或清除，从而减少对后端资源的访问。

#### 更多

- [SpringBoot缓存管理（一） 默认缓存管理](https://www.cnblogs.com/blayn/p/14890973.html)
- [SpringBoot缓存管理（二） 整合Redis缓存实现](https://www.cnblogs.com/blayn/p/14890975.html)
- [@EnableCaching、@Cacheable的介绍，及Redis在SpringBoot中的使用教程](https://blog.csdn.net/u011746120/article/details/131545275)
- [springboot整合redis使用注解](https://blog.csdn.net/run65536/article/details/130839549)
- [SpringFramework官方文档 - Caching](https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#cache)

[前言](https://www.cnblogs.com/ElloeStudy/p/17612422.html#前言)[  常用注解介绍](https://www.cnblogs.com/ElloeStudy/p/17612422.html#常用注解介绍)[    步骤一：添加依赖项](https://www.cnblogs.com/ElloeStudy/p/17612422.html#步骤一添加依赖项)[    步骤二：配置Redis连接](https://www.cnblogs.com/ElloeStudy/p/17612422.html#步骤二配置redis连接)[    步骤三：启用缓存和Redis支持](https://www.cnblogs.com/ElloeStudy/p/17612422.html#步骤三启用缓存和redis支持)[    步骤四：在方法上使用缓存注解](https://www.cnblogs.com/ElloeStudy/p/17612422.html#步骤四在方法上使用缓存注解)[    步骤5：使用缓存注解进行方法缓存](https://www.cnblogs.com/ElloeStudy/p/17612422.html#步骤5使用缓存注解进行方法缓存)[    结论](https://www.cnblogs.com/ElloeStudy/p/17612422.html#结论)[  更多](https://www.cnblogs.com/ElloeStudy/p/17612422.html#更多)


__EOF__[![img](https://pic.cnblogs.com/avatar/2473716/20210722102841.png)](https://pic.cnblogs.com/avatar/2473716/20210722102841.png)**本文作者：** [ElloeStudy Blog](https://www.cnblogs.com/ElloeStudy)**本文链接：** https://www.cnblogs.com/ElloeStudy/p/17612422.html**关于博主：** 评论和私信会在第一时间回复。或者[直接私信](https://msg.cnblogs.com/msg/send/ElloeStudy)我。**版权声明：** 本博客所有文章除特别声明外，均采用 [BY-NC-SA](https://creativecommons.org/licenses/by-nc-nd/4.0/) 许可协议。转载请注明出处！**声援博主：** 如果您觉得文章对您有帮助，可以点击文章右下角**【[推荐](javascript:void(0);)】**一下。

本文来自博客园，作者：[ElloeStudy](https://www.cnblogs.com/ElloeStudy/)，转载请注明原文链接：https://www.cnblogs.com/ElloeStudy/p/17612422.html