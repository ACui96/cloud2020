package com.atguigu.springcloud;

import com.atguigu.myrule.MyselfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;


/**
 * @ClassName OrderMain80
 * @Description TODO
 * @Author acui
 * @Date 2021/1/10 23:44
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyselfRule.class) //不用默认配置，改用定制的
public class OrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class, args);
    }
}
