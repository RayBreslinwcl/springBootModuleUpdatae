# 一、常见报错

## 1.[java.lang.IllegalArgumentException: jdbcUrl is required with driverClassName.](https://www.cnblogs.com/hellokitty1/p/12174950.html)

百度翻译就是：driverClassName需要jdbcUrl

在配置文件中的

![img](https://img2018.cnblogs.com/blog/664931/202001/664931-20200110103553147-1439547537.png)

 改成：

![img](https://img2018.cnblogs.com/blog/664931/202001/664931-20200110103624337-1163453470.png)

就可以了。

spring.datasource.url 数据库的 JDBC URL。

spring.datasource.jdbc-url 用来创建连接的 JDBC URL。

官方文档的解释是：

因为连接池的实际类型没有被公开，所以在您的自定义数据源的元数据中没有生成密钥，而且在IDE中没有完成(因为DataSource接口没有暴露属性)。另外，如果您碰巧在类路径上有Hikari，那么这个基本设置就不起作用了，因为Hikari没有url属性(但是确实有一个jdbcUrl属性)。





# 二、springboot的jdbc结构

## 1.DataSourceConfig配置

### application.yml

```
spring:
  datasource:
    mysql:
      username: root
      password: 123456
      #mysql5.8���ϣ�����ʱ������������һ��ʱ�����ã�serverTimezone=UTC
      jdbc-url: jdbc:mysql://192.168.136.100:3306/test?userUnicode=true?characterEncodeing=utf-8
      driver-class-name: com.mysql.jdbc.Driver
```

### DataSourceConfig类

```sql
package com.ray.jdbc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/15 15:00
 */
@Configuration
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlJdbcTemplate")
    public JdbcTemplate mysqlJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }


}

```

![image-20240615155917380](Readme.assets/image-20240615155917380.png)



## 2.Service操作sql：可以直接查询

```java
package com.ray.jdbc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/15 15:38
 */
@Service
public class MysqlService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllUsers() {
        String sql = "SELECT * FROM user";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        return users;
    }

}

```



![image-20240615160418027](Readme.assets/image-20240615160418027.png)



## 3.关键点：Autowired和Qualifier指定bean

md_springboot_知识点\01-基本注解-2-Autowired和Qualifier多个bean时，指定对应Bean.md





# 三、springboot的链接数据库

## 1.mysql

## 2.postgresql

已经添加