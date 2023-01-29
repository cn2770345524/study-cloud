package com.liuhao.cloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableHystrix // 开启Hystrix
@EnableFeignClients // 开启FeignClient
@EnableDiscoveryClient
@SpringBootApplication
public class HystrixConsumer80Application {

    public static void main(String[] args) {
        SpringApplication.run(HystrixConsumer80Application.class, args);
    }
}
