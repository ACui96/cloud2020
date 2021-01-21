package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHytrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName OrderHytrixController
 * @Description TODO
 * @Author acui
 * @Date 2021/1/13 0:20
 * @Version 1.0
 **/
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalFallback")
public class OrderHytrixController {

    @Resource
    private PaymentHytrixService paymentHytrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public String PaymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHytrixService.PaymentInfo_OK(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    /*@HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")  //3秒钟以内就是正常的业务逻辑
    })*/
    @HystrixCommand
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id){
        int age = 10 / 0;
        String result = paymentHytrixService.PaymentInfo_timeout(id);
        return result;
    }

    //兜底方法
    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id){
        return "我是消费者80，对付支付系统繁忙请10秒钟后再试或者自己运行出错请检查自己,(┬＿┬)";
    }

    /**
     * @Description global fallback  全局默认fallback方法，解决代码膨胀问题，不用给每个方法设置一个fallback方法
     *
     */
    public String globalFallback() {
        return "我是默认fallback方法，系统出错啦~！";
    }

}
