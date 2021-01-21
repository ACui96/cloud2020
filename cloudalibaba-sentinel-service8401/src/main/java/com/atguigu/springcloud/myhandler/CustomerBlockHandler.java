package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;

/**
 * @ClassName CustomerBlockHandler
 * @Description TODO
 * @Author acui
 * @Date 2021/1/16 16:22
 * @Version 1.0
 **/
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(4444, "按客户自定义，global handlerException-----1");
    }
    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444, "按客户自定义，global handlerException-----2");
    }
}
