package com.example.bin.sentineldemo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.example.bin.sentineldemo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class FlowLimitTestController {

    @Value("${userprefix:Default}")
    String prefix;

    @Autowired
    private TestService service;

    @GetMapping(value = "/hello/{name}")
    public String apiHello(@PathVariable String name) {
        return service.sayHello(name);
    }

    @GetMapping(value="/getName")
    @SentinelResource(value = "getName-resource", blockHandler = "handleGetNameBlock")
    public  String getName(@RequestParam Integer id) {
        System.out.println("==========" + prefix+"User"+id + "==========");
        return prefix + "User" + id;
    }


    public String handleGetNameBlock(Integer id, BlockException e) {
        return "getName blocked";
    }

}
