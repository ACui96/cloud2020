package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName PaymentFallbackService
 * @Description TODO
 * @Author acui
 * @Date 2021/1/13 15:43
 * @Version 1.0
 **/
@Component
public class PaymentFallbackService implements PaymentHytrixService{
    @Override
    public String PaymentInfo_OK(Integer id) {
        return "------PaymentFallbackService fallback-PaymentInfo_OK, /(ㄒoㄒ)/~~";
    }

    @Override
    public String PaymentInfo_timeout(Integer id) {
        return "------PaymentFallbackService fallback-PaymentInfo_timeout, /(ㄒoㄒ)/~~";
    }
}
