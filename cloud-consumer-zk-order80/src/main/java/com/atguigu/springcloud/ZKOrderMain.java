package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName ZKOrderMain
 * @Description TODO
 * @Author acui
 * @Date 2021/1/11 19:28
 * @Version 1.0
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ZKOrderMain {
    public static void main(String[] args) {
        SpringApplication.run(ZKOrderMain.class, args);
    }
}
