package com.atguigu.springcloud.controller;

/**
 * @ClassName ConfigClientController
 * @Description TODO
 * @Author acui
 * @Date 2021/1/14 21:10
 * @Version 1.0
 **/

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //支持Nacos的动态刷新功能
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @GetMapping(value = "/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
