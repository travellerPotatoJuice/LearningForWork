# 员工相关

## 新增员工

**密码存储：**

数据库存储密码时不能存储明文密码，应该进行加密。

当从前端获取到用户输入的密码时，用同样的方式将密码进行加密，并且与数据库中存放的密码加密值并且比对以校验密码的正确性。

```Java
//MD5加密
DigestUtils.md5DigestAsHex
```



**DTO：**

DTO（Data Transfer Object）用于封装数据，可以有效地减少不必要的数据交互

**DTO**

```Java
//批量数据拷贝
BeanUtils.copyProperties(Object source, Object target)
```



**异常处理：**

当录入多次被标注为unique字段的值时，会出现SQLIntegrityConstraintViolationException异常，需要在全局异常处理器中对其进行处理，返回带有异常信息的Result

```Java
//这段代码写得不是很好
@ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String message = ex.getMessage();
        if(message.contains("Duplicate entry")){
            String[] split = message.split(" ");
            String username = split[2];
            String msg = username+ MessageConstant.ALREADY_EXISTS;
            return Result.error(msg);
        }else{
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }
```



**动态获取操作人ID：**

客户端每次发起的每一次请求都是一次独立的线程，线程内部共享存储空间，可以通过Thread的局部变量ThreadLocal将Jwt令牌解析到的用户id存储起来，在用户进行操作时再取出相应的数据

在实现时最好将其进行封装再使用

```Java
public class BaseContext {

    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
```



## 员工分页查询

**基于PageHelper实现分页查询：**



**日期格式化：**

方法一：在实体类的属性上添加注解

```Java
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private LocalDateTime createTime;
```



方法二：在WebMvcConfiguration中扩展Spring MVC消息转换器，同一对日期类型进行格式化处理

```Java
@Override
    protected void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器...");
        //创建一个消息转换器
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        //为消息转换器设置一个对象转换器，该对象转换器可以将java对象序列化成json数据
        converter.setObjectMapper(new JacksonObjectMapper());
        //将转换器加入到容器的第一位
        converters.add(0,converter);
    }
```



## 启用/禁用员工账号

**build注解**



**动态修改：**

启用和禁用员工账号的时候只传递了两个参数，但是在service实现类中传递参数给mapper时可以传递Employee实体类，只需要在mapper中使用动态的sql判断字段是否为空即可。这样书写有一个好处，可以让后续对employee进行修改的操作都复用这个接口





## 编辑员工

**根据id获取员工数据：**

在修改员工信息之前需要先实现数据回显的功能，让用户在点击修改按钮后可以看到需要修改的内容，因此这个功能需要在修改员工信息功能之前实现

需要注意的是，通过select语句查询到员工后，最好可以手动将密码设置成不可见的字符，这种在service层的修改并没有将修改内容写回数据库，所以不会对数据库中的内容造成影响，只会影响返回给前端的内容

```java
@Override
public Employee getById(Long id) {
    Employee employee = employeeMapper.getById(id);
    employee.setPassword("****");
    return employee;
}
```





# 菜品管理

## 公共字段自动填充

每次对内容进行修改时都需要重复set修改时间和修改人id，出现了很多冗余代码，不便于后期维护

**解决方法：**

+ 自定义AutoFill注解，用于标识需要进行公共字段自动填充的方法
+ 自定义切面类AutoFillAspect，统一拦截加入了AutoFill注解的方法，通过反射为公共字段赋值
+ 在Mapper的方法上加入AutoFill注解



自定义切面类时，首先定义切入点。切入点表达式指明了需要执行通知的方法，但范围过大，所以需要用@annotation来指定注解，说明只有在上述执行范围内，并且添加了@AutoFill注解的方法才需要自动填充

```Java
@Pointcut("execution (* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill)")
    public void autoFillPointCut(){}
```

然后编写通知内容

```Java
//获取当前被拦截方法上的数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value(); //获取操作类型
```



## 文件上传

**阿里云bucket配置**

首先需要在yml配置文件中写好endpoint, access-key-id, access-key-secret, bucket-name的值。

再写一个工具类AliOssUtil，通过这个类创建OSSClient实例，并且创建PutObject请求。

最后写一个配置类OssConfiguration，通过这个类返回一个AliOssUtil对象，并且将其交给Ioc容器管理。

```Java
public class OssConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){
        log.info("创建阿里云文件上传工具类对象：{}",aliOssProperties);
        return new AliOssUtil(aliOssProperties.getEndpoint(), aliOssProperties.getAccessKeyId(),
                aliOssProperties.getAccessKeySecret(), aliOssProperties.getBucketName());
    }
}
```



**上传文件：**

获取文件的原始名称，再拼接到UUID生成的字符串后面，以保证文件名的唯一性，防止文件上传到bucket后因为重名而被覆盖

```Java
UUID.randomUUID().toString();
```



## 新增菜品

