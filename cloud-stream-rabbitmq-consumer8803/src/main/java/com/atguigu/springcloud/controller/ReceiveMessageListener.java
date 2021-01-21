package com.atguigu.springcloud.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName ReceiveMessageListener
 * @Description TODO
 * @Author acui
 * @Date 2021/1/14 16:58
 * @Version 1.0
 **/
@EnableBinding(Sink.class)
@Component
public class ReceiveMessageListener {

    @Value("${server.port}")
    private String serverPort;


    @StreamListener(Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者2号，------>接受到的消息： " + message.getPayload() + "\t port:" + serverPort);
    }
}
