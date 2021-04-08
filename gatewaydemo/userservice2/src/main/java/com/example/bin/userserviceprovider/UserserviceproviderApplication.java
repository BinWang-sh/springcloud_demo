package com.example.bin.userserviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserserviceproviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceproviderApplication.class, args);
	}

}
