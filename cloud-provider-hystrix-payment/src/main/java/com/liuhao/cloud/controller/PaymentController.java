package com.liuhao.cloud.controller;

import com.liuhao.cloud.commons.entity.CommonResult;
import com.liuhao.cloud.commons.entity.Payment;
import com.liuhao.cloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
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
@DefaultProperties(defaultFallback = "default_fallback_method")
public class PaymentController {

    private static Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private PaymentService paymentService;

    // 为当前方法设置熔断机制，允许开启熔断器，当2秒内10次请求失败了6次则视为当前接口失效，直接走降级方法的逻辑
    // 窗口期为10秒，当熔断器开启后10秒自动重试，如果2秒10次请求失败小于6次则视为接口已恢复
    // 注意：在10秒的窗口期内，就算发送是正确请求，也会因为熔断器的开启，返回默认的fallback_method
    @HystrixCommand(fallbackMethod = "selectOne_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启熔断器
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "2000"), //统计时间窗
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //统计时间窗内请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //在统计时间窗口期以内，请求失败率达到 60% 时进入熔断状态)
    })
    @GetMapping("get/{id}")
    public CommonResult<Payment> selectOne(@PathVariable("id") Long id) {
        if (id <= 0) {
            throw new RuntimeException("id 必须大于0");
        }
        Payment payment = this.paymentService.queryById(id);
        logger.info("payment :{}", payment);
        return new CommonResult<Payment>(200, "port:{" + serverPort + "}", payment);
    }

    @PostMapping("create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        Payment insert = this.paymentService.insert(payment);
        logger.info("payment {} insert:", insert);
        return new CommonResult<>(200, "port:{" + serverPort + "}", insert);
    }

    @HystrixCommand
    @RequestMapping(method = RequestMethod.GET, value = "/uuid")
    public CommonResult<String> getUUID() {
        String uuid = UUID.randomUUID().toString();
        int a = 10 / 0;
        logger.info("uuid:{}", uuid);
        return new CommonResult<>(200, uuid, null);
    }

    public CommonResult<Payment> selectOne_fallback(Long id) {
        return new CommonResult<>(500, "服务调用失败，返回默认标识");
    }

    public CommonResult<String> default_fallback_method() {
        return new CommonResult<>("当前接口没有设置降级方法，因此使用默认降级方法");
    }

}
