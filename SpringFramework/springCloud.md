# SpringCloud组件

## Eureka

Netflix公司开发的模块，用于实现服务治理。系统中的微服务通过Eureka客户端连接到Eureka Server维持心跳，系统通过Eureka来监控系统中的微服务是否正常运行。

+ EurekaServer：服务端，注册中心。负责记录服务信息，进行心跳监控
+ EurekaClient：
  + Provider：服务提供者。注册自己的信息到EurekaServer，每隔一段时间发送心跳
  + Concumer：服务消费者。基于服务名称从EurekaServer中拉去服务，基于服务列表做负载均衡，选中微服务发起远程调用。



## Ribbon

 

**负载均衡策略：**

+ 在配置类中定义一个IRule类型的Bean（作用位置是全体，对所有微服务都采用这个规则）
+ 在配置文件中添加负载均衡规则（针对某个微服务而言的）





## Nacos

+ 服务消费者：定时拉取服务，远程调用服务提供者

+ nacos注册中心：对服务消费者主动推送服务变更信息，对服务提供者的非临时实例主动询问是否存活

+ 服务提供者：在注册中心中注册服务信息

  + 临时实例：采用心跳检测
  + 非临时实例：nacos主动询问

  

父工程依赖：

```xml
<dependency>
   <groupId>com.alibaba.cloud</groupId>
   <artifactId>spring-cloud-alibaba-dependencies</artifactId>
   <version>2.2.5.RELEASE</version>
   <type>pom</type>
   <scope>import</scope>             
</dependency>
```

客户端依赖：

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

临时示例与非临时实例配置：

```yaml
spring:
	cloud:
		nacos:
			discovery:
				ephemeral: false #设置为非临时实例
		
```



<font size=5>**nacos配置管理**</font>

配置获取的步骤如下：

1. 项目启动
2. 读取bootstap.yaml中的nacos地址
3. 读取nacos中的配置文件
4. 读取本地配置文件
5. 根据配置文件进行后续操作



**操作步骤如下：**

1. 引入nacos的配置管理客户端依赖

   ```
   <dependency>
       <groupId>com.alibaba.cloud</groupId>
       <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
   </dependency>
   ```

2. 在需要进行nacos配置管理的服务中添加一个bootstrap配置文件，设置其优先级高于application.yaml

   ```yaml
   spring:
   	application:
   		name: userservice
   	profiles:
   		active: dev
   	cloud:
   		nacos:
   			server-addr: localhost:8848
   			config:
   				file-extension: yaml
   ```

3. 设置配置自动刷新。让Nacos中的配置文件变更后，微服务无需重启就可以感知到。主要有两种方式可以实现

   方式一：添加注解@RefreshScope

   方式二：使用注解@ConfigurationProperties。相当于创建一个类，再在controller中调用这个类的成员变量。其实和方式一是一个道理。推荐这个方法



**多环境配置共享：**

微服务会从nacos中读取多个配置文件：

1. 服务名-环境名.yaml
2. 服务名.yaml

配置文件的优先级：环境配置 > 环境共享配置 > 本地配置





<font size=5>**nacos集群**</font>

通过nginx实现nacos集群的负载均衡，通过nacos节点实现服务实例的负载均衡。



##  Feign

RestTemplate是Spring框架提供的一个用于访问Rest服务的客户端工具，Feign也实现了类似的功能，并且在内部集成了Ribbon，可以自动实现负载均衡。

1. 引入依赖

   ```xml
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-openfeign</artifactId>
   </dependency>
   ```

2. 在service的启动类添加注解来开启Feign的功能

   ```
   @EnableFeignClients
   ```

3. 编写Feign客户端

   ```
   @FeignClient("服务名称")
   public interface UserClient {
   	@GetMapping("/xxx/xxx")
   	User findById(Long id);
   }
   ```

   

<font size=5>**Feign自定义配置**</font>

