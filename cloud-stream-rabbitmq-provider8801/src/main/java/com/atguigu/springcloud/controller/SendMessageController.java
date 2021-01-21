package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.Service.IMeassageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName SendMessageController
 * @Description TODO
 * @Author acui
 * @Date 2021/1/14 15:40
 * @Version 1.0
 **/
@RestController
public class SendMessageController {
    @Resource
    private IMeassageProvider iMeassageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMeassage() {
        return iMeassageProvider.send();
    }
}
