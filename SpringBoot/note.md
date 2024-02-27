# SpringBoot 简介

Spring Boot：用于构建项目，其设计目的是用来简化Spring应用的初始搭建以及开发过程

Spring Cloud：用于配置

Spring Cloud Data Flow：用于进行数据连接



**Spring的缺点：**

+ 配置繁琐
+ 依赖繁琐



**Spring Boot的功能：**

+ 起步依赖：将具备某种功能的坐标打包到一起，并提供一些默认的功能
  + starter：SpringBoot中常见项目名称，定义了当前项目使用的所有项目坐标，以达到减少依赖配置的目的
  + parent：所有SpringBoot项目要继承的项目，定义了若干个坐标版本号，以达到减少依赖冲突的目的
+ 自动配置：简化了Spring的配置过程
+ 辅助功能：提供了一些大型项目中常见的非功能特性



| 类/配置文件            | Spring   | SpringBoot        |
| ---------------------- | -------- | ----------------- |
| pom文件中的坐标        | 手工添加 | 勾选添加/手工添加 |
| web配置类              | 手工制作 | 无                |
| Spring/SpringMVC配置类 | 手工制作 | 无                |
| 控制器                 | 手工制作 | 手工制作          |



## SpringBoot快速启动

1. 对SpringBoot项目打包（执行Maven构建指令package）打包时需要确认是否具有SpringBoot对应的maven插件

   ````xml
   <build>
       <plugs>
           <plug>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-maven-plugin</artifactId>
           </plug>
       </plugs>
   </build>
   ````

   

2. 执行启动指令java -jar springboot.jar。



## SpringBoot基础配置

### 配置文件格式

+ application.properties

  ```properties
  server.port=80
  ```

  

+ application.yaml/application.yml(.yml是.yaml的一种简写形式)

  ```yaml
  server:
  	port: 80
  	subject: 
  		- Java
  		- C++
  ```

  

+ application.xml

  ```xml
  <server>
  
  	<port>8080</port>
  
  </server>
  ```

  

**优先级从高到低：**

+ application.properties
+ application.yml
+ application.yaml