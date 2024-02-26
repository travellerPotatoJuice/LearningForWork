# Spring概述

## 为什么需要Spring

### 传统Javaweb开发存在的问题：

+ 接口与具体实现之间紧密耦合。解决方法：在程序代码中不手动创建对象，而是由第三方根据要求创建
+ 通用的事务以及日志功能耦合在业务对象中。解决方法：第三方根据要求为程序提供需要的Bean对象的代理对象



## Spring Framework系统架构

**框架**：基于基础技术之上，从众多业务中抽取出的通用解决方案。

**常见基础框架：**MyBatis，Spring，SpringMVC

**常见服务框架：**MQ，ES，Nacos



## IoC，DI，AOP

IoC：Inversion of Control.。控制反转。指将创建Bean的权利反转给第三方

DI：Dependency Injection。依赖注入。由第三方来负责设计Bean之间的关系

AOP：Aspect Oriented Programming。面向切面编程。功能的横向抽取





# xml配置开发

## 定义bean

```xml
<bean id="bookDao" class="com.spring.dao.impl.BookDaoImpl"/>
<bean id="bookService" class="com.spring.service.impl.BookServiceImpl">
     <property name="bookDao" ref="bookDao"/>
</bean>
```

**说明：**

+ bean

  + id属性用于指明bean的名称
  + name属性用于指明bean的别名，不同的bean可以拥有一样的别名
  + class属性指定bean的类名，Spring容器会根据这个类名实例化一个bean对象
  + scope属性用于指定bean的作用域，默认为singleton（单例模式）
  + init-method用于指明初始化方法
  + destory-method属性用于指明销毁方法
  + autowire属性用于指明自动装配的类型
  + factory-method属性用于指明bean的工厂方法
  + factory-bean属性用于指明实例工厂bean
  + lazy-init属性控制bean的延迟加载

+ property

  + 标name属性用于指定要设置的属性名。对应于Java类中的属性名称
  + ref属性用于指定属性的引用值，对应于Spring容器中另一个bean的ID

  

  

## bean的实例化

+ 构造方法：调用无参的构造方法。

+ 静态工厂：创建工厂类UserDaoFactory，添加静态方法getUserDao，在bean中添加factory-method属性

  ```xml
  <bean id="userDao" class="UserDaoFactory" factory-method="getUserDao"/>
  ```

  

+ 实例工厂

  + 普通模式：创建工厂类UserDaoFactory的bean，使用factory-method和factory-bean属性指定工厂类中的实例方法和工厂bean的名称。

  ```xml
  <bean id="userFactory" class="com.xx.xxxx.xxxx.UserDaoFactory"/>
  <bean id="userDao" factory-method="getUserDao" factory-bean="userFactory"/>
  ```

  + 简化模式：创建继承于FactoryBean<T>接口的类FactoryBean，在调用类bean

  ```xml
  <bean id="userDao" class="com.xx.xxx.UserDaoFactoryBean"/>
  ```

  



## 生命周期

bean从创建到销毁的整体过程

### 配置

1. 在bean中使用init-method属性和destory-method属性，

   ```
   <bean id="userDao" class="com.xx.xxx.UserServiceImpl" init-method="init" destroy-method="destory"/>
   ```

2. 让类实现InitializingBean，DisposableBean接口



### 关闭容器

1. 通过ConfigurableApplicationContext中的close方法关闭容器
2. 通过ConfigurableApplicationContext中的registerShutdownHook方法关闭容器



## 注入方法

+ 构造器注入要求在实例化Bean时就提供所有必需的依赖一旦Bean实例创建完成，其依赖关系就固定下来，不能再修改。**强制依赖使用构造器注入**
+ Setter注入允许在实例化后动态设置依赖。在创建Bean实例后，通过调用Bean的Setter方法来注入所需的依赖。**可选依赖使用setter注入**
+ Spring框架倡导使用构造器，但实际开发过程中要根据实际情况进行选择。
+ 自己开发的模块推荐使用setter注入



### setter注入

**注入引用数据类型：**

```Java
public class BookServiceImpl implements BookService{
	private BookDao bookDao;
	public void setBookDao(BookDao bookDao){
		this.bookDao = bookDao;
	}
}
```

```xml
<bean id="bookService" class="com.xx.service.impl.BookServiceImpl">
    <!---name是BookServiceImpl中的对象名---->
	<property name="bookDao" ref="bookDao"/>
</bean>
<bean id="bookDao" class="com.xx.dao.impl.BookDaoImpl"/>
```

**注入基本数据类型:**

```Java
public class BookServiceImpl implements BookService{
	private String databaseName;
	public void setDatabaseName(String name){
		this.databaseName = name;
	}
}
```

```xml
<bean id="bookService" class="com.xx.service.impl.BookServiceImpl">
	<property name="databaseName" value="mysql"/>
</bean>
```



