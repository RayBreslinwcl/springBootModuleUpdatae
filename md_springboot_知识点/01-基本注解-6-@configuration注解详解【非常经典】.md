# [【转载】 @configuration注解详解](https://www.cnblogs.com/cplinux/p/17933628.html)

https://www.cnblogs.com/cplinux/p/17933628.html

为了能深入地掌握Spring Boot的自动配置原理，我们来看一下Spring Boot的一些底层注解，要知道它们是如何完成相关功能的。首先，我们来看一下怎么给容器里面添加组件。

我在这儿准备了两个组件，它们分别是：

用户，即User类

```java
package com.spring.learn.bean;

public class User {
    private String name;
    private Integer age;

    public User() {

    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
```

 

 

宠物，即Pet类

```java
package com.spring.learn.bean;

public class Pet {
    private String name;

    public Pet() {

    }

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

 

如果我们是用以前原生的Spring来把以上这两个组件添加到容器中，那么我们应该是要这么来做的，即首先来创建一个Spring的配置文件，例如beans.xml，记住该文件得在src > main > resources目录下哟，然后使用<bean>标签来向容器中添加组件，如下所示。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean id="user01" class="com.spring.learn.bean.User">
		<property name="name" value="zhangsan"></property>
		<property name="age" value="18"></property>
	</bean>

	<bean id="cat" class="com.spring.learn.bean.Pet">
		<property name="name" value="tomcat"></property>
	</bean>
    

</beans>
```

 

现在，容器中就会有两个组件了，一个是User组件，一个是Pet组件。当然了，这是我们使用以前Spring XML配置文件的方式来向容器中注册组件的。

但是，我们现在使用的是Spring Boot，它已经不推荐我们使用Spring XML配置文件的这种方式了，既然不用Spring XML配置文件，那么它是怎么给容器中添加组件的呢？笨蛋，当然是使用注解呗，而且Spring Boot还有好几种办法向容器中注册组件呢！这里，我们先来看第一种办法吧！

由于我们使用了Spring Boot之后，已经不再写配置文件了，所以要向容器中注册组件，我们得使用Spring Boot底层一个叫@Configuration的注解了，该注解翻译过来就是配置的意思。不妨我们就新建一个类，例如MyConfig，然后我们给该类上标注上@Configuration这么一个注解，如下所示。

```java
package com.spring.learn.config;

import com.spring.learn.bean.Pet;
import com.spring.learn.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 告诉Spring Boot这是一个配置类 == 配置文件
public class MyConfig {
  
}
```

其实，我们这样的做法（即创建一个类，然后在类上标注@Configuration注解）就是类似于创建了一个配置文件，在类上标注@Configuration注解，就相当于告诉Spring Boot这个文件是一个Spring XML配置文件。你现在明白了吧！@Configuration注解就是来告诉Spring Boot它标注的类是一个配置类的。

以前，我们能在Spring XML配置文件里面能做什么，我们现在也能做什么。以前我们是在配置文件里面使用<bean>标签向容器中添加组件的，现在，在配置类里面我们就不能写标签了，而是要写一个方法，比如现在想要向容器中添加一个User组件，我们就得在MyConfig配置类里面编写如下这样一个方法。

```java
package com.spring.learn.config;

import com.spring.learn.bean.Pet;
import com.spring.learn.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 告诉Spring Boot这是一个配置类 == 配置文件
public class MyConfig {
    /**
     * @Bean注解是给容器中添加组件的。添加什么组件呢？以方法名作为组件的id，返回类型就是组件类型，返回的值就是组件在容器中的实例
     * @return
     */
    @Bean
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        return zhangsan;
    }

    
}
```

可以看到，我们在以上user01方法上标注了一个@Bean注解，该注解是用于给容器中注册组件的。那注册什么组件呢？就拿以上user01方法来说，注册的组件是这样子的：

- 方法名（即user01）作为组件的id
- 方法的返回类型（即com.spring.learn.bean.User）作为组件的类型
- 方法返回的值（即User对象）作为组件在容器中的实例

同样的，我们也可以再向容器中注册一个Pet组件，如下所示。

```java
package com.spring.learn.config;

import com.spring.learn.bean.Pet;
import com.spring.learn.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 告诉Spring Boot这是一个配置类 == 配置文件
public class MyConfig {
    /**
     * @Bean注解是给容器中添加组件的。添加什么组件呢？以方法名作为组件的id，返回类型就是组件类型，返回的值就是组件在容器中的实例
     * @return
     */
    @Bean
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        return zhangsan;
    }

    @Bean
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }
}
```

 