+ feign.Logger.Level：修改日志级别
+ feign.coder.Decoder：响应结果的解析器。对http远程调用的结果作解析
+ feign.codec.Encoder：请求参数编码。将请求参数编码，便于通过http请求发送
+ feign.Contract：支持的注解格式。默认是SpringMVC的注解
+ feign.Retryer：失败重试机制



配置的方式：

方式一：修改配置文件

```yaml
#全局生效
feign:
	client:
		config:
			default:
				loggerLevel: FULL
```

```yaml
#局部生效
feign:
	client:
		config:
			userservice:     #服务名
				loggerLevel: FULL
```



方式二：用java代码

```java
public class FeignClientConfiguration{
	@Bean
    public Logger.Level feignLogLevel(){
        return Logger.Level.BASIC
    }
}

```

如果是全局配置，就将其放在@EnableFeignClients注解中。如果是局部配置，就将其放在FeignClient注解中

```
@EnableFeignClients(defaultConfiguration = FeignClientConfiguration.class)

@FeignClient(value="xxservice", configuration = FeignClientConfiguration.class)
```



<font size=5>**Feign性能优化**</font>

Feign底层的客户端实现：

+ URLConnection：默认实现，不支持连接池
+ Apache HttpClient：支持连接池
+ OKHttp：支持连接池



优化：

+ 使用连接池替代默认的URLConnection
+ 日志级别最好用basic或none

```xml
<!--引入httpclient的依赖-->
<dependency>
	<groupId>io.github.openfeign</groupId>
    <artifactId>feign-httpclient</artifactId>
</dependency>
```

```yaml
feign: 
	client:
		config:
			default:	#default全局的配置
				loggerLevel: BASIC	# 日志级别
	httpclient:
		enabled: true
		max-connections: 200    #最大连接数
		max-connections-per-route: 50      #每个路径的最大连接数
```



<font size=5>**Feign最佳实践**</font>

方式一：继承。给消费者的FeignClient和提供者的Controller定义统一的父接口作为标准（一般不推荐，因为会导致两个服务紧耦合

方式二：抽取。将FeignClient抽取为独立模块，将与接口有关的Pojo、默认的Feign配置都放在这个模块中，提供给所有消费者使用

+ 创建一个module，引入feign的starter依赖
+ 将实体类，feign的client，以及feign的配置都写到feign-api module中
+ 在消费者service中引入feign-api依赖
+ 在消费者service的启动类中修改EnableFeignClient的属性值（主要是为了指定扫描的位置，否则Spring默认的扫描位置是当前启动类所在的包，不会扫描到其他的包，从而不会创建Bean） 



## Gateway

网关就是为微服务架构提供简单而有效的统一的API路由管理方式。主要功能包括：

+ 身份认证和权限校验
+ 服务路由、负载均衡
+ 请求限流

SpringCloud中网关的实现方式有两种：Gateway和Zuul。

Gateway基于WebFlux实现，属于响应式编程

Zuul基于Servlet实现，属于阻塞式编程

 

<font size=5>**Gateway快速入门**</font>

1. 创建一个module作为网关服务

2. 引入nacos服务发现依赖（网关也是一项服务，需要注册到注册中心）和网关依赖

   ```xml
   <!--nacos服务发现依赖-->
   <dependency>
   	<groupId>com.alibaba.cloud</groupId>
   	<artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
   </dependency>
   
   <!--网关Gateway依赖-->
   <dependency>
   	<groupId>org.springframework.cloud</groupId>
   	<artifactId>spring-cloud-starter-gateway</artifactId>
   </dependency>
   ```

3. 在yaml文件中编写配置信息

   ```yaml
   server:
     port: 10010
   spring:
     application:
       name: gateway
     cloud:
       nacos:
         server-addr: localhost:8848
       gateway:
         routes:
           id: user-service
           # uri: http://127.0.0.1:8081 指定路由的目标地址
           uri: lb://userservice   # 这种写法更好，可以实现负载均衡（loadBalance）
           predicates:     # 路由断言，判断请求是否符合路由规则的条件
           	- Path=/user/**  # 指定使用的断言工厂，Path是按路径匹配
   ```



