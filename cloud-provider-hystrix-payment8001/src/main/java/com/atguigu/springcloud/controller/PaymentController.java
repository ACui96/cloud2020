package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName PaymentController
 * @Description TODO
 * @Author acui
 * @Date 2021/1/12 23:39
 * @Version 1.0
 **/
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.PaymentInfo_OK(id);
        log.info("******result:" + result);
        return result;
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String PaymentInfo_timeout(@PathVariable("id") Integer id) {
        String result = paymentService.PaymentInfo_Timeout(id);
        log.info("******result:" + result);
        return result;
    }

    @GetMapping(value = "/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("*****result:" + result);
        return result;
    }

}
