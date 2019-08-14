package com.example.musjoy.controller;

import com.example.musjoy.pojo.ResultDTO;
import com.example.musjoy.pojo.User;
import com.example.musjoy.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("login")
public class UserController {

    @Resource
    private UserService userService;
      @RequestMapping("register")
      public ResultDTO register(User user){
        return userService.register(user);
      }

    @RequestMapping("login")
    public ResultDTO login(User user) throws Exception {
        return userService.login2(user);

    }

}
