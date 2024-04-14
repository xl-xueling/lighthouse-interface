package com.dtstep.lighthouse.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import java.util.HashMap;
import java.util.Map;


@SpringBootApplication(scanBasePackages = {"com.dtstep.lighthouse.api"},exclude = {SecurityAutoConfiguration.class,DataSourceAutoConfiguration.class})
public class LightHouseAPIApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(LightHouseAPIApplication.class);
        Map<String,Object> defaultProperties = new HashMap<>();
        defaultProperties.put("spring.config.name","lighthouse-interface");
        springApplication.setDefaultProperties(defaultProperties);
        springApplication.run(args);
    }
}
