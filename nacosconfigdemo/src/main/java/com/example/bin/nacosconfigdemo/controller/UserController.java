package com.example.bin.nacosconfigdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class UserController {


    @Value("${userprefix:Comm}")
    private String uPrex;


    @RequestMapping("/user/getName")
    public String getBalance(Integer id) {

        return uPrex + id;
    }
}
