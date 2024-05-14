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

+ @TableName：表名
  + value：指定真实表名
  + schema：指定schema名
  + excludeProperty：需要排除的属性名
+ @TableId：指定主键。
  + value：指定真实主键名
  + type：指定主键类型（/策略）。AUTO（自增长）、INPUT（通过set自行输入）、ASSIGN_ID（Mybatis_plus通过雪花算法生成Id）
+ @TableField：指定字段。
  + value：指定真实字段名
  + exist：是否为数据库字段名
+ @TableLogic：表字段逻辑处理注解
  + value：逻辑未删除值
  + delval：逻辑删除值



# 核心功能

## 代码生成器

mybatias-plus从3.0.3之后移除了代码生成器和模板引擎之间的默认依赖，需要手动添加相关依赖





## 条件构造器

在MyBatis-Plus中，Wrapper是一个用于构建SQL查询条件的接口，具有以下实现类：

+ QueryWrapper：用于构建查询条件
+ UpdateWrapper：用于构建更新条件
+ LambdaQueryWrapper：基于Lambda表达式构建查询条件
+ LambdaUpdateWrapper：基于Lambda表达式构建更新条件



## 自定义SQL

 



## Service接口

在mybatis-plus框架中，Service接口为实体类提供了一系列CRUD操作的方法。如果遇到简单的业务功能，可以直接调用IService提供的功能，如果遇到复杂的业务功能再在自定义的Service接口中定义方法。

实现方式：自定义一个自己的接口，继承自IService<T>接口；创建一个自己的实现类，继承自UserServiceImpl<M,T>实现类