package com.atguigu.springcloud.Service.impl;

import com.atguigu.springcloud.Service.IMeassageProvider;
import com.sun.media.jfxmediaimpl.HostUtils;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * @ClassName IMeassageProviderImpl
 * @Description TODO
 * @Author acui
 * @Date 2021/1/14 15:31
 * @Version 1.0
 **/
@EnableBinding(Source.class)//定义消息的推送管道
public class IMeassageProviderImpl implements IMeassageProvider {
    @Resource
    private MessageChannel output;//消息发送管道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: " + serial);
        return null;
    }
}
