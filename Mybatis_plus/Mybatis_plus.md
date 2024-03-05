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



# 条件构造器

