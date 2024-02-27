# Web入门

## HTTP协议

- Hyper Text Transfer Protocol, 超文本传输协议，规定了浏览器和服务器之间数据传输的规则
- 特点：
  - 基于TCP协议：面向连接，安全
  - 基于请求-响应模型：一次请求对应一次响应
  - HTTP协议是无状态的协议，对于事务处理没有记忆能力，每次请求-响应都是独立的



### HTTP请求协议

- 请求行（请求方式，资源路径，协议）
- 请求头（key：value）
  - **Host**：主机名
  - **User-Agent**：浏览器版本
  - **Accept**：浏览器可接收的资源类型
  - **Accept-Language**：浏览器希望接收到的语言
  - **Accept-Encoding**：浏览器可以支持的压缩类型
  - **Content-Type**：请求主体的数据类型
  - **Content-Length**：请求主体的大小
- 请求体（GET请求没有请求体，POST请求参数在请求体中）



### HTTP响应协议

- 响应行（协议，状态码，描述）
  - 响应状态码
    - 1xx:响应中
    - 2xx:成功
    - 3xx:重定向
    - 4xx:客户端错误
    - 5xx:服务器错误
  - 常见响应状态码
    - 200 OK ：处理成功
    - 302 Found
    - 304 Not Modified：服务端未修改，可直接使用本地缓存
    - 400 Bad Request：请求有语法错误
    - 403 Forbidden：服务器拒绝提供服务
    - 404 Not Found：请求资源不存在
    - 405 Method Not Allowed：请求方式有误
    - 428 Precondition Required：服务器要求有条件的请求  
    - 429 Too Many Requests：请求太多
    - 500 Internal Server Error：服务器发生不可预期的错误
    - 503 Service Unabailable：服务器没有做好准备
- 响应头（key：value）
  - Content-Type：响应内容类型
  - Content-Length：相应内容长度
  - Content-Encoding：响应压缩算法
  - Cache-Control：指示如何缓存
  - Set-Cookie：告知浏览器设置Cookie
- 响应体（存放响应数据）



## 请求响应

前端控制器：DispatcherServlet

HttpServletRequest：请求对象

HttpServletResponse：响应对象

 

BS架构：维护方便，只需要维护服务端，体验一般（主要受带宽等影响）

CS架构：软件的开发和维护比较麻烦（因为需要根据不同的操作系统开发客户端），体验较好



### 请求

Postman：一种网页调试与发送HTTP请求的Chrome插件，用于进行接口测试



#### 简单参数：参数多了就不方便了

**在原始的web程序中获取请求参数**

```java
public String simpleParam(HttpServletRequest request){
    String name = request.getParameter("name");
    String name = request.getParameter("age");
    System.out.println(name+":"+age);
    return "OK";
}
```

**SpringBoot方式**

 ```java
//在请求中要确保请求参数名和形参名一致
public String simpleParam(String name, Integer age){
    System.out.println(name+":"+age);
	return "OK";
}

//如果不一致需要在形参前面添加@RequestParam,该注解中的required属性默认为true，说明这个参数必须传递。
public String simpleParam(@RequestParam(name="name") String username, Integer age){
    System.out.println(name+":"+age);
	return "OK";
}
 ```



#### 实体参数：将多个参数封装成实体进行传递

```Java
//请求的属性名和形参对象的属性名一致
public String simplePojo(User user){
        System.out.println(user);
        return "OK";
}
```



#### 数组参数

```Java
//请求参数名与形参数组名称相同且请求参数为多个
//一个属性名，多个参数值

public String arrayParam(String[] hobby){
        System.out.println(Arrays.toString(hobby));
        return "OK";
}    
```



#### 集合参数

```java
//请求参数名与形参数组名称相同且请求参数为多个
//一个属性名，多个参数值

public String arrayParam(@RequestParam List<String> hobby){
        System.out.println(Arrays.toString(hobby));
        return "OK";
}    
```



#### 日期参数

