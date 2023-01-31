package com.liuhao.cloud.consumer.service.impl;

import com.liuhao.cloud.commons.entity.CommonResult;
import com.liuhao.cloud.commons.entity.Payment;
import com.liuhao.cloud.consumer.service.PaymentService;
import org.springframework.stereotype.Component;

/**
 * feign 配合 hystrix在服务层面上通过接口实现的方式实现fallback兜底
 **/
@Component
public class PaymentServiceFallbackImpl implements PaymentService {


    @Override
    public CommonResult<String> getUUID() {
        return new CommonResult<>("com.liuhao.cloud.consumer.service.impl.PaymentService.getUUID fallback");
    }

    @Override
    public CommonResult<Payment> create(Payment payment) {
        return new CommonResult<>("com.liuhao.cloud.consumer.service.impl.PaymentService.create fallback");
    }

    @Override
    public CommonResult<Payment> selectOne(Long id) {
        return new CommonResult<>("com.liuhao.cloud.consumer.service.impl.PaymentService.selectOne fallback");
    }
}
