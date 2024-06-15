package com.ray.jdbc.entity;

import lombok.Data;

import java.util.Date;


@Data
public class User {
    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    //    @Id
//    @GeneratedValue
    private Integer id;

//    @Column(name = "name")
    private String name;

//    @Column(name = "address")
    private String address;

//    @Column(name = "mobile")
    private String mobile;

//    @Column(name = "email")
    private String email;

//    @Column(name = "create_time")
    private Date createTime;

//    @Column(name = "role")
    private Integer role;

}