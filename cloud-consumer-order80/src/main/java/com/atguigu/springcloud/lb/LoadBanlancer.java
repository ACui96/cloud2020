package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author acui
 * @Date 2021/1/12 21:28
 * 自己实现的轮询方法
 **/
public interface LoadBanlancer {
    ServiceInstance instance(List<ServiceInstance> serviceInstances);
}
