package com.liuhao.cloud.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/provider")
@RestController
public class DefaultController {

    @Value("${server.port}")
    public Integer serverPort;

    @RequestMapping(path = "/get",method = RequestMethod.GET)
    public String getId(){
        return "server port:"+serverPort;
    }
}
