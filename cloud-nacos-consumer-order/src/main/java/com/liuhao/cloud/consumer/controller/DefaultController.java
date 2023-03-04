package com.liuhao.cloud.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RequestMapping("/consumer")
@RestController
public class DefaultController {

    private static Logger log=LoggerFactory.getLogger(DefaultController.class);

    @Value("${server.port}")
    public Integer serverPort;

    @Autowired
    private RestTemplate restTemplate;

    @SentinelResource("consumer/get")
    @RequestMapping(path = "/get",method = RequestMethod.GET)
    public String getId(){
        String url="http://cloud-nacos-payment-service/provider/get";
        String result = restTemplate.getForObject(url, String.class);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("passed");
        return result;
    }
}