```java
public String dataParam(@DateTimeFormat(pattern="yyyy-MM-dd")LocalDateTime updateTime){
    System.out.println(updateTime);
    return "OK";
}
```



#### JSON参数

```Java
//要通过RequestBody注释将json格式的数据封装到对象里。
//如果不用RequestBody不会报错，但是内容会为空
public String jsonParam(@RequestBody User user){
        System.out.println(user);
        return "OK";
}
```



#### 路径参数

```Java
//PathVariable获取到路径上的id，并且把id绑定给形参里的id
//路径参数名和形参名称需要保持一致
@RequestMapping("/path/{id}/{name}")
    public String pathParam(@PathVariable Integer id，@PathVariable String name){
        System.out.println(id+":"+name);
        return "OK";
    }
```



### 响应

@ResponseBody

位置：Controller类上/方法上

类型：方法注解，类注解

作用：将方法返回值直接响应，如果返回值类型是“实体对象/集合”，将会转换成JSON格式响应

说明：@RestController = @Controller + @ResponseBody



## 分层解耦

### 三层架构

+ **表示层(User Interface Layer)**：主要模式有MVC（Model-View-Controller)或MVVM（Model-View-ViewModel）。用于显示和接收用户输入的数据。
+ **逻辑层(Business Logic Layer)**：处理业务逻辑和规则
+ **数据访问层(Data Access Layer)：**负责数据库的访问



### IOC&DI

控制反转（Inversion Of Control)：对象创建的控制权由程序内部转移到外部

依赖注入（Dependency Injection）：容器为应用程序提供运行时所依赖的资源

Bean对象：IOC容器中创建，管理的对象



### 相关注解

+ @Component：用于标识一个类是被Spring框架管理的组件。如果组件不能用以下三个注解标注，又需要被容器进行管理，则使用Component
  + @Controller：标注控制器
  + @Service：标志业务类
  + @Repository：标注数据访问类

+ @ComponentScan：用于告诉Spring容器要扫描的包路径，Spring会自动扫描该包和其子包下的所有类。@SpringBootApplication中包含ComponentScan

+ @Autowired：用于实现自动装配，容器会自动查找匹配的Bean，将其注入到目标对象中
  + @Autowired+@Primary：用于设置优先级
  + @Autowire+@Qualifier：用于指定注入bean的名称，如果声明bean的时候没给名字，默认是首字母小写的类名
+ @Resource：JDK提供的。用于指定要注入的bean的名字



# 案例学习

## 分页查询

### 基本实现

+ Controller：提供分页号和每页的数据数量
+ Service：调用Mapper中的方法，将数据总数和每页的数据封装为实体类提供给控制层
+ Mapper：查询数据总数和每页的数据



### PageHelper

+ Controller：

+ Service：调用PageHelper的静态方法startPage设置分页参数；调用Mapper中的方法，将返回结果强转为Page<T>类型。通过getTotal和getResult方法获取所需的数据
+ Mapper：直接查询数据，无需设计两个查询方法



## 文件上传

服务器接收到上传的文件后，有许多种存储方式。可以将其存储在本地硬盘，数据库，以及云存储服务中，也可以自建文件存储服务集群。



### 本地存储

存在的问题：

+ 存储容量限制：本地磁盘容量有限，文件数量过多时会导致存储空间不足
+ 单点故障：如果磁盘出现损坏或安全性问题，容易导致数据的丢失和泄露

在SpringBoot中，默认允许单个上传文件的大小上限为1M，如需上传大文件需要进行如下配置：

```
#配置单个文件最大大小
spring.servlet.multipart.max-file-size=10MB

#配置单个请求最大上传大小（一个请求可以上传多个文件）
spring.servlet.multipart.max-request-size=100MB
```



### 阿里云OSS（云存储服务）

阿里云对象存储服务（Object Storage Service）

使用云存储的过程

1. 服务器接收上传的内容
2. 服务器将内容存储到OSS中
3. OSS提供访问的URL



## 配置文件

### XML（臃肿）

<server>

​	<port>8080</port>

</server>



