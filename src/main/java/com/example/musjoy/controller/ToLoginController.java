package com.example.musjoy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("index")
public class ToLoginController {

    @RequestMapping("login")
    public String toLogin(){
        return "login";
    }
}
