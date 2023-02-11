package com.liuhao.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
