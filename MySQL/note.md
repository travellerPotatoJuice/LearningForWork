# 基础

## 启动与停止

1. msconfig。在服务里找到mySQL，选择启动/停止
2. 启动：net start mysql80      停止：net stop mysql80



连接到mysql：cmd。 mysql -u root -p



## SQL

SQL分类：

![image-20231102153329120](image\image-20231102153329120.png)

----------------------------------------

### DDL：数据定义语言

#### 操作数据库

查询所有数据库：show databases;

查询当前数据库：select database();

创建：create database [if not exists] 数据库名 [default charset  字符集] [collate 排序规则]；

删除：drop database [if exists] 数据库名；

使用：use 数据库名;



#### 操作表结构

查询当前数据库所有表：show tables;

查询表结构：desc 表名；

查询指定表的建表语句：show create table 表名；

创建：

​		create table 表名（

​					字段1 字段1类型[comment 字段1注释],

​					字段2 字段2类型[comment 字段2注释],

​					字段3 字段3类型[comment 字段3注释]

​	 	）[表注释]；

​	tip:建表的时候需要留意数据是否是定长，数据的类型，数据的长度，数据是否有符号等，根据具体要求选择数据类型。

##### 数据类型

日期类型

1. date：YYYY-MM-DD
2. time：HH：MM：SS
3. year：YYYY
4. datatime：有效时间到9999年
5. timestamp：有效时间到2038年

##### 修改

添加字段：alter table 表名 add 字段名 类型（长度）\[comment 注释\][约束];

修改数据类型: alter table 表名 modify 字段名 新数据类型（长度）;

修改字段名和字段类型：alter table 表名 change 旧字段名 新字段名 类型（长度）\[comment 注释\][约束];

删除字段： alter table 表名 drop 字段名；

修改表名：alter table 表名 rename to 新表名；

##### 删除

删除表：drop table [if exists] 表名；

删除指定表，并重新创建该表：truncate table 表名；（删除表数据，但保留表结构）

-----------------------------------------------------------------------------------------------------------------------------------------------------------------

### DML：数据操作语言

 #### 添加数据

给指定字段添加数据： insert into 表名 （字段名1，字段名2，...）values (值1，值2，...)

给全部字段添加数据：insert into 表名 values（值1，值2，...）

批量添加数据：insert into 表名（字段名1，字段名2，...）values （值1，值2，...）（值1，值2，...）;

​						   insert into 表名   values （值1，值2，...）（值1，值2，...）



+ 插入数据时，指定的字段顺序需要与值的顺序一一对应

+ 字符串和日期型数据包含在引号中

+ 插入数据应该在字段要求范围之内



#### 修改数据

修改数据：update 表名 set 字段名=值1, 字段名2=值2，...[where 条件]；

删除数据：delete from 表名 [where 条件]; 

​	delete 语句的条件可以有，也可以没有，如果没有条件，则会删除整张表的所有数据

​	delete语句不能删除某一个字段的值

--------------------

### DQL：数据查询语言

**基本查询**

查询多个字段：select 字段1[as 别名1]，字段2[as 别名2]，字段3[as 别名3]...from 表名；

去除重复记录查询：select distinct 字段列表 from 表名；



**条件查询**

select 字段列表 from 表名 where 条件列表;

![image-20231103161253266](image\image-20231103161253266.png)



**聚合函数**：所有的null值是不参与聚合函数运算的

![image-20231103163355028](image\image-20231103163355028.png)

select 聚合函数（字段列表） from 表名;



**分组查询**

```SQL
select 字段列表 from 表名 [where 条件] group by 分组字段名 [having 分组后过滤条件];
```

执行顺序：where > 聚合函数 > having

注意：分组之后，查询的字段一般为聚合函数字段和分组字段，查询其他字段无任何意义



**排序查询**

```SQL
select 字段列表 from 表名 order by 字段1 排序方式，字段2 排序方式;
```

排序方式：ASC（升序，默认值），DESC（降序）

如果有多个字段，只有当前面的字段相同时，才会根据第二个字段排序



**分页查询**

```SQL
select 字段列表 from 表名 limit 起始索引,查询记录数;
```

注意：

+ 起始索引从0开始
+ 在MySQL中分页查询是用limit的，其他数据库中不一定
+ 如果查询的是第一页数据，起始索引可以省略