路由配置包括

+ 路由id：路由的唯一标识
+ 路由目标（uri）：路由的目标地址
+ 路由断言（predicates）：判断路由的规则
+ 路由过滤器（filters）：对请求或响应做处理



<font size=5>**路由断言工厂**</font>

用于定义路由请求的匹配条件的机制。在配置文件中写的断言规则只是字符串，这些字符串会被断言工厂读取并处理，转变为路由判断的条件。

+ After：某个时间点之后的请求
+ Before：某个时间点之前的请求
+ Between：某两个时间点之间的请求
+ Cookie
+ Header
+ Host
+ Method
+ Path
+ Query
+ RemoteAddr：对请求者的ip地址范围进行限制
+ Weight



<font size=5>**路由过滤器**</font>

对进入网关的请求和微服务返回的响应做处理

+ 当前路由过滤器
+ DefaultFilter：默认过滤器
+ GlobalFilter：全局过滤器



**路由器执行顺序：**

order值越小，优先级越高。在配置文件中，filters和defaultFilters中的过滤器各自按照声明的顺序赋order值。当过滤器的order值一样时，defaultFilter > 路由过滤器 > 全局过滤器  

+ GlobalFilter通过实现Ordered接口，或者添加@Order注解来指定order值

+ 路由过滤器和defaultFilter的order由Spring指定



<font size=5>**网关的跨域问题处理**</font>

跨域问题：浏览器在执行一个网页中的脚本时，出于安全考虑，会限制该脚本访问其他来源（即协议、域名或端口中至少有一个不同）的资源。

CORS：（Cross-Origin Resouce Sharing，跨来源资源共享）。一个W3C规范，它定义了一种浏览器和服务器交互的方式来确定是否允许跨来源请求。这个机制通过一种特殊的HTTP头部（即CORS headers）来告诉浏览器，位于一个源（domain）上的网页所加载的脚本可以访问来自另一个源的资源，而无需通过传统的同源策略（Same-Origin Policy）来限制。

```yaml
spring:
	cloud:
		gateway:
			globalcors: #全局跨域处理
				add-to-simple-url-handler-mapping: true  #不拦截option请求
				corsConfigurations:
					'[/**]':
						allowedOrigins: #允许哪些网址的跨域请求
							- "http://localhost:8081"
							- "http://www.leyou.com"
						allowedMethods: #允许跨域的请求方式
							- "GET"
							- "POST"
						allowedHeaders: "*" #允许在请求中携带的头信息
						allowCredentials: true #是否允许携带cookie
						maxAge: 360000 #跨域检测的有效期

```



# Docker

Docker是一个容器化平台，允许开发者将应用程序及其依赖项打包到一个可移植的容器中，从而简化应用程序的部署过程。

**docker如何解决大型项目依赖关系复杂的，不同组件依赖的兼容性问题？**

+ Docker将应用、依赖、函数库、配置一起打包，形成可移植镜像
+ Docker应用运行在容器中，使用沙箱机制，相互隔离

**Docker怎么解决开发、测试、生产环境差异的问题？**

+ Docker镜像中包含完整运行环境，包括系统函数库，只要是基于Linux内核就可以。

-------------------------------------

<font size=5>**Docker基本操作**</font>

**镜像命令**

```shell
#拉取镜像
docker pull xx

#查看镜像
docker images

#导出镜像
docker save [options] image [image]
docker save -o nginx.tar nginx:latest

#移除镜像
docker rmi nginx:latest

#加载镜像
docker load [options]
docker load -i nginx.tar
```



**容器命令**

