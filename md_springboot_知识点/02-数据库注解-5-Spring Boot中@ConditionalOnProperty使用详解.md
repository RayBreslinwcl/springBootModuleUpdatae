## [Spring Boot中@ConditionalOnProperty使用详解](https://www.cnblogs.com/secbro/p/12011522.html)

在Spring Boot的自动配置中经常看到@ConditionalOnProperty注解的使用，本篇文章带大家来了解一下该注解的功能。

## Spring Boot中的使用

在Spring Boot的源码中，比如涉及到Http编码的自动配置、数据源类型的自动配置等大量的使用到了@ConditionalOnProperty的注解。

HttpEncodingAutoConfiguration类中部分源代码：

```less
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(HttpProperties.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@ConditionalOnClass(CharacterEncodingFilter.class)
@ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)
public class HttpEncodingAutoConfiguration {
    // 省略内部代码
}
```

DataSourceConfiguration类中部分代码：

```less
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(org.apache.tomcat.jdbc.pool.DataSource.class)
@ConditionalOnMissingBean(DataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "org.apache.tomcat.jdbc.pool.DataSource",
		matchIfMissing = true)
static class Tomcat {
  // 省略内部代码
}
```

很显然，以上两个自动配置类中都通过@ConditionalOnProperty来控制自动配置是否生效，下面我们来了解一下它的源码和具体使用。

## @ConditionalOnProperty源码说明

@ConditionalOnProperty注解类源码如下：

```scss
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Documented
@Conditional(OnPropertyCondition.class)
public @interface ConditionalOnProperty {

	// 数组，获取对应property名称的值，与name不可同时使用
	String[] value() default {};

	// 配置属性名称的前缀，比如spring.http.encoding
	String prefix() default "";

	// 数组，配置属性完整名称或部分名称
	// 可与prefix组合使用，组成完整的配置属性名称，与value不可同时使用
	String[] name() default {};

	// 可与name组合使用，比较获取到的属性值与havingValue给定的值是否相同，相同才加载配置
	String havingValue() default "";

	// 缺少该配置属性时是否可以加载。如果为true，没有该配置属性时也会正常加载；反之则不会生效
	boolean matchIfMissing() default false;

}
```

其中在历史版本中还存在一个relaxedNames属性：

```csharp
//是否可以松散匹配
boolean relaxedNames() default true;
```

最新版本中已经不存在该属性了。

通过注解ConditionalOnProperty上的@Conditional(OnPropertyCondition.class)代码，可以看出ConditionalOnProperty属于@Conditional的衍生注解。生效条件由OnPropertyCondition来进行判断。

## 使用方法

关于@ConditionalOnProperty的使用方法，我们在上面的Spring Boot中的使用已经看到。

@ConditionalOnProperty的核心功能是通过属性name以及havingValue来实现的。

首先看matchIfMissing属性，用来指定如果配置文件中未进行对应属性配置时的默认处理：默认情况下matchIfMissing为false，也就是说如果未进行属性配置，则自动配置不生效。如果matchIfMissing为true，则表示如果没有对应的属性配置，则自动配置默认生效。

下面看name属性，name用来从application.properties中读取某个属性值。比如上面Tomcat的自动配置在配置文件为：

```ini
spring.datasource.type=org.apache.tomcat.jdbc.pool.DataSource
```

在matchIfMissing为false时，如果name值为空，则返回false；如果name不为空，则将该值与havingValue指定的值进行比较，如果一样则返回true，否则返回false。返回false也就意味着自动配置不会生效。

但是如果看HttpEncodingAutoConfiguration类上的属性配置发现并没有完全按照上面所说的name和havingValue配合使用。它是通过“prefix+value”作为属性的名称来进行配置：

```ini
spring.http.encoding.enabled=true
```

其中prefix指定了配置的统一前缀“spring.http.encoding”，而value指定了具体的属性名称为“enabled”。这里并没有设置havingValue的值，如果havingValue未指定值，默认情况下在属性配置中设置的值为true则生效（如上配置），false则不生效。

原文链接：《[SPRING BOOT中@CONDITIONALONPROPERTY使用详解](http://www.choupangxia.com/2019/12/09/spring-boot-conditionalonproperty/)》


\---

**程序新视界**：精彩和成长都不容错过

![程序新视界-微信公众号](https://img2018.cnblogs.com/blog/1742867/201910/1742867-20191013111755842-2090947098.png)

标签: [springboot](https://www.cnblogs.com/secbro/tag/springboot/) , [ConditionalOnProperty](https://www.cnblogs.com/secbro/tag/ConditionalOnProperty/)