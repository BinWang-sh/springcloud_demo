package com.example.bin.nacosconfigdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UsernacosdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsernacosdemoApplication.class, args);
	}

}
