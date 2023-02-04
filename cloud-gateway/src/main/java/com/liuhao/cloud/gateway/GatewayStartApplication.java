package com.liuhao.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GatewayStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayStartApplication.class, args);
    }

}