**注入集合：**

```xml
<!------array---------->
<property name="array名">
	<array>
    	<value></value>
        <value></value>
    </array>
</property>

<!------list---------->
<property name="list名">
	<list>
    	<value></value>
        <value></value>
    </list>
</property>

<!------set---------->
<property name="set名">
	<set>
    	<value></value>
        <value></value>
    </set>
</property>

<!------map---------->
<property name="map名">
	<map>
    	<entry key="1" value="a"/>
        <entry key="2" value="b"/>
    </map>
</property>

<!------properties---------->
<property name="properties名">
	<props>
        <prop key="1">a</prop>
        <prop key="2">b</prop>
    </props>
</property>
```



### 构造器注入

**注入引用数据类型：**

```Java
public class BookServiceImpl implements BookService{
	private BookDao bookDao;
	public BookServiceImpl(BookDao bookDao){
		this.bookDao = bookDao;
	}
}
```

```xml
<bean id="bookService" class="com.xx.service.impl.BookServiceImpl">
    <!---name是构造器中形参的名称---->
	<constructor-arg name="bookDao" ref="bookDao">
</bean>
<bean id="bookDao" class="com.xx.dao.impl.BookDaoImpl"/>
```

**注入基本数据类型:**

```Java
public class BookServiceImpl implements BookService{
	private String databaseName;
	public BookServiceImpl(String databaseName){
		this.databaseName = databaseName;
	}
}
```

```xml
<bean id="bookService" class="com.xx.service.impl.BookServiceImpl">
    <!---name是构造器中形参的名称---->
	<constructor-arg name="databaseName" value="mysql">
</bean>
```



## 自动装配

+ 通过autowire属性指定装配方式
+ 用于引用类型依赖注入，不能对简单类型进行操作
+ 需要被装配的类需要实现set方法
+ 使用byType装配时，必须保障容器中相同类型的bean唯一
+ 使用byName装配时，必须保障容器中有指定名称的bean
+ 自动装配优先级低于setter注入和构造器注入，同时出现时则自动装配配置失效

```xml
<bean id="bookService" class="com.spring.service.impl.BookServiceImpl" autowire="byType"/>
```



## bean的获取方式

+ 使用名称获取

  ```java
  BookDao bookDao = (BookDao)ctx.getBean("bookDao");
  ```

+ 使用名称与类型获取

  ```java
  BookDao bookDao = ctx.getBean("bookDao",BookDao.class);
  ```

+ 使用bean类型获取

  ```java
  BookDao bookDao = ctx.getBean(BookDao.class);
  ```



## 加载配置文件

```Java
//加载类路径下的配置文件
ApplicationContext ctx = new ClassPathXmlApplicationContext(application.xml);

//从文件系统下加载配置文件
ApplicationContext ctx = new FileSystemXmlApplicationContext(D:\\xx\\xxx\\application.xml)
```



+ BeanFactory是IoC容器的顶层接口，初始化BeanFactory对象时，加载的bean延迟加载

+ ApplicationContext接口是Spring容器的核心接口，提供基础的bean操作相关方法，在其初始化时bean会立刻加载

  



# 注解开发

Spring3.0开启了纯注解开发模式，使用Java类替代了配置文件。在配置类中使用：

+ @Configuration：设定当前类为配置类
+ @ComponentScan：用于设定扫描路径
+ @PropertySource：用于指定属性文件，不支持通配符*



## 定义bean

+ @Component：标识一个类作为Spring管理的组件

  - @Controller：标识控制器层组件
  - @Service：标识服务层组件
  - @Repository：标识数据访问层组件

  



## 生命周期

@Scope：定义bean作用范围

@PostConstruct：定义初始化方法

@PreDestory：定义销毁方法



## 注入方法

@Autowired：注入引用类型

@Qualifier：用于指定注入的bean的名称

@Value：注入简单类型。（@Value（${xxxx}）)



## 加载配置类文件

```
ApplicationContext ctx = new AnnotationConfigApplicationContext(配置类名.class)
```



## 第三方bean管理

1. 创建一个配置类，在该类中定义一个方法用于获取需要管理的对象，并用@Bean注解表明方法的返回值是一个bean
2. 在Spring配置类中使用@Import注解导入创建的配置类



**简单类型依赖注入：**使用@Value注解进行注入

**引用类型依赖注入**：为bean中定义的方法设置形参即可，容器会根据类型自动装配对象



# AOP

## 基本概念

AOP：Aspect Oriented Programming，面向切面编程，是一种满足**无侵入式编程**理念的一种编程范式。它可以在**不影响原始设计**的基础上对其进行**功能增强**。

