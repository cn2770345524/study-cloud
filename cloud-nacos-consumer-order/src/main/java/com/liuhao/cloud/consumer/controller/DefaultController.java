package com.liuhao.cloud.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/consumer")
@RestController
public class DefaultController {

    @Value("${server.port}")
    public Integer serverPort;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/get",method = RequestMethod.GET)
    public String getId(){
        String url="http://cloud-nacos-payment-service/provider/get";
        return restTemplate.getForObject(url,String.class);
    }
}