### properties（层级结构不清晰）

使用@Value注解来进行值注入

- 注入简单类型值：@Value("${property,name}")
- 注入字符串：@Value("Hello World")
- 注入表达式：@Value("#{expression}")

例：server.port=8080



### yml/yaml（最常见）

使用@ConfigurationProperties进行批量注入

例：

server：

​	port：8080



**注意事项：**

+ 大小写敏感
+ 数值前面必须有空格作为分隔符
+ 缩进时不能使用Tab，只能使用空格（idea会自动将Tab转换成空格）
+ 相同层级左侧对齐
+ “#”表示注释



```
#对象/Map集合
user:
	name: Tom
	age: 20
	address: beijing
```

```
#数组/List/Set集合
hobby：
	- java
	- c
	- game
	- sport
```



## 基础登录功能

### 登录校验

#### 会话技术

**会话**：从用户打开浏览器访问web服务器的资源到某一方断开连接，称为一次会话。一次会话中可以包含多次请求和响应。

**会话跟踪：**一种维护浏览器状态的方法。服务器需要识别多次请求是否来自于同一浏览器，以便在同一次会话的多次请求间共享数据

**会话跟踪方案：**

+ 客户端会话跟踪：Cookie
+ 服务端会话跟踪：Session
+ 令牌技术



##### Cookie

**优点**：

+ HTTP协议中天然支持



**缺点**：

+ 移动端APP无法使用Cookie
+ 不安全，用户可以自己禁用
+ Cookie不能跨域（协议，ip地址，端口号三者中有一个不同）



##### Session

基于cookie实现，但此时客户端的cookie仅仅用于存储session ID；cookie技术中，客户端的cookie存放的数据比较复杂

**·优点**：

+ 存储在服务器端，比较安全



**缺点**：

+ 服务器集群环境下无法直接使用Session
+ 底层基于Cookie实现，Cookie有的缺点它都有



##### 令牌技术

**优点**：

+ 支持PC端和移动端
+ 解决集群环境下的认证问题(服务器端不存储，服务器节点只要能验证令牌合法性即可)
+ 减轻服务器端的存储压力



**缺点**：

+ 需要自己实现



#### JWT令牌（JSON Web Token）

**组成**：

1. Header（头）：记录令牌类型，签名算法等
2. Payload（有效载荷）：携带自定义信息，默认信息等
3. Signature（签名）：防止Token被篡改，确保安全性。



JWT校验时使用的签名密钥，必须和生成JWT令牌时使用的密钥是配套的。

如果JWT令牌解析时报错，说明令牌被篡改了或失效了



**实现思路：**

1. 令牌生成：登录成功后，生成JWT令牌并返回给前端

2. 令牌校验：在请求到达服务端后，对令牌进行统一拦截和校验

   

#### 过滤器Filter

1. 定义Filter

   + 初始化方法

   + 拦截方法

   + 销毁方法

2. 配置Filter：在Filter类上添加@WebFilter注解配置拦截资源的路径。引导类上加@ServletComponentScan开启Servlet组件支持



#### 拦截器Interceptor

Filter会拦截所有的资源，Interceptor只会拦截Spring环境中的资源



### 异常处理

Controller->Service->Mapper

Controller<-Service<-Mapper

方案一：在Controller的方法中对异常进行捕获（代码臃肿）

方案二：全局异常处理器



# 事务管理

**事务**：一组操作的集合，是一个不可分割的工作单元

**事务特性（ACID特性）**：

1. 原子性
2. 一致性
3. 隔离性
4. 持久性

**操作**：

1. 开启事务
2. 提交事务
3. 回滚事务



### Spring中的事务管理

@Transactional：将当前方法交给spring进行事务管理，可以作用在方法、类、接口上



**Transactional注解中的属性：**

+ rollbackFor：默认情况下，只有出现RuntimeException才回滚异常。rollbackFor属性用于控制出现何种异常类型
+ propagation：用于指定事务的传播行为，默认值为REQUIRED。



