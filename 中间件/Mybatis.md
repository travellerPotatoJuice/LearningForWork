# Mybatis入门

## Mybatis使用步骤

1. 创建SpringBoot工程，数据库表，实体类

2. 引入Mybatis相关依赖，配置Mybatis

3. 使用（@Select，@Delete）等注解，将SQL语句写入注解中。使用@Mapper注解，让程序自动生成接口对应的Bean对象

   

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

@Insert

如果有多个参数，可以使用实体类将多个参数进行封装，在填写占位符时要使用实体类里的参数名而不是数据库中的字段名



**主键返回**

useGeneratedKeys = true：指明需要获取自动生成的主键值

keyProperty：表示获取的主键会被封装到id属性中

@Options(useGeneratedKeys=true，keyProperty = "id")



## 更新

@Update



## 查询

@Select

**myBatis中的数据封装：**

+ 如果实体类的属性名与数据库中的字段名一致，myBatis会自动封装
+ 如果不一致，则不能自动封装



解决方法：

+ 在SQL语句中给字段起别名，别名与实体类的属性名保持一致

+ 通过@Results，@Result注解手动映射封装

  + ```
    @Results({
    	@Result(column="数据库字段",property="实体类属性名")
    })
    ```

+ 开启myBatis的驼峰命名自动映射开关。在application.properties里修改(数据库里的字段名必须用下划线分隔，实体类中的名称必须用驼峰命名)

  + ```
    mybatis.configuration.map-underscore-tp-camel-case=true
    ```

    

## 条件查询

模糊匹配的时候，不能使用'%#{name}%'这种写法，解决方法：

+ 直接用'%${name}%'（性能低，不安全，存在SQL注入的问题）
+ 使用concat函数对字符串进行拼接



# XML映射文件

如果需要完成的功能比较简单，可以直接用SQL注解实现。如果功能比较复杂，则考虑用XML映射文件实现。

使用XML映射文件需要遵循以下规范：

+ XML文件的名称和Mapper接口名称一致（包名和文件名）
+ XML文件namespace属性的值应该与对应的Mapper全限定名一致（从包名一直到类名）
+ XML文件中SQL语句的id与MApper接口中的方法名一致

## 动态SQL

随着用户的输入或外部条件的变化而变化的SQL语句

+ <if>：用于判断条件是否成立，使用test属性进行条件判断
+ <where>：在子元素有内容的情况下会插入where字句，并且会自动去除子句开头的and或or
+ <set>：去除字段后多余的逗号
+ <foreach>：
  + collection：遍历的集合
  + item：遍历出的元素
  + separator：分隔符
  + open：遍历前拼接的SQL片段
  + close：遍历后拼接的SQL片段
+ <sql>：用于抽取sql片段
+ <include>：引用被抽取的sql片段

```xml
<set>   
    <if test="name!=null">
    	name like concat('%',#{name},'%')
	</if>
    <if test="gender!=null">
    	gender = #{gender}
	</if>
</set>
```

