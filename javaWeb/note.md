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

