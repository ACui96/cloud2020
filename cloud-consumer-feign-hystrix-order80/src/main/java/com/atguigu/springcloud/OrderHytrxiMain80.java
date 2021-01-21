package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName OrderHytrxiMain80
 * @Description TODO
 * @Author acui
 * @Date 2021/1/13 0:15
 * @Version 1.0
 **/
@SpringBootApplication
@EnableFeignClients
@EnableHystrix
public class OrderHytrxiMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHytrxiMain80.class, args);
    }
}
