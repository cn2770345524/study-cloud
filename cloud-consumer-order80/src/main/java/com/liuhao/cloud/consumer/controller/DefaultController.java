package com.liuhao.cloud.consumer.controller;

import com.alibaba.fastjson.JSON;
import com.liuhao.cloud.commons.entity.CommonResult;
import com.liuhao.cloud.commons.entity.Payment;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping("/consumer")
@RestController
public class DefaultController {

    //public static final String PAYMENT_URL = "http://localhost:8001";
    public static final String PAYMENT_URL = "http://cloud-payment-service";

    private static Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        logger.info("com.liuhao.cloud.consumer.controller.DefaultController.getPayment");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/payment/discovery")
    public Object getDiscoveryInfo() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            logger.info("service name:{}", service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            logger.info("instance of :{}", JSON.toJSONString(instance));
        }
        return discoveryClient;
    }
}
