package com.liuhao.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProviderStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderStartApplication.class, args);
    }

}