+ 连接点：能够被拦截并执行切面逻辑的具体位置
+ 切入点：指定连接点的表达式
+ 通知：共性功能
+ 切面：描述通知和切入点之间的关系





## 入门案例

1. 在pom.xml中导入依赖
2. 制作连接点方法
3. 制作共性功能（创建通知类）
4. 定义切入点

**注意点：**

+ 在配置类中需要使用@EnableAspectJAutoProxy注解开启对@Aspect切面的支持
+ 在通知类中使用@Aspect将类标注为切面





## AOP工作流程

1. Spring容器启动

2. 读取所有切面配置中的切入点

3. 初始化bean，判定bean对应的类的方法是否匹配到某个切入点

   + 匹配失败：创建对象。获取bean，调用方法并执行
   + 匹配成功：创建原始对象（目标对象）的**代理对象**。根据代理对象的运行模式运行原始方法和增强的内容

   

<font color="red" size=5>**SpringAOP的本质：代理模式**</font>



## 切入点表达式

标准格式：动作关键字（访问修饰符  返回值  包名.类/接口名.方法名（参数）异常名）

通配符：

+ "  *  "：单个独立的任意符号，可以独立出现，也可以作为前缀或者后缀的匹配符出现
+ "  ..  "：多个连续的任意符号，可以独立出现，常用于简化包名与参数的书写
+ "  +  "：专用于匹配子类类型



## 通知类型

+ @Before：前置通知
+ @After：后置通知
+ @Around：环绕通知。
  + 使用ProceedingJoinPoint的对象的proceed方法来运行原始方法
  + 对原始方法的调用可以不接收返回值，若接收则通知方法的返回值必须设定为Object类型
  + 由于无法预知原始方法运行后是否会抛出异常，因此环绕通知方法必须抛出Throwable对象
+ @AfterReturning：返回后通知
+ @AfterThrowing：抛出异常后通知



## AOP通知获取数据

+ JoinPoint

+ ProceedingJoinPoint

+ returning

  ```Java
  @AfterReturning(value="pt()",returning="ret")
  public void afterReturning(JoinPoint jp, String ret){
  	//有多个形参的时候，JoinPoint必须为第一个
  }
  ```

+ throwing

  ```Java
  @AfterThrowing(value="pt()",throwing="t")
  public void afterThrowing(Throwable t){
  
  }
  ```



# Spring事务

Spring事务用于在数据层或业务层保障一系列的数据库操作同成功同失败

## 事务角色

事务管理员：发起事务方，在Spring中通常指代业务层开启事务的方法

事务协调员：加入事务方，在Spring中通常指代数据层方法，也可以是业务层方法



## 事务相关配置

+ readOnly：设置是否为可读事务
+ timeout：设置事务超时时间
+ rollbackFor：设置事务回滚异常（Class）
+ rollbackForClassName：设置事务回滚异常（String）
+ noRollbackFor：设置事务不回滚异常（Class）
+ noRollbackForClassName：设置事务不回滚异常（String）
+ propagation：设置事务传播行为



## 事务传播行为

+ REQUIRED：有则加入，无则新建
+ REQUIRES_NEW：有则新建，无则新建
+ SUPPORTS：有则加入，无亦不新建
+ NOT_SUPPORTED：有不加入，无亦不加入
+ MANDATORY：有则加入，无则报错
+ NEVER：有则报错，无则正常
+ NESTED：设置savePoint，一旦事务回滚，事务将回滚到savePoint处，交由客户端响应提交、回滚。



# SpringMVC

Spring MVC（Model-View-Controller）是一种基于Java实现MVC模型的轻量级Web框架

优点：

+ 使用简单，开发便捷（相比于Servlet）
+ 灵活性强



## 入门案例

1. 导入相关依赖

2. 创建SpringMVC控制器类（等同于Servlet功能）

   ```Java
   @Controller
   public class UserController{
   	@RequestMapping("/xxx")
   	@ResponseBody
   	public String save(){
   		System.out.println("xxx");
   	}
   }
   ```

3. 初始化SpringMVC环境，设定SpringMVC加载对应的bean

4. 初始化Servlet容器，加载SpringMVC环境，并设置SpringMVC技术处理的请求。该类必须继承AbstractDispatcherServletInitializer

   ```Java
   public class ServletContainersInitConfig extends AbstractDispatcherServletInitializer {
   
       //加载SpringMVC容器配置
       @Override
       protected WebApplicationContext createServletApplicationContext() {
           AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
           ctx.register(SpringMvcConfig.class);
           return ctx;
       }
   
       //设置哪些请求归属SpringMVC处理
       @Override
       protected String[] getServletMappings() {
           return new String[]{"/"};
       }
   
       //加载Spring容器配置
       @Override
       protected WebApplicationContext createRootApplicationContext() {
           return null;
       }
   }
   ```

   

## 加载控制

