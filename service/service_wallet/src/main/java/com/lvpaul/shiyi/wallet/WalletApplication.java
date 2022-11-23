package com.lvpaul.shiyi.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.lvpaul")
@EnableFeignClients
public class WalletApplication {
    public static void main(String[] args) {
        SpringApplication.run(WalletApplication.class,args);
    }
}
