package com.liuhao.cloud.consumer;

import com.liuhao.cloud.ribbon.settings.MyRuleSetting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

//// 启动该服务时去加载自定义的ribbon配置，注意服务名的大小写问题，有可能因为大小写问题导致无法成功配置
//@RibbonClient(name = "cloud-payment-service", configuration = MyRuleSetting.class)
@EnableDiscoveryClient
@SpringBootApplication
public class Consumer80Application {

    public static void main(String[] args) {
        SpringApplication.run(Consumer80Application.class, args);
    }
}