这时，我们容器中就会有两个组件了，那怎么来验证容器中有两个组件呢？很简单，来到咱们的主程序类中，因为在主程序类中直接给我们返回了容器，所以我们就可以从容器中来获取组件了。当然了，我们也可以先不获取，而是先将容器中所有组件的名字全部打印出来，看打印出的这些名字里面有没有我们想要的两个组件的名字。

 

```java
package com.spring.learn;

import com.spring.learn.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication {
    public static void main(String[] args) {


        // 1. 返回IoC容器，IoC容器里面就包含了当前应用的所有组件
        AnnotationConfigApplicationContext run = new AnnotationConfigApplicationContext(MyConfig.class) ;
        // 2. 我们可以来查看下IoC容器里面所有的组件，只要能查找到某一个组件，就说明这个组件是能工作的，至于怎么工作，这就是我们后来要阐述的原理了
        String[] names = run.getBeanDefinitionNames(); // 获取所有组件定义的名字
        for (String name : names) {
            System.out.println(name);
        }

        // 3. 从容器中获取组件

    }
}
```

 

我们在IDEA控制台中以user01关键字来搜一下，发现确实能搜到我们注册到容器中的组件，如下图所示，而且还可以看到组件的名字默认就是方法名。

 ![img](https://img2023.cnblogs.com/blog/683560/202312/683560-20231228210604675-400114140.png)

当然，你不想让方法名作为组件名字，而是想给它一个自定义的名字，也是可以的哟！要知道，我们在Spring XML配置文件里面也是可以使用<bean>标签为要注册的组件起一个自定义的名字的。例如，想要为注册的Pet组件起名为tom，我们只需要将tomcatPet方法上的@Bean注解修改为@Bean(“tom”)即可，如下所示。

```java
package com.spring.learn.config;

import com.spring.learn.bean.Pet;
import com.spring.learn.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 告诉Spring Boot这是一个配置类 == 配置文件
public class MyConfig {
    /**
     * @Bean注解是给容器中添加组件的。添加什么组件呢？以方法名作为组件的id，返回类型就是组件类型，返回的值就是组件在容器中的实例
     * @return
     */
    @Bean
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        return zhangsan;
    }

    @Bean("tom")
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }
}
```

 这样，要注册的Pet组件的名字就不再是方法名，而是我们自己定义的名字了。这时，不妨再来重新运行一下主程序类，然后我们再来IDEA控制台中搜索一下，相信你很快就能找到名字为tom的组件了，如下图所示。

![img](https://img2023.cnblogs.com/blog/683560/202312/683560-20231228210836555-2012210285.png)

 

而且，我们给容器中注册的这两个组件，它们默认都是单实例的哟，也就是说，我们无论从容器中获取多少次，获取的都是同一个实例。这儿，不妨我们来验证一下吧！即我从容器中多次获取名字为tom的宠物类型的组件，看看它们是不是同一个实例。

```java
package com.spring.learn;

import com.spring.learn.bean.Pet;
import com.spring.learn.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication {
    public static void main(String[] args) {


        // 1. 返回IoC容器，IoC容器里面就包含了当前应用的所有组件
        AnnotationConfigApplicationContext run = new AnnotationConfigApplicationContext(MyConfig.class) ;// 这是固定写法哟
        // 2. 我们可以来查看下IoC容器里面所有的组件，只要能查找到某一个组件，就说明这个组件是能工作的，至于怎么工作，这就是我们后来要阐述的原理了
        String[] names = run.getBeanDefinitionNames(); // 获取所有组件定义的名字
        for (String name : names) {
            System.out.println(name);
        }

        // 3. 从容器中获取组件
        Pet tom01 = run.getBean("tom", Pet.class);
        Pet tom02 = run.getBean("tom", Pet.class);
        System.out.println("组件是否为单实例：" + (tom01 == tom02));

    }
}
```

 

 再次重新运行主程序类，发现IDEA控制台打印结果如下。

![img](https://img2023.cnblogs.com/blog/683560/202312/683560-20231228211029262-192133827.png)

 

这已然说明了，我们注册的组件默认就是单实例的。也就是说，你就算获取无限次组件，获取到的也是一样的。

至此，我们来总结一下，给一个类上标注@Configuration注解，就是为了告诉Spring Boot该类是一个配置类，然后，我们就可以在该配置类里面使用@Bean注解标注在方法上向容器中注册组件了，而且，注册的组件默认还是单实例的哟！

值得注意的是@Configuration注解标注的类本身也是一个组件哟，也就是说配置类本身也是容器中的一个组件。不妨来验证一下，即我们从容器中能不能获取到配置类。

 

```java
package com.spring.learn;

import com.spring.learn.bean.Pet;
import com.spring.learn.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication {
    public static void main(String[] args) {


        // 1. 返回IoC容器，IoC容器里面就包含了当前应用的所有组件
        AnnotationConfigApplicationContext run = new AnnotationConfigApplicationContext(MyConfig.class) ;// 这是固定写法哟
        // 2. 我们可以来查看下IoC容器里面所有的组件，只要能查找到某一个组件，就说明这个组件是能工作的，至于怎么工作，这就是我们后来要阐述的原理了
        String[] names = run.getBeanDefinitionNames(); // 获取所有组件定义的名字
        for (String name : names) {
            System.out.println(name);
        }

        // 3. 从容器中获取组件
        Pet tom01 = run.getBean("tom", Pet.class);
        Pet tom02 = run.getBean("tom", Pet.class);
        System.out.println("组件是否为单实例：" + (tom01 == tom02));

        //4. 配置类打印
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

    }
}
```

再次重新运行主程序类，发现IDEA控制台打印结果如下。 

 

![img](https://img2023.cnblogs.com/blog/683560/202312/683560-20231228211203055-154009350.png)

 

可以看到能打印出MyConfig配置类这个组件，所以，我们才说配置类本身也是组件。下面，我可要说一个@Configuration注解最大的特性了，大家可一定要睁大眼睛看好哟~

在Spring Boot 2.0这个版本以后，@Configuration注解里面多了一个属性，我们不妨进入到@Configuration注解的源码里面去看一看，如下图所示。

 

```java
/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration {

	
	@AliasFor(annotation = Component.class)
	String value() default "";

	
	boolean proxyBeanMethods() default true;

}
```

 

可以看到，确实多了一个叫proxyBeanMethods的属性，而且其默认值还是true。注意，该属性是Spring Boot基于Spring 5.2而来的哟~

也就是说，现在在我们的MyConfig配置类上面标注的@Configuration注解，默认其里面的proxyBeanMethods属性是为true的，那proxyBeanMethods属性有什么作用呢？它可有大作用了，而且它也是Spring Boot 2.0与Spring Boot 1.0的不同之处。proxyBeanMethods翻译过来的话，应该就是代理bean的方法的意思了，这说的啥啊？要不我现在先来提出这样一个大胆的猜想吧！就是拿到配置类这个组件之后，我们不妨来多次调用其里面的方法，例如user01方法，这时，会不会得到的是不一样的对象呢？

```java
package com.spring.learn;

import com.spring.learn.bean.Pet;
import com.spring.learn.bean.User;
import com.spring.learn.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication {
    public static void main(String[] args) {


        // 1. 返回IoC容器，IoC容器里面就包含了当前应用的所有组件
        AnnotationConfigApplicationContext run = new AnnotationConfigApplicationContext(MyConfig.class) ;// 这是固定写法哟
        // 2. 我们可以来查看下IoC容器里面所有的组件，只要能查找到某一个组件，就说明这个组件是能工作的，至于怎么工作，这就是我们后来要阐述的原理了
        String[] names = run.getBeanDefinitionNames(); // 获取所有组件定义的名字
        for (String name : names) {
            System.out.println(name);
        }

        // 3. 从容器中获取组件
        Pet tom01 = run.getBean("tom", Pet.class);
        Pet tom02 = run.getBean("tom", Pet.class);
        System.out.println("组件是否为单实例：" + (tom01 == tom02));

        //4. 配置类打印
        MyConfig myConfig = run.getBean(MyConfig.class);
        System.out.println(myConfig);

        User user = myConfig.user01();
        User user1 = myConfig.user01();
        System.out.println("(user == user1) = " + (user == user1));

    }
}
```

我们知道配置类里面的user01方法就是给容器中注册组件的，因为该方法上面标注了@Bean注解。而现在我在配置类外面把user01这个方法多调了两遍，那么该方法返回的User对象是从容器中拿的吗？还是说这尼玛就是一个普通方法的调用。不管了，咱们就直接运行主程序类来验证一下，发现IDEA控制台打印的结果如下图所示。

 

![img](https://img2023.cnblogs.com/blog/683560/202312/683560-20231228211651769-2132066202.png)

 

也就是说，配置类里面组件注册的方法，无论你在外面调多少遍，获取到的都是容器中的单实例对象。

至此，我们再来总结一下，我们在外部无论对配置类中的组件注册方法调用多少次，获取到的都是之前注册到容器中的单实例对象。所以，在IDEA控制台这一块，我们才能看到两次调用user01方法获取到的两个User对象是同一个对象。

那么其原因何在呢？原因就在**@Configuration**注解里面的**proxyBeanMethods**属性。我们知道**配置类**本身也是组件，从IDEA控制台打印出的结果中我们还能知道它并不是一个普通对象，而是一个被**Spring CGLIB增强了的代理对象**，所以，可以这样说，我们获取到的配置类组件本身就是一个**代理对象**。当我们在外部通过该代理对象来调用其方法时，那么Spring Boot里面的默认逻辑就是这样子的，即Spring Boot默认会检查容器中是否有该方法已经返回了的组件，若有则不会新创，而是直接拿，简简单单一句话，就是要保持组件单实例；若没有则再来调用该方法创建一个新的，总之，Spring Boot总会检查方法返回的组件是否在容器中存在。当然了，这一切的前提是@Configuration注解里面的proxyBeanMethods属性的值为true（@Configuration(proxyBeanMethods = true)），即在配置类中的方法被代理的情况下（proxyBeanMethods翻译过来就是**代理bean的方法嘛**），我们才能看到上述这种现象。

但是，如果我们把@Configuration注解里面的proxyBeanMethods属性的值置为false，即@Configuration(proxyBeanMethods = false)，那么又是一种什么现象呢？我们不妨重新运行一下主程序类，给大家看看此时的效果。

```java
package com.spring.learn.config;

import com.spring.learn.bean.Pet;
import com.spring.learn.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods=false) // 告诉Spring Boot这是一个配置类 == 配置文件
public class MyConfig {
    /**
     * @Bean注解是给容器中添加组件的。添加什么组件呢？以方法名作为组件的id，返回类型就是组件类型，返回的值就是组件在容器中的实例
     * @return
     */
    @Bean
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        return zhangsan;
    }

    @Bean("tom")
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }
}
```

主函数运行效果如下

![img](https://img2023.cnblogs.com/blog/683560/202312/683560-20231228212210606-361614261.png)

 

可以看到，此时我们从容器中拿到的MyConfig类型的组件就不再是代理对象了，而是一个普普通通的对象，而且，还能看到两次调用user01方法获取到的两个User对象不再是同一个对象了。

以上就是我们要说的proxyBeanMethods属性的作用。而且，该属性还另外引申出了这样一个概念，Spring Boot在底层对@Configuration注解有两种配置，它们分别是：

- Full：即@Configuration(proxyBeanMethods = true)，一般称为全配置
- Lite：即@Configuration(proxyBeanMethods = false)，一般称为轻量级配置

也就是说，以后我们想要给容器中添加组件的时候，不是会编写一个配置类嘛，然后，如果@Configuration注解里面的proxyBeanMethods属性的值为true，那么配置类里面每一个向容器中注册组件的方法在外面都能随便调用，而且该方法都会去容器中找组件，这就是所谓的Full模式；如果@Configuration注解里面的proxyBeanMethods属性的值为false，那么配置类组件本身在容器中就再也不会被保存为代理对象了，这样，当你在外面无限次调用其中的方法时，你的每一次方法调用就都会产生一个新的对象了，这就是所谓的Lite模式。

说了这么多，那么它适用于什么场景呢？我就直说了吧！**组件依赖必须使用默认的Full模式，其他则默认使用Lite模式**。还是举一个例子来说吧，假设用户要养一个宠物，那么在User类中是不是得加上一个Pet类型的属性啊？

```java
package com.spring.learn.bean;

public class User {
    private String name;
    private Integer age;

    private Pet pet;

    public User() {

    }


    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", pet=" + pet +
                '}';
    }
}
```

可以看到，我们又重写了User类的toString方法。此时，相当于我们现在给容器中注册了一个User类型的组件，但这个组件还想要用之前容器中注册的宠物类型的组件。

我们不妨先使用一下默认的Full模式，即将@Configuration注解里面的proxyBeanMethods属性的值置为true，在这种模式下，用户想要养宠物，是不是就得这样啊？

```java
package com.spring.learn.config;

import com.spring.learn.bean.Pet;
import com.spring.learn.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods=true) // 告诉Spring Boot这是一个配置类 == 配置文件
public class MyConfig {
    /**
     * @Bean注解是给容器中添加组件的。添加什么组件呢？以方法名作为组件的id，返回类型就是组件类型，返回的值就是组件在容器中的实例
     * @return
     */
    @Bean
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        // User类型的组件依赖了Pet类型的组件
        zhangsan.setPet(tomcatPet());
        return zhangsan;
    }

    @Bean("tom")
    public Pet tomcatPet() {
        return new Pet("tomcat");
    }
}
```

 

从上可以看到，用户养的宠物就是容器中的宠物，这说明了User类型的组件依赖了Pet类型的组件。而且，在默认的Full模式下，这种组件依赖是成立的，对此，我们不妨来确认一下。

```java
package com.spring.learn;

import com.spring.learn.bean.Pet;
import com.spring.learn.bean.User;
import com.spring.learn.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication {
    public static void main(String[] args) {


        // 1. 返回IoC容器，IoC容器里面就包含了当前应用的所有组件
        AnnotationConfigApplicationContext run = new AnnotationConfigApplicationContext(MyConfig.class) ;// 这是固定写法哟
        // 2. 我们可以来查看下IoC容器里面所有的组件，只要能查找到某一个组件，就说明这个组件是能工作的，至于怎么工作，这就是我们后来要阐述的原理了
        String[] names = run.getBeanDefinitionNames(); // 获取所有组件定义的名字
        for (String name : names) {
            System.out.println(name);
        }

        // 3. 从容器中获取组件
        Pet tom01 = run.getBean("tom", Pet.class);
        Pet tom02 = run.getBean("tom", Pet.class);
        System.out.println("组件是否为单实例：" + (tom01 == tom02));

        //4. 配置类打印
        MyConfig myConfig = run.getBean(MyConfig.class);
        System.out.println(myConfig);

        User user = myConfig.user01();
        User user1 = myConfig.user01();
        System.out.println("(user == user1) = " + (user == user1));

        User user01 = run.getBean("user01", User.class);
        Pet tom = run.getBean("tom", Pet.class);
        System.out.println("用户的宠物：" + (user01.getPet() == tom));

    }
}
```

 

再次重新运行主程序类，发现IDEA控制台打印出了如下结果。

![img](https://img2023.cnblogs.com/blog/683560/202312/683560-20231228213114783-2125199365.png)

 

可以看到，用户养的宠物就是容器中的宠物。你现在该知道，使用默认的Full模式，就能很方便地来解决组件依赖的问题了吧！

但是，如果我们将@Configuration注解里面的proxyBeanMethods属性的值置为false，那么会导致什么现象出现呢？你会发现连编译都通不过去，在调用tomcatPet方法时，发现它下面有一个红色的波浪线，如下图所示。

 

![img](https://img2023.cnblogs.com/blog/683560/202312/683560-20231228212636765-1338464333.png)

 

代码编译都通不过，接下来就不用测试了吧！

以上就是我们Spring Boot 2.0最大的一个更新，即我们可以将配置类编写为轻量级模式和全模式这两种配置模式。

那么，将配置类编写为轻量级模式有什么优点呢？优点就是Spring Boot不会来检查组件注册的方法（例如user01方法）返回的东东在容器中有没有，相当于就跳过了检查，这样，我们整个Spring Boot应用启动并运行起来就非常快了。而如果是将配置类编写为全模式，那么外界对它里面的方法的每一次调用，Spring Boot都会检查容器中是不是有了该方法已经返回了的组件。

虽然Spring Boot给我们带来了Full和Lite这两种配置模式，但是在Spring Boot的最佳实践中，我们推荐的做法是这样子的——如果我们只是向容器中单单注册组件，而且也不存在组件依赖，那么一般使用Lite模式，因为这样的话，我们Spring Boot应用整个的启动速度就会非常快，加载起来也会非常快哟；如果存在组件依赖，那么一般使用Full模式，因为这样能保证某一个组件它所依赖的组件就是容器中的组件。

这就是Spring Boot 2.0里面，@Configuration注解结合@Bean注解给容器中添加组件的使用方法，而且我们也看到了这跟以前是有大大的不同哟，大家未来会在Spring Boot底层见到非常多这样的写法。

 

 

转载自：https://blog.csdn.net/yerenyuan_pku/article/details/116201120