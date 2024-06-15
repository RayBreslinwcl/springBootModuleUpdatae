package com.ray.jdbc.service;

import com.sun.xml.internal.stream.events.NamedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Qualifier("mysqlJdbcTemplate")
//    @Resource(name = "mysqlJdbcTemplate") //可以实现
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getAllUsers() {
        String sql = "SELECT * FROM user";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(sql);
        return users;
    }

}
