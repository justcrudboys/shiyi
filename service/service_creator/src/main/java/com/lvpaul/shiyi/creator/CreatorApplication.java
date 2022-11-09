package com.lvpaul.shiyi.creator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.lvpaul")
@EnableFeignClients
public class CreatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(CreatorApplication.class,args);
    }
}
