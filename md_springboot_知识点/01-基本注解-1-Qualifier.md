# [Spring之@Qualifier注解](https://blog.csdn.net/qq_38257958/article/details/136733938)



# 场景重现

当我们注入的依赖存在多个候选者,我们得使用一些方法来筛选出唯一候选者,否则就会抛出异常

demo演示

创建接口Car,以及两个实现其接口的bean

```java
public interface Car {
}

@Component
public class RedCar implements Car {
}

@Component
public class WhiteCar implements Car {
}
```

创建类型为Person的bean

```
@Component
public class Person {
 
    @Autowired
    private Car car;
}
```

创建配置类以及Main类

```java
@ComponentScan("com.test.qualifier")
public class AppConfig {
 
}
 
public class Main {
 
    public static void main(String[] args) {
 
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
 
        Person bean = context.getBean(Person.class);
        System.out.println(bean);
 
    }
}
```

运行main方法

![img](https://img-blog.csdnimg.cn/direct/a45e05c8483b4cd297cac837e622925a.jpeg)

# 解决方法

在WhiteCar或者RedCar所在的类上加@Primary注解
将private Car car改成private Car redCar
使用@Qualifier注解
查看@Qualifier注解源码

#  从源码中我们知道这个注解可以作用于属性、方法、参数、类、注解上面



![img](https://img-blog.csdnimg.cn/direct/b444a25041854c3cad64b0bc858230d5.png)

## 作用于属性上

我们以demo演示代码为前提,使用@Qualifier注解

改造Person类

```java
@Component
public class Person {

    @Autowired
    @Qualifier("redCar")
    private Car car;

}
 
```



运行main方法,查看运行结果

![img](https://img-blog.csdnimg.cn/direct/835c396d47474d3c8494498dcaea35ee.png)

## 作用于方法上

创建一个接口Animal,以及两个实现类

```java
public interface Animal {
}

public class Dog implements Animal {
}

public class Cat implements Animal {
}
```

创建配置类

```java
@Configuration
public class AnimalConfig {
 
    @Bean
    @Qualifier("mimi")
    public Cat cat(){
        return new Cat();
    }
 
    @Bean
    @Qualifier("wangcai")
    public Dog dog(){
        return new Dog();
    }
}
```



改造Person类

```java
@Component
public class Person {
 
    @Autowired
    @Qualifier("redCar")
    private Car car;
 
    @Autowired
    @Qualifier("mimi")
    private Animal animal;
 
}
```



运行main方法,查看运行结果

![img](https://img-blog.csdnimg.cn/direct/240b55a9a0a64cbb9a2ab448ed068233.png)

## 作用于类上

改造Person和RedCar

```java
@Component
@Qualifier("car666")
public class RedCar implements Car {
}
 
@Component
public class Person {
 
    @Autowired
    @Qualifier("car666")
    private Car car;
 
    @Autowired
    @Qualifier("mimi")
    private Animal animal;
 
}
```



运行main方法,查看运行结果

![img](https://img-blog.csdnimg.cn/direct/4918394fe297463d9ad8bf4bf459d9b0.png)

## 作用于参数上

改造Person类

```java
@Component
public class Person {
 
    @Autowired
    @Qualifier("car666")
    private Car car;
 
    private Animal animal;
 
    public Person(@Qualifier("wangcai") Animal animal) {
        this.animal = animal;
    }
}
```

运行main方法,查看运行结果

![img](https://img-blog.csdnimg.cn/direct/29ad883ae4344f4fae1fbf40c8de3c1a.png)

## 作用于注解上

创建自定义注解NestQualifier

```java
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Qualifier
public @interface NestQualifier {
    @AliasFor(annotation = Qualifier.class, attribute = "value")
    String value() default "";
 
}
```



case1:改造Person类

```java
@Component
public class Person {
 
    @Autowired
    @NestQualifier("redCar")
    private Car car;
 
    private Animal animal;
 
    public Person(@Qualifier("wangcai") Animal animal) {
        this.animal = animal;
    }
}
```

case2:改造Person类、RedCar类

```java
@Component
public class Person {
 
    @Autowired
    @NestQualifier("car666")
    private Car car;
 
    private Animal animal;
 
    public Person(@Qualifier("wangcai") Animal animal) {
        this.animal = animal;
    }
}
 
@Component
@NestQualifier("car666")
public class RedCar implements Car {
}
```

运行main方法,查看运行结果

![img](https://img-blog.csdnimg.cn/direct/2b4f1ffe2ddc49fa959333f7fc6a64b4.png)

## 小结

作用于方法上、作用于类上等于给bean添加了一个alias，作用于属性上、作用于参数上时等于注入指定标识符的bean,这个标识符既可以是beanName,也可以是alias。作用于注解上比较特殊，如果作用于方法上、作用于类上时用了包装注解，作用于属性上、作用于参数上也必须使用包装注解，否则标识符只能使用beanName，使用alias会报错

## 源码解析

QualifierAnnotationAutowireCandidateResolver#checkQualifier

```java
protected boolean checkQualifier(BeanDefinitionHolder bdHolder, Annotation annotation, TypeConverter typeConverter) {
 
    Class<? extends Annotation> type = annotation.annotationType();
    RootBeanDefinition bd = (RootBeanDefinition) bdHolder.getBeanDefinition();
 
    // 这里以@NestQualifier注解为例
    // 判断是否存在名称为com.test.qualifier.annotations.NestQualifier的AutowireCandidateQualifier
    AutowireCandidateQualifier qualifier = bd.getQualifier(type.getName());
    if (qualifier == null) {
        // 判断是否存在名称为NestQualifier的AutowireCandidateQualifier
        qualifier = bd.getQualifier(ClassUtils.getShortName(type));
    }
    if (qualifier == null) {
        // 判断bd的qualifiedElement,是否存在@NestQualifier注解
        Annotation targetAnnotation = getQualifiedElementAnnotation(bd, type);
 
        // 判断bd的factoryMethod,是否存在@NestQualifier注解
        // PS:即@Bean标注的方法上是否存在@NestQualifier注解
        if (targetAnnotation == null) {
            targetAnnotation = getFactoryMethodAnnotation(bd, type);
        }
        // 判断bd是否存在装饰器bd,然后判断装饰器bd的factoryMethod,是否存在@NestQualifier注解
        if (targetAnnotation == null) {
            RootBeanDefinition dbd = getResolvedDecoratedDefinition(bd);
            if (dbd != null) {
                targetAnnotation = getFactoryMethodAnnotation(dbd, type);
            }
        }
 
        // 判断bd的实际类型是否存在@NestQualifier注解
        if (targetAnnotation == null) {
            // Look for matching annotation on the target class
            if (getBeanFactory() != null) {
                try {
                    Class<?> beanType = getBeanFactory().getType(bdHolder.getBeanName());
                    if (beanType != null) {
                        targetAnnotation = AnnotationUtils.getAnnotation(ClassUtils.getUserClass(beanType), type);
                    }
                } catch (NoSuchBeanDefinitionException ex) {
                    // Not the usual case - simply forget about the type check...
                }
            }
 
            // 判断bd的实际类型是否存在@NestQualifier注解,这里主要针对没有传入beanFactory的情况
            if (targetAnnotation == null && bd.hasBeanClass()) {
                targetAnnotation = AnnotationUtils.getAnnotation(ClassUtils.getUserClass(bd.getBeanClass()), type);
            }
        }
 
        // 如果目标注解等于方法传入的注解,则返回true
        // 即属性注入的value值和类上或者方法上的value值一致,则返回true
        if (targetAnnotation != null && targetAnnotation.equals(annotation)) {
            return true;
        }
    }
 
    Map<String, Object> attributes = AnnotationUtils.getAnnotationAttributes(annotation);
    if (attributes.isEmpty() && qualifier == null) {
        // If no attributes, the qualifier must be present
        return false;
    }
    for (Map.Entry<String, Object> entry : attributes.entrySet()) {
        // 获取注解的属性和属性值
        String attributeName = entry.getKey();
        Object expectedValue = entry.getValue();
        Object actualValue = null;
        // 通过qualifier获取actualValue
        if (qualifier != null) {
            actualValue = qualifier.getAttribute(attributeName);
        }
        // 通过bd获取actualValue
        if (actualValue == null) {
            // Fall back on bean definition attribute
            actualValue = bd.getAttribute(attributeName);
        }
        // 即属性注入的value值和beanName的值相等则,返回true
        if (actualValue == null && attributeName.equals(AutowireCandidateQualifier.VALUE_KEY) &&
                expectedValue instanceof String && bdHolder.matchesName((String) expectedValue)) {
            // Fall back on bean name (or alias) match
            continue;
        }
        // actualValue等于null,qualifier不等于null,获取value的默认值
        if (actualValue == null && qualifier != null) {
            // Fall back on default, but only if the qualifier is present
            actualValue = AnnotationUtils.getDefaultValue(annotation, attributeName);
        }
        if (actualValue != null) {
            actualValue = typeConverter.convertIfNecessary(actualValue, expectedValue.getClass());
        }
        // 判断@NestQualifier注解设置的值是否与默认值相等
        if (!expectedValue.equals(actualValue)) {
            return false;
        }
    }
    return true;
}
```



 上述源码的一些值需要通过bfpp来设置

```java
@Component
public class FirstBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
 
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ScannedGenericBeanDefinition scannedGenericBeanDefinition = (ScannedGenericBeanDefinition) registry.getBeanDefinition("redCar");
 
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClassName(scannedGenericBeanDefinition.getBeanClassName());
 
        // 设置qualifiedElement
        beanDefinition.setQualifiedElement(RedCar.class);
 
        AutowireCandidateQualifier qualifier = new AutowireCandidateQualifier(NestQualifier.class);
        // 通过qualifier设置actualValue
        qualifier.setAttribute("value", "whiteCar");
        beanDefinition.addQualifier(qualifier);
 
        // 通过bd设置actualValue
        beanDefinition.setAttribute("value", "redCar");
 
        registry.registerBeanDefinition("redCar", beanDefinition);
 
    }
 
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
 
    }
}
```




PS : 被@ComponentScan注解扫描的带有@Component注解的类会被解析成ScannedGenericBeanDefinition, 但是Spring在实例化bean的时候会把所有bd封装成RootBeanDefinition处理,如果不提前改造bd的话,RootBeanDefinition属性都是默认值
————————————————

                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。

原文链接：https://blog.csdn.net/qq_38257958/article/details/136733938