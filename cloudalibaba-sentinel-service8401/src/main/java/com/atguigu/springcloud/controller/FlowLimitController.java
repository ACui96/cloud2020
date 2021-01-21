package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName FlowLimitController
 * @Description TODO
 * @Author acui
 * @Date 2021/1/15 16:46
 * @Version 1.0
 **/
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping(value = "/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping(value = "/testB")
    public String testB() {
        log.info(Thread.currentThread().getName()+"\t"+"...testB");
        return "------testB";
    }
    @GetMapping(value = "/testD")
    public String testD() {
        //暂停几秒钟线程
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        int age = 10 / 0;
        log.info("/testD 测试RT");
        return "------testD";
    }
    @GetMapping("/testE")
    public String testE()
    {
        log.info("testE 测试异常数");
        int age = 10/0;
        return "------testE 测试异常数";
    }

    @GetMapping(value = "/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "*********testHotKey";
    }

    public String deal_testHotKey(String p1,String p2,BlockException e) {
        //默认提示
        return "*********deal_testHotKey,/(ㄒoㄒ)/~~";
    }

}
