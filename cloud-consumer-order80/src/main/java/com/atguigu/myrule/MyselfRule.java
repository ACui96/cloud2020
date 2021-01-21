package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyselfRule
 * @Description TODO
 * @Author acui
 * @Date 2021/1/12 20:35
 * @Version 1.0
 **/
@Configuration
public class MyselfRule {
    @Bean
    public IRule myRule() {
        //定义为随机
        return new RandomRule();
    }
}
