package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @ClassName HystrixDashbordMain9001
 * @Description TODO
 * @Author acui
 * @Date 2021/1/13 17:00
 * @Version 1.0
 **/
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashbordMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashbordMain9001.class, args);
    }
}
