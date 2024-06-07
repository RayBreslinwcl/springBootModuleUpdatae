# [Lombok中@Data注释的使用](https://blog.csdn.net/weixin_51451545/article/details/131199216)

快来救救我

已于 2023-09-05 11:44:44 修改

阅读量1.6k
 收藏 1

点赞数
文章标签： java intellij-idea spring boot 后端
版权
目录

# 一、导语

# 二、使用

## 1、在pom文件中引入依赖

## 2、在JavaBean类（实体类）的定义中添加

# 一、导语

@Data注释是由Lombok框架提供的一个注释标签，它可以自动生成JavaBean类（实体类）中的getter和setter方法、toString方法、equals方法和hashCode方法。使用@Data注释可以简化JavaBean的编写，使代码更加简洁，开发者能够更快地编写出可读性更高的JavaBean类。

使用@Data注释非常简单，只需引入依赖，然后在JavaBean类的定义中添加即可。

# 二、使用

## 1、在pom文件中引入依赖

```xml
<!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.16.10</version>
        </dependency>
```

## 2、在JavaBean类（实体类）的定义中添加

例如：

```java
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private Integer age;
}
```

在上面的代码中，使用@Data注释对User类进行注释，它会自动生成User类的getter和setter方法、toString方法、equals方法和hashCode方法。使用@Data注释可以使代码更加简洁明了，减少冗余的getter和setter方法的编写，提高开发效率。

除了@Data注释外，Lombok框架还提供了其他常用的注释标签，例如@Getter、@Setter、@ToString、@EqualsAndHashCode等，它们都可以帮助开发者更快捷地编写出高质量的JavaBean类。
————————————————

                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。

原文链接：https://blog.csdn.net/weixin_51451545/article/details/131199216