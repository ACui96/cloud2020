package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName PaymentMain8004
 * @Description
 * @Author acui
 * @Date 2021/1/11 17:08
 * @Version 1.0
 **/
@SpringBootApplication
/**·@EnableDiscoveryClient· 用于使用consul或者zookeeper作为注册中心时注册服务*/
@EnableDiscoveryClient
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class, args);
    }
}
