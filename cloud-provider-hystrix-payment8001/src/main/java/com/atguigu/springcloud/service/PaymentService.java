package com.atguigu.springcloud.service;

/**
 * @ClassName PaymentService
 * @Description TODO
 * @Author acui
 * @Date 2021/1/12 23:31
 * @Version 1.0
 **/


import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;


@Service
public class PaymentService {
    /**
     * 正常访问，肯定OK
     * @param id
     * @return
     */
    public String PaymentInfo_OK(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_OK,id: " + id + "\t" + "哈哈";
    }
    /**
     *
     * @param id
     * @return
     */

    @HystrixCommand(fallbackMethod = "PaymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String PaymentInfo_Timeout(Integer id) {
        //暂停毫秒
//        int timeNumber = 5;
        //模拟运行报错
//        int age = 10 / 0;
        try {
//        模拟超时
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + "paymentInfo_Timeout,id: " + id + "\t" + "哈哈"+"耗时(s)：";
    }


    public String PaymentInfo_TimeoutHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + "8001系统忙或运行报错，请稍后再试！id: " + id + "\t" + "/(ㄒoㄒ)/~~";

    }

    //-----服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }
}
