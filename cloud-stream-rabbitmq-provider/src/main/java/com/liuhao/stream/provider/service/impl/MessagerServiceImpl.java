package com.liuhao.stream.provider.service.impl;

import com.liuhao.stream.provider.service.MessagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@EnableBinding(Source.class)
public class MessagerServiceImpl implements MessagerService {

    /**
     * 消息发送管道/信道
     */
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        for (int i = 0; i < 100; i++) {
            String serial = UUID.randomUUID().toString();
            Message<String> stringMessage = MessageBuilder.withPayload(serial).build();
            output.send(stringMessage);

            System.out.println("*****serial: " + serial);
        }
        return "";
    }
}
