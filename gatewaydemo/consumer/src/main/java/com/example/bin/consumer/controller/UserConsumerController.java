package com.example.bin.consumer.controller;


import com.example.bin.commonmodule.IUserService;
import com.example.bin.commonmodule.UserInfo;
import com.example.bin.consumer.service.UserServiceIf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class UserConsumerController {

    //@Resource
    //private RestTemplate restTemplate;

    @Autowired
    private UserServiceIf userService;

    //@Value("${service-url.nacos-user-service}")
    //private String serverURL;

    @RequestMapping(value = "/getUser", produces = "application/json")
    public UserInfo getUser(@RequestParam Integer id) {
        //return restTemplate.getForObject(serverURL + "/getUserInfo?id=" + id,UserInfo.class);
        return userService.getUserInfo(id);
    }

}
