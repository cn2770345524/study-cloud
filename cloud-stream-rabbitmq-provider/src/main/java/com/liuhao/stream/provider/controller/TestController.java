package com.liuhao.stream.provider.controller;

import com.liuhao.stream.provider.service.MessagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private MessagerService messagerService;

    @RequestMapping(path = "/stream/send", method = RequestMethod.GET)
    public String send() {
        messagerService.send();
        return "success";
    }

}
