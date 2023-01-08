package com.liuhao.cloud.service.impl;

import com.liuhao.cloud.commons.entity.Payment;
import com.liuhao.cloud.dao.PaymentDao;
import com.liuhao.cloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    @Override
    public Payment queryById(Long id) {
        return paymentDao.queryById(id);
    }

    @Override
    public List<Payment> queryAllByLimit(int offset, int limit) {
        return paymentDao.queryAllByLimit(offset, limit);
    }

    @Override
    public Payment insert(Payment payment) {
        paymentDao.insert(payment);
        return payment;
    }

    @Override
    public Payment update(Payment payment) {
        paymentDao.update(payment);
        return paymentDao.queryById(payment.getId());
    }

    @Override
    public boolean deleteById(Long id) {
        return paymentDao.deleteById(id) > 0;
    }
}
