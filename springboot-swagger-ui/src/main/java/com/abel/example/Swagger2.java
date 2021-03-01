package com.abel.example;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by yangyibo on 2018/9/7.
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                //RequestHandlerSelectors配置要扫描接口的方式
                //basePackage：指定要扫描的包
                // any()扫描所有，项目中的所有接口都会被扫描到
                // none()不扫描接口
                // withMethodAnnotation(final Class<? extends Annotation> annotation)通过方法上的注解扫描，如withMethodAnnotation(GetMapping.class)只扫描get请求
                // 通过类上的注解扫描，如.withClassAnnotation(Controller.class)只扫描有controller注解的类中的接口
                .apis(RequestHandlerSelectors.basePackage("com.abel.example.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多请关注https://blog.csdn.net/u012373815")
                .termsOfServiceUrl("https://blog.csdn.net/u012373815")
                .contact("abel")
                .version("1.0")
                .build();
    }
}
