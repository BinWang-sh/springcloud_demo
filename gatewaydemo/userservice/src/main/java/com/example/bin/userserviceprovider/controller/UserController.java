package com.example.bin.userserviceprovider.controller;


import com.example.bin.commonmodule.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/getUserInfo", produces= MediaType.APPLICATION_JSON_VALUE)
    public UserInfo getUserInfo(@RequestParam Integer id) {
        return new UserInfo(id, "user"+id, 22, "SH:"+serverPort);
    }
}