SpringMVC加载的bean对应的包均在com.xx.controller包内，如何避免让Spring加载到SpringMVC控制的bean？

+ 方式一：Spring加载的bean扫描范围设置为com.xx，并且排除controller包内的bean

  ```Java
  @ComponentScan(value="com.xx",excludeFilters= @ComponentScan.Filter(
  	type = FilterType.ANNOTATION,
  	classes = Controller.class
  ))
  ```

+ 方式二：Spring加载的bean设定扫描范围为精准范围，例如com.xx.service，com.xx.dao等

  ```Java
  @ComponentScan({"com.xx.service","com.xx.dao"})
  ```

+ 方式三：不区分Spring和SpringMVC的环境，加载到同一个环境中

  

  **简化开发：**

  ```Java
  public class ServeletContainersInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer{
  	
  }
  ```

  

## 请求参数传递

```
//为web容器添加过滤器并指定字符集
protect Filter[] getServletFilters() {
	CharacterEncodingFilter filter = new CharacterEncodingFilter();
	filter.setEncoding("utf-8");
	return new Filter[](filter);
}
```

+ **普通参数**：

  + 地址参数名与形参变量名相同，可以直接接收
  + 地址参数名与形参变量名不相同，使用@RequestParam将请求参数绑定到方法参数上

  ```Java
  //将请求参数name绑定给形参userName
  public String commonParamDifferentName(@RequestParam("name") String userName, int age){
  	return "hello";
  }
  ```

+ **POJO参数**：请求参数名和实体类中的属性名一样时，会实现自动绑定

  ```Java
  //请求参数为username，age；实体类User中的属性名为username，age
  public String pojoParam(User user){
  	return "hello";
      
  }
  ```

+ **嵌套POJO参数**：请求参数按照对象层次结构关系提供参数

+ **数组参数**：请求参数名与形参对象属性名相同且请求参数为多个

  ```Java
  public String arrayParam(String[] likes){
  	return "hello";
  }
  ```

+ **集合参数**：请求参数名与形参集合对象名相同且请求参数为多个，用@RequestParam绑定参数关系

+ **JSON格式参数**：

  - 在pom.xml中添加相关依赖

  - 在核心配置中添加@EnableWebMvc
  - 添加@RequestBody

+ **日期类型参数**：使用@DateTimeFormat(pattern="yyyy-MM-dd")指定日期格式

  

## 响应

**@ResponseBody**：当一个方法被@ResponseBody注解修饰时，Spring MVC会将方法的返回值序列化为JSON、XML或其他格式，并写入HTTP响应体中，返回给客户端。当使用@ResponseBody修饰方法时，Spring MVC会根据请求的Accept头部信息选择合适的HttpMessageConverter来将方法的返回值序列化为对应的HTTP响应数据格式



**HttpMessageConverter**：Spring框架中用于处理HTTP请求和响应消息转换的核心接口之一，定义了将HTTP请求和响应中的数据转换为Java对象（反序列化）以及将Java对象转换为HTTP响应数据（序列化）的通用方法。



##  REST风格

REST（Representational State Transfer）：表现形式状态转换。是一种软件架构风格，而不是规范



**传统风格资源描述形式**：

 	1. http://localhost/user/getById?id=1

 2. http://localhost/user/saveUser

    

**REST风格描述形式**：

 	1. http://localhost/user/1

	2. http://localhost/user



**优点：**

+ 隐藏资源的访问行为，无法通过地址得知对资源进行了什么操作
+ 简化了书写



### 参数获取

- @RequestParam：用于从请求中获取查询参数，通常通过?key=value形式传递在URL中。
- @RequestBody：用于从请求体中提取数据
- @PathVariable：用于从URL路径中获取参数，通常用于RESTful风格的API，参数直接嵌入在URL路径中。

```
@RequestMapping(value="/users/{id},method = RequestMethod.DELETE")
@ResponseBody
public String delete(@PathVariable Integer id){
    System.out.println("user delete..." +id);
    return "{'module':'user delete'}";
}
```



**应用：**

- 发送请求参数超过一个时，以JSON格式为主
- 如果发送非JSON格式数据，选用@RequestParam接收请求参数
- 采用RESTful开发，当参数较少时使用@PathVariable



### 简化注解

- @RestController：@Controller + @ResponseBody。使用 @RestController注解的控制器类会自动将方法的返回值序列化为 JSON 或 XML 格式的数据，并将其直接写入 HTTP 响应体中。
- @PostMapping：等价于@RequestMapping（method=RequestMethod.POST）
- @DeleteMapping：等价于@RequestMapping（method=RequestMethod.DELETE）
- @PutMapping：等价于@RequestMapping（method=RequestMethod.PUT）
- @GetMapping：等价于@RequestMapping（method=RequestMethod.GET）