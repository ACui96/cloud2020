package com.atguigu.springcloud.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author acui
 * @Date 2021/1/11 17:11
 * @Version 1.0
 **/
@RestController
@Slf4j
public class PaymentController {
    //8004
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value="/payment/zk")
    public String paymenttzk() {
        //UUID ：流水号，每次不一样
        return "springCloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID().toString();
    }

}
