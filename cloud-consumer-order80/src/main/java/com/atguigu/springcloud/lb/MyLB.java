package com.atguigu.springcloud.lb;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MyLB
 * @Description TODO
 * @Author acui
 * @Date 2021/1/12 21:30
 * @Version 1.0
 **/
@Component
public class MyLB implements LoadBanlancer {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        // while 条件 为 自旋锁+CAS
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("********第几次访问next:" + next);
        return next;
    }

    /**
     *
     * @param serviceInstances 服务总数
     * @return 要用那个服务
     */
    @Override
    public ServiceInstance instance(List<ServiceInstance> serviceInstances) {
        // 负载均衡算法： rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标， 每次服务重新启动后rest接口数从1开始。
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
