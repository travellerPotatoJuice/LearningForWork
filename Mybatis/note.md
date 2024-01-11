# Mybatis入门

## Mybatis使用步骤

1. 创建SpringBoot工程，数据库表，实体类

2. 引入Mybatis相关依赖，配置Mybatis

3. 使用@Select注解，将SQL语句写入Select中。使用@Mapper注解，让程序自动生成接口对应的Bean对象

   

## JDBC

Java Database Connectivity：使用Java语言操作**关系型数据库**的一套API

**传统JDBC程序**

1. 注册驱动
2. 获取连接对象
3. 获取执行SQL的对象Statement，执行SQL，返回结果
4. 解析结果数据
5. 释放资源



**MyBatis**

+ 将数据库连接的要素放在了配置文件中
+ 将查询结果的解析过程自动化
+ 使用数据库连接池避免频繁地建立连接和释放连接



## 数据库连接池

数据库连接池是Mybatis中负责分配和管理数据库连接的容器

**传统实现方式**：每执行一次SQL语句就要创建一次连接，需要频繁建立和释放连接

**基于数据库连接池地实现方式**：允许应用程序重复使用现有的数据库连接，无需重新建立；释放空闲时间超过最大空闲时间的连接。

切换数据库连接池：引入对应数据库连接池的依赖



## Lombok

Lombok是一个Java库，它通过注解的方式简化了Java的编写，可以自动生成一些常见的Java代码（get，set，构造函数，equals，hashCode等）。使用时需要引入lombok的依赖



+ @Getter/@Setter：生成get/set方法
+ @ToString：生成toString方法
+ @EqualsAndHashCode：生成equals方法和hashCode方法
+ @Data（@Getter+@Setter+@ToString+@EqualsAndHashCode）
+ @NoArgsConstructor：无参构造器
+ @AllArgsConstructor：所有参数（除static修饰之外）的构造器





# Mybatis基础操作

## 删除

@Delete



**预编译SQL**：SQL被发送给MySQL后，MySQL会先对SQL进行语法检查，再对SQL进行优化，最后进行编译。以上三个过程会被缓存起来，当新的SQL语句到达时，如果在缓存中已存在就可以无需进行以上三个操作。这种方法更加高效（减少编译次数），也更加安全（可以防止SQL注入攻击）



**占位符：**

+ #{}：执行时会被替换为？，生成预编译SQL。用于参数传递
+ ${}：直接将参数拼接在SQL语句中，存在SQL注入问题。用于对表名，列名进行动态设置





## 增加

