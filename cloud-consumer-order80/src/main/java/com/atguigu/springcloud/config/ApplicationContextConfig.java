package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName ApplicationContextConfig
 * @Description TODO
 * @Author acui
 * @Date 2021/1/10 23:54
 * @Version 1.0
 **/
@Configuration
public class ApplicationContextConfig {

    /**
     *使用 @LoadBalanced 注解赋予 RestTemplate ·负载均衡·的能力
     */
    @Bean
    //@LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
