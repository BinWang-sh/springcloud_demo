package com.example.bin.sentineldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SentineldemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentineldemoApplication.class, args);
    }

}