**事务传播行为：**一个事务方法调用另一个事务方法时，如果在两个或多个事物之间进行交互和管理

+ REQUIRED：需要事务。有则加入，无则创建新事务
+ REQUIRES_NEW：需要新事务。无论有无，总是创建新事务。**当不希望事务之间相互影响时，可以使用该传播行为**
+ SUPPORTS：支持事务。有则加入，无则不创建。
+ NOT_SUPPORTED：不支持事务。有则挂起事务，无则运行
+ MANDATORY：必须有事务，否则抛异常
+ NEVER：必须没事务，否则抛异常



# AOP

AOP：Aspect Oriented Programming，面向切面编程。即面向方法编程.

在软件开发中，有许多功能是跨越多个模块的，它们不属于特定的业务逻辑却又存在于整个应用程序中，AOP通过将横切关注点从业务逻辑中分离出来，从而提高代码的模块化性和复用性。

## AOP实现步骤

1. 导入依赖

   ```xml
   <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-aop</artifactId>
   </dependency>
   ```

   

2. 在类上添加@Aspect注解，表明该类是AOP类。

3. 在模板方法上添加通知类型注解，通过切入点表达式指定要调用的原始方法



## AOP核心概念

连接点：JoinPoint，可以被AOP控制的方法

通知：Advice，抽取出来的重复逻辑，即共性功能

切入点：PointCut，匹配连接点的条件，用于决定哪些方法需要加入通知

切面：Aspect，通知+切入点

目标对象：Target，通知所应用的对象



##AOP通知类型

@Aroud：环绕通知。目标方法执行前后都会执行通知方法。环绕通知必须要自己调用ProceedingJoinPoint.proceed()来执行原始方法

@Before：前置通知。目标方法运行之前执行

@After：后置通知。目标方法运行之后执行，无论目标方法是否正确执行都会执行通知方法

@AfterReturning：返回后通知。目标方法正确执行后才会执行通知

@AfterThrowing：异常后通知。目标方法执行出现异常后执行通知



## AOP通知顺序

当有多个切面的切入点都匹配到了目标方法，则需要研究通知的执行顺序

+ 不同切面类中，默认按照切面类的类名字母排序进行通知。
  + 目标方法前的通知方法，字母排名靠前的先执行
  + 目标方法后的通知方法，字母排名靠后的后执行

+ 在切面类上用@Order（数字）注解来控制运行顺序

  + 目标方法前的通知方法，数字小的先执行
  + 目标方法后的通知方法，数字大的先执行

  

## 切入点表达式



常见形式：

1. execution(...)：根据方法的签名来匹配
2. @annotation(...)：根据注解匹配

###execution

execution（访问修饰符？ 返回值 包名.类名.?方法名（方法参数） throws 异常？）

打问号的部分可以省略

**通配符：**

+ ” * “：单个独立的任意符号，可以匹配任意类型的一个参数
+ ” .. “：多个连续的任意符号，可以匹配任意层级的包，或任意类型任意个数的参数



切入点表达式可以通过@Pointcut注解进行抽取

```Java
@Pointcut("execution(* com.xx.xxxx.*(..))")
public void pt(){};//权限修饰符控制可复用该抽取的切面类
```



### @annotation

用于匹配标识有特定注解的方法。先自定义一个注解，然后给需要匹配的方法加上注解

@annotation(注解全类名)





## 连接点

JoinPoint：连接点

ProceedingJoinPoint：JoinPoint的子类，只用于Around通知

```Java
//获取目标对象的类名
String className = joinPoint.getTarget().getClass().getName();

//获取目标方法的方法名
String methodName = joinPoint.getSignature().getName();

//获取目标方法运行时传入的参数
Object[] args = joinPoint.getArgs();

//放行目标方法执行并获取返回值
Object result = joinPoint.proceed();
```





# SpringBoot

## 配置优先级

**优先级由低到高**：

application.yaml

appilication.yml

application.properties

java系统属性(-Dxxx=xxx)

命令行参数(--xxx=xxx)

