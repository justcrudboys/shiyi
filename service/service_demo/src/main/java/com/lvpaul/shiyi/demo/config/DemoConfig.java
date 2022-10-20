package com.lvpaul.shiyi.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lvpaul.shiyi.demo.mapper")
public class DemoConfig {
}
