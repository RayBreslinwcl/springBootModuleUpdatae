package com.ray.springbootoften.conf;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 参考：https://blog.csdn.net/qq_40437152/article/details/88866035
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
 
    @Bean
    public FilterRegistrationBean<Filter> registFilter() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<Filter>();
        registration.setFilter(new Filter() {

            public void init(FilterConfig filterConfig) throws ServletException {
//                log.info("过滤器init!");
            }

            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                    throws IOException, ServletException {

                allowCrossAccess((HttpServletRequest)request, (HttpServletResponse)response);

                // 判断是否是预请求 OPTIONS  是则放行
                if((boolean) ((HttpServletRequest) request).getMethod().equals("OPTIONS")){
                    System.out.println(((HttpServletRequest) request).getMethod());
                    ((HttpServletResponse) response).setStatus(HttpStatus.OK.value());
                    return;
                }
                chain.doFilter(request, response);
            }

            public void destroy() {
                // TODO Auto-generated method stub
//                log.info("过滤器destroy!");
            }
        });
        return registration;
    }


    protected void allowCrossAccess(HttpServletRequest request,HttpServletResponse response) {

        String allowOrigin = "*";
//		String allowOrigin = request.getHeader("Origin");
        String allowMethods = "GET,PUT,OPTIONS,POST,DELETE";
        String allowHeaders = "authorization,Origin,No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified,Cache-Control, Expires, Content-Type, X-E4M-With";

        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Headers", allowHeaders);
        response.addHeader("Access-Control-Allow-Methods", allowMethods);
        response.addHeader("Access-Control-Allow-Origin", allowOrigin);
        response.addHeader("Access-Control-Max-Age", "1800");//30 min
    }
 
}