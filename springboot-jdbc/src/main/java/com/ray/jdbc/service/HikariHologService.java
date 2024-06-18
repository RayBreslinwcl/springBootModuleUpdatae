package com.ray.jdbc.service;

import com.ray.jdbc.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class HikariHologService {

    @Autowired
    @Qualifier("hologJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllData(){
        // 创建动态的占位符

        // 构建查询语句
        String sql = String.format("SELECT * FROM %s ", "public.segment_detail");

//        return jdbcTemplate.queryForList(sql, String.class);
        return jdbcTemplate.queryForList(sql);

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
