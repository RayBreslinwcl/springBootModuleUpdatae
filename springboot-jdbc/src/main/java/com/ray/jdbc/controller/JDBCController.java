//package com.ray.jdbc.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//public class JDBCController {
//
//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//
//    //查询数据库的所有信息
//    // 没有实体类，数据库中获取：直接使用Map
//    @GetMapping("/userList")
//    public List<Map<String,Object>> userList(){
//        String sql="select * from user";
//        List<Map<String, Object>> list_maps = jdbcTemplate.queryForList(sql);
//        for (Map<String, Object> map : list_maps) {
//            System.out.println(map);
//        }
//        return list_maps;
//    }
//
//    //插入
//    @GetMapping("/addUser")
//    public String addUser(){
//        String sql="insert into user(id,name,address,mobile,email,create_time,role) values(3,'3','5','6','7','2020-12-12 11:11:11',0)";
//        jdbcTemplate.update(sql);
//        return "update-ok";
//    }
//
//    @GetMapping("/updateUser/{id}")
//    public String updateUser(@PathVariable("id") int id){
//        String sql="update user set name=? where id="+id;
//        //封装
//        Object[] objects=new Object[1];
//        objects[0]="小红";
//        jdbcTemplate.update(sql,objects);
//        return "update-ok";
//    }
//
//    @GetMapping("/deleteUser/{id}")
//    public String deleteUser(@PathVariable("id") int id){
//        String sql="delete from user where id=?";
//        jdbcTemplate.update(sql,id);
//        return "delete-ok";
//    }
//
//}
