package com.example.bin.consumer.service;

import com.example.bin.commonmodule.IUserService;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name ="user-provider")
public interface UserServiceIf extends IUserService {

}
