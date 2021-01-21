package com.atguigu.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @ClassName OrderNacosController
 * @Description TODO
 * @Author acui
 * @Date 2021/1/14 20:43
 * @Version 1.0
 **/
@RestController
@Slf4j
public class OrderNacosController {

    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/nacos/{id}")
    public String PaymentInfo(@PathVariable("id") Long id) {
        return restTemplate.getForObject(serverUrl + "/payment/nacos/" + id, String.class);
    }

}
