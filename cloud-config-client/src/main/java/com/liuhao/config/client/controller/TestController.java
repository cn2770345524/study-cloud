package com.liuhao.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 读取配置中心指定配置文件的内容，并展示到页面
@RefreshScope //为了让动态（手动）的获取最新的git 配置，在添加 actuator 监控加载 RefreshScope，
@RequestMapping("/config/test")
@RestController
public class TestController {

    @Value("${config.version}")
    private String configVersion;

    @RequestMapping("/method")
    public String TestMethod() {
        return configVersion;
    }
}
