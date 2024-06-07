# @Data注解的理解和详细说明以及使用场景


@Data 是一个 Lombok 提供的注解，它可以自动为类生成常用的方法，包括 getter、setter、equals、hashCode 和 toString 等。使用 @Data 注解可以简化代码，使代码更加简洁易读。

# 在类上添加 @Data 注解后，Lombok 会自动生成以下方法：

### 所有字段的 getter 和 setter 方法。

### equals 和 hashCode 方法，用于比较两个对象是否相等。

### toString 方法，用于将对象转换为字符串表示形式。

例如，下面这个 Java 类：

```java
@Data
public class User {
    private String name;
    private int age;
}
```

 使用 @Data 注解后，Lombok 会自动生成以下代码：

```java
public class User {
    private String name;
    private int age;
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return this.age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public boolean equals(Object obj) {
        // ...
    }
    
    @Override
    public int hashCode() {
        // ...
    }
    
    @Override
    public String toString() {
        // ...
    }
}
```




使用 @Data 注解还可以为类生成其他方法，例如带参构造函数、无参构造函数、builder 方法等。可以通过在注解中配置参数来控制生成哪些方法。例如，@Data 注解默认会生成一个无参构造函数，可以通过设置 @Data(staticConstructor = "of") 来生成一个带参的静态构造函数。

@Data 注解适用于大部分的 Java 类，特别是那些只包含属性和简单逻辑的 POJO（Plain Old Java Object）类。它可以使代码更加简洁易读，减少冗余代码，提高开发效率。但需要注意的是，由于 @Data 注解会自动生成大量的代码，如果类中包含大量字段，可能会影响编译速度和性能。此外，@Data 注解也不适合用于一些需要手动实现 getter 和 setter 方法，或需要对 equals 和 hashCode 方法进行特殊定制的场景。
————————————————

                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。

原文链接：https://blog.csdn.net/weixin_47128494/article/details/134706882