# SpringCloud笔记
# 如何构建一个微服务模块

- 1.建module

- 2.改pom

- 3.写YML

- 4.主启动

- 5.业务类

    建表
    
    entities
    
    dao
    
    service
    
    controller
# 解决window下端口占用问题

```shell
//解决window下端口占用问题
netstat -ano | findstr 61616 //列出进程极其占用的端口，且包含 80
tasklist | findstr 21884
taskkill -PID 21884 -F //强制关闭某个进程
```
# 服务注册中心
- Eureka        停止更新    AP
- Zookeeper     get       CP
- Consul                  CP  
- Nacos         阿里牛逼

## CAP
- C:Consistency(强一致性)
- A:Availability(可用性)
- P:Partition tolerance(分区容错性)

三者最多得其二

# 负载均衡
## Ribbon

负载均衡服务调用  //半死不活

生产环境中还在大规模使用，将来或将被spring的LB替代
Ribbon VS Nginx
- 本地 VS 服务器
- 进程内LB VS 集中式LB

一句话概括Ribbon： 负载均衡+RestTemplate调用

### Ribbon默认负载均衡算法 -- 轮询
```java
public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            log.warn("no load balancer");
            return null;
        }

        Server server = null;
        int count = 0;
        while (server == null && count++ < 10) {
            //所有可用服务
            List<Server> reachableServers = lb.getReachableServers();
            //所有服务，包括未启用的
            List<Server> allServers = lb.getAllServers();
            //可用服务数量
            int upCount = reachableServers.size();
            //所有服务数量
            int serverCount = allServers.size();

            if ((upCount == 0) || (serverCount == 0)) {
                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            int nextServerIndex = incrementAndGetModulo(serverCount);
            server = allServers.get(nextServerIndex);

            if (server == null) {
                /* Transient. */
                Thread.yield();
                continue;
            }

            if (server.isAlive() && (server.isReadyToServe())) {
                return (server);
            }

            // Next.
            server = null;
        }

        if (count >= 10) {
            log.warn("No available alive servers after 10 tries from load balancer: "
                    + lb);
        }
        return server;
    }
    
    private int incrementAndGetModulo(int modulo) {
            for (;;) {
                int current = nextServerCyclicCounter.get();
                int next = (current + 1) % modulo;
                if (nextServerCyclicCounter.compareAndSet(current, next))
                    return next;
            }
        }
```

# OpenFeign 服务调用

# 服务降级
## Histrix断路器 
豪猪哥？？ 

Netflix已停止更新  国内现在主要用阿里的sentinel

但其思想十分优秀，值得学习

**当某个服务发生故障，返回一个备选结果用来兜底，起到一个保险丝的作用，从而避免雪崩。**

官网： https://github.com/Netflix/Hystrix/wiki/How-To-Use

Hystrix is no longer in active development, and is currently in maintenance mode. 闻者伤心，听者落泪。

官网推荐用resilience4j替代 不过中国没啥人用。

## 服务降级-服务熔断-服务限流
- 服务降级：fallback 当某个服务发生故障，返回一个备选结果用来兜底。  eg. 提示 “ 服务器忙，请稍后再试” 
- 服务熔断：保险丝。 达到最大访问服务后，直接拒绝访问，然后调用服务降级的方法并返回友好提示。
- 服务限流：秒杀高并发等操作，一秒钟N个，有序进行。
## resilience4j  
没啥人用

# 网关 Gateway  
spring家自己出的，用来替代Zuul1.x  Zuul2已胎死腹中

底层使用webflux  netty，基于spring5 ，springboot2，project reactor

- 路由
- 断言
- 过滤
# 配置中心
## SpringCloud Config
spring家的，8太行了，Nacos替代 

主要用来解决分布式系统面临的**配置问题**，旨在提供集中式的、动态的配置管理设施

# 消息总线

## SpringCloud Bus
目的：实现配置动态更新
消息中间件：RabbitMQ

一次订阅，处处更新

# 消息驱动

## SpringCloud Stream

先说消息中间件
- ActiveMQ
- RabbitMQ
- RocketMQ
- Kafka

一个系统可能存在两种消息中间件，存在 切换、维护、开发的问题

有没有一种新的技术的诞生，让我们不在关注MQ的具体细节，我们只需用一种适配绑定的方式，自动的给我们在各种MQ之间切换。

cloud stream 应运而生。

一句话：屏蔽底层消息中间件的差异，降低切换成本，统一消息的编程模式

### 官网

- https://spring.io/projects/spring-cloud-stream#overview
- https://cloud.spring.io/spring-cloud-static/spring-cloud-stream/3.0.1.RELEASE/reference/html/
- [Spring Cloud Stream中文指导手册](https://m.wang1314.com/doc/webapp/topic/20971999.html)

### 设计思想

- 标准MQ
  - 生产者/消费者之间靠消息媒介传递信息内容
    - Message
  - 消息必须走特定的通道--消息通
    - MessageChannel
  - 消息通道里的消息如何被消费呢，谁负责收发处理
    - 消息通道MessageChannel的子接口SubscribableChannel,由MessageHandler消息处理器订阅
- 为什么用Cloud Stream

# 分布式请求链路跟踪

## SpringCloud Sleuth