```shell
#创建并运行一个容器
docker run
 --name:容器名称
 -p：指定端口映射
 -d：让容器后台运行

#挂起运行中的容器
docker pause

#恢复容器到运行状态
docker unpause

#停止容器
docker stop

#启动容器
docker start

#查看容器状态
docker ps

#查看日志
docker logs
docker logs -f   #持续查看日志

#进入容器执行命令
docker exec
	-it:给当前要进入的容器创建一个标准输入输出终端，允许我们与容器交互
docker exec -it mn bash


#删除容器
docker rm
```

--------------------

<font size=5>**docker数据卷**</font>

docker提供的一种持久化存储的机制，允许宿主机和容器之间，容器与容器之间共享文件或目录。即使容器被删除，数据卷中的数据仍然会保留在宿主机上

```shell
#创建数据卷
docker volumn create [name]
docker volumn create html

#列出所有数据卷
docker volumn ls

#查看数据卷的详细信息
docker volumn inspect [name]
docker volumn inspect html

#删除未使用的数据卷
docker volumn prune

#删除指定数据卷
docker volumn rm [name]
docker volumn rm html
```



```
#数据卷挂载方式，如果targetContainerPath不存在，则会自动创建
-v [volumeName: /targetContainerPath]
docker run --name mn -p 80:80 -v html:/usr/share/nginx/html -d nginx

#目录挂载方式，如果targetContainerPath不存在，则会自动创建
-v [宿主机目录: 容器目录]

docker run \
	--name mysql \
	-e MYSQL_ROOT_PASSWORD=123456 \
	-p 3306:3306
	-v /tmp/mysql/conf/hmy.cnf:/etc/mysql/conf.d/hmy.cnf
	-v /tmp/mysql/data:/var/lib/mysql
	-d \
	mysql:5.7.25
```



**数据卷挂载方式比较**

+ 数据卷挂在由docker来管理目录，但是不好找目录
+ 目录挂载需要自己管理目录，不过目录容易寻找查看

-------------------------

<font size=5>**Docker自定义镜像**</font>

**镜像的分层结构：**

入口（Entrypoint）：镜像运行入口，一般是程序启动的脚本和参数

层（Layer）：在BaseImage基础上添加安装包、依赖、配置等，每次操作都形成新的的一层

基础镜像层（BaseImage）



Dockerfile是一个文本文件，其中包含很多指令，用指令来说明要执行什么操作来构建镜像，每一个指令都会形成一层Layer。其第一行必须是FROM，从一个基础镜像来构建，基础镜像可以是基本操作系统也可以是其他人制作好的镜像（可以共享前面几层，dockerfile就可以少写点指令了）。

------------------

<font size=5>**DockerCompose**</font>

可以基于Compose文件快速部署分布式应用，而无需手动一个个创建和运行容器。

Compose文件是一个文本文件，通过指令定义集群中的每个容器如何运行，一般是yaml格式的。

```shell
#查看帮助文档
docker-compose --help
```

------------

<font size=5>**Docker镜像仓库**</font>

镜像仓库（Docker Registry）分为公有和私用两种：

+ 公共仓库：例如Docker官方的Docker Hub、网易云镜像服务、DaoCloud镜像服务、阿里云镜像服务等
+ 私有仓库：个人或企业自行搭建，搭建可以基于Docker官方提供的DockerRegistry来实现



# MQ

## 基本概念

**同步调用存在的问题**

+ 耦合度高：每次加新需求都需要修改原来的代码
+ 性能下降：调用者需要等待服务提供者响应，如果调用链过长会影响响应的时间
+ 资源浪费：调用链中的每个服务在等待响应过程中不能释放请求占用的资源
+ 级联失败：如果服务提供者出问题，所有调用方都会跟着出问题



**异步调用的优点：**

+ 耦合度低：通过订阅机制降低了耦合度
+ 性能提升：不需要调用，不用长时间等待，提高了吞吐量
+ 故障隔离：没有强依赖关系，不用担心级联失败
+ 流量削峰



**异步调用的缺点：**

+ 依赖Broker：Broker的性能显著影响整体性能
+ 架构复杂：业务没有明显的流程线，不好追踪管理



