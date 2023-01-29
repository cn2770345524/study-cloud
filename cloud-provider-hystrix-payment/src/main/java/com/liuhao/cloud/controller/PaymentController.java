package com.liuhao.cloud.controller;

import com.liuhao.cloud.commons.entity.CommonResult;
import com.liuhao.cloud.commons.entity.Payment;
import com.liuhao.cloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/payment")
@RestController
public class PaymentController {

    private static Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;

    // 设置失败的兜底犯法，设置方法超时的时间timeout属性
    @HystrixCommand(fallbackMethod = "selectOne_fallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @GetMapping("get/{id}")
    public CommonResult<Payment> selectOne(@PathVariable("id") Long id) {
        Payment payment = this.paymentService.queryById(id);
        logger.info("payment :{}", payment);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new CommonResult<Payment>(200, "port:{" + serverPort + "}", payment);
    }

    @PostMapping("create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        Payment insert = this.paymentService.insert(payment);
        logger.info("payment {} insert:", insert);
        return new CommonResult<>(200, "port:{" + serverPort + "}", insert);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uuid")
    public CommonResult<String> getUUID() {
        String uuid = UUID.randomUUID().toString();
        logger.info("uuid:{}", uuid);
        return new CommonResult<>(200, uuid, null);
    }

    public CommonResult<Payment> selectOne_fallback(Long id) {
        return new CommonResult<>(500,"服务调用失败，返回默认标识");
    }

}
