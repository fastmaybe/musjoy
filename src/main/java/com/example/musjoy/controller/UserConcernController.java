package com.example.musjoy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("concern")
public class UserConcernController {

    @RequestMapping("as")
    public String getConcern(){
        return "as";
    }
}
