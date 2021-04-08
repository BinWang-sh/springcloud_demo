package com.example.bin.commonmodule;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface IUserService {
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    UserInfo getUserInfo(@RequestParam("id") Integer id);
}