# 快速入门

## 使用步骤

1. 引入mybatis依赖

```xml
<dependency>
     <groupId>com.baomidou</groupId>
     <artifactId>mybatis-plus-boot-starter</artifactId>
     <version>3.5.3.1</version>
</dependency>
```

2. 让自定义的Mapper继承MybatisPlus提供的BaseMapper接口，BaseMapper的泛型需要指定为需要操作的实体类的类型





## 常用注解

Mybatis通过扫描实体类，并基于反射获取实体类信息作为数据库表信息

+ 类名驼峰转下划线作为表名
+ 名为id的字段作为主键
+ 变量名驼峰转下划线作为字段名

如果实体类不符合上述约定，则需要使用注解来自定义表名、主键、字段名



**注解：**

+ @TableName：指定表名。
  + value：指定真实表名
+ @TableId：指定主键。
  + value：指定真实主键名
  + type：指定主键类型（/策略）。AUTO（自增长）、INPUT（通过set自行输入）、ASSIGN_ID（Mybatis_plus通过雪花算法生成Id）
+ @TableField：指定字段。
  + value：指定真实字段名
    + 成员变量名以is开头，并且是布尔值时，需要用TableField标注
    + 成员变量名和数据库关键字冲突时用"  'xxx'  " 作为转义字符   
  + exist：说明该属性不是数据库字段名
    + 成员变量不是数据库字段时使用该属性



# 核心功能

## 条件构造器

在MyBatis-Plus中，Wrapper是一个用于构建SQL查询条件的接口，具有以下实现类：

+ QueryWrapper：用于构建查询条件
+ UpdateWrapper：用于构建更新条件
+ LambdaQueryWrapper：基于Lambda表达式构建查询条件
+ LambdaUpdateWrapper：基于Lambda表达式构建更新条件



## 自定义SQL

 



## Service接口

在mybatis-plus框架中，Service接口为实体类提供了一系列CRUD操作的方法。

自定义一个自己的接口，继承自IService<T>接口；创建一个自己的实现类，继承自UserServiceImpl<M,T>实现类