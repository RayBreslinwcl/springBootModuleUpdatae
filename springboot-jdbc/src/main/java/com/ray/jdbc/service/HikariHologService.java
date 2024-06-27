package com.ray.jdbc.service;

import com.ray.jdbc.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassDescription:
 * @Author:
 * @Created: 2024/6/17 18:22
 */
@Service
//@ConditionalOnProperty(name = "spring.datasource.mysql.active", havingValue = "true")
public class HikariHologService {

    @Value("${spring.datasource.mysql.username}")
    private String mysqlUsername; //root

    @Autowired
    @Qualifier("hologJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllData(){
        // 创建动态的占位符
        System.out.println(mysqlUsername);
        // 构建查询语句
        String sql = String.format("SELECT * FROM %s", "public.segment_detail");
//        String sql = String.format("SELECT * FROM %s where 1=2", "public.segment_detail");//可以的为了测试 空

//        return jdbcTemplate.queryForList(sql, String.class);
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql);
        return results;

    }

    public List<OrderDetail> getAllData_orderdetail(){
        // 创建动态的占位符

        // 构建查询语句
        String sql = String.format("SELECT * FROM %s ", "public.segment_detail");

//        return jdbcTemplate.queryForList(sql, String.class);
        List<OrderDetail> orderDetails =  jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderDetail.class));
        return orderDetails;

    }

}
