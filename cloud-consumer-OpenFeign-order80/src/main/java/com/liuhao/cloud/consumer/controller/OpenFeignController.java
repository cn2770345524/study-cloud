package com.liuhao.cloud.consumer.controller;

import com.liuhao.cloud.commons.entity.CommonResult;
import com.liuhao.cloud.commons.entity.Payment;
import com.liuhao.cloud.consumer.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order/openFeign")
@RestController
public class OpenFeignController {

    private static Logger logger = LoggerFactory.getLogger(OpenFeignController.class);

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(method = RequestMethod.GET, value = "/uuid")
    public CommonResult<String> getUUID() {
        CommonResult<String> result = paymentService.getUUID();
        logger.info("uuid:{}", result.getMessage());
        return new CommonResult<>(200, result.getMessage(), null);
    }

    @PostMapping("create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        CommonResult<Payment> res = paymentService.create(payment);
        logger.info(res.toString());
        return res;
    }

    @GetMapping("get/{id}")
    public CommonResult<Payment> selectOne(@PathVariable("id") Long id) {
        CommonResult<Payment> res = paymentService.selectOne(id);
        logger.info(res.toString());
        return res;
    }
}
