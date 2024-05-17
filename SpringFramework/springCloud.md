# SpringCloud组件

## Eureka

Netflix公司开发的模块，用于实现服务治理。系统中的微服务通过Eureka客户端连接到Eureka Server维持心跳，系统通过Eureka来监控系统中的微服务是否正常运行。

+ EurekaServer：服务端，注册中心。负责记录服务信息，进行心跳监控
+ EurekaClient：
  + Provider：服务提供者。注册自己的信息到EurekaServer，每隔一段时间发送心跳
  + Concumer：服务消费者。基于服务名称从EurekaServer中拉去服务，基于服务列表做负载均衡，选中微服务发起远程调用。



## Ribbon

 







## Nacos

