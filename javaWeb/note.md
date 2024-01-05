# Web入门

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

