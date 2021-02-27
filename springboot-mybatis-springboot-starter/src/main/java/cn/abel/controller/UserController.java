package cn.abel.controller;

import cn.abel.bean.User;
import cn.abel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/queryUserById")
    public User queryUserList(){
        User user= userService.getById(1);
        System.out.println(user);
        return user;
    }
}
