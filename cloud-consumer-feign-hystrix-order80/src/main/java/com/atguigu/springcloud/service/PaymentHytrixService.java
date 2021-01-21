package com.atguigu.springcloud.service;

/**
 * @ClassName PaymentHytrixService
 * @Description TODO
 * @Author acui
 * @Date 2021/1/13 0:16
 * @Version 1.0
 **/

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentFallbackService.class)//出事了由PaymentFallbackService来处理
public interface PaymentHytrixService {

    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String PaymentInfo_timeout(@PathVariable("id") Integer id);
}
