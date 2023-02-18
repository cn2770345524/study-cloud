package com.liuhao.cloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RequestMapping("/config")
@RestController
public class DefaultController {

    @Value("${config.info}")
    public String configInfo;

    @RequestMapping(path = "/get",method = RequestMethod.GET)
    public String getId(){
        return configInfo;
    }
}
