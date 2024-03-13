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

#### **基本查询**

查询多个字段：select 字段1[as 别名1]，字段2[as 别名2]，字段3[as 别名3]...from 表名；

去除重复记录查询：select distinct 字段列表 from 表名；



#### **条件查询**

select 字段列表 from 表名 where 条件列表;

![image-20231103161253266](image\image-20231103161253266.png)



#### **聚合函数**

所有的null值是不参与聚合函数运算的

![image-20231103163355028](image\image-20231103163355028.png)

select 聚合函数（字段列表） from 表名;



#### **分组查询**

```SQL
select 字段列表 from 表名 [where 条件] group by 分组字段名 [having 分组后过滤条件];
```

执行顺序：where > 聚合函数 > having

注意：分组之后，查询的字段一般为聚合函数字段和分组字段，查询其他字段无任何意义



#### **排序查询**

```SQL
select 字段列表 from 表名 order by 字段1 排序方式，字段2 排序方式;
```

排序方式：ASC（升序，默认值），DESC（降序）

如果有多个字段，只有当前面的字段相同时，才会根据第二个字段排序



#### **分页查询**

```SQL
select 字段列表 from 表名 limit 起始索引,查询记录数;
```

注意：

+ 起始索引从0开始
+ 在MySQL中分页查询是用limit的，其他数据库中不一定
+ 如果查询的是第一页数据，起始索引可以省略



#### DQL执行顺序

1. from：确认要查询的数据表
2. where：筛选满足条件的行
3. group by：分组
4. having：分组后过滤
5. select：选择要返回的列
6. distinct：去重
7. order by：排序
8. limit：限制行数



### DCL：数据控制语言

用来管理数据库用户，控制数据库的访问权限

#### 管理用户用户

```sql
/*查询用户*/
use mysql
select * from user

/*创建用户*/
/*主机名如果用%通配符表示的话，创建的用户可以在任意主机上访问该数据库*/
create user '用户名'@'主机名' identified by '密码'

/*修改用户密码*/
alter user '用户名'@'主机名' identified with mysql_native_password by '新密码'

/*删除用户*/
drop user '用户名'@'主机名';
```



#### 权限控制

![image-20240109200915532](image\image-20240109200915532.png)

```SQL
/*查询权限*/
show grants for '用户名'@'主机名'

/*授予权限*/
grant 权限列表 on 数据库名.表名 to '用户名'@'主机名'

/*撤销权限*/
revoke 权限列表 on 数据库名.表名 to '用户名'@'主机名'
```



## 函数

**字符串函数**

![image-20240109204003089](image\image-20240109204003089.png)



**数值函数**

![image-20240109204652212](image\image-20240109204652212.png)



**日期函数**

![image-20240109205121982](image\image-20240109205121982.png)



**流程函数**

![image-20240110153633953](image\image-20240110153633953.png)



## 约束

作用于表中字段上的规则

+ 非空约束：not null
+ 唯一约束：unique
+ 主键约束：primary key
+ 默认约束：default
+ 检查约束（8.0.16版本之后）：check
+ 外键约束：foreign key。让两张表之间建立连接，从而保证数据的一致性和完整性。有外键的是子表，被关联的是父表

```sql
-- 添加外键
alter table emp add constraint fk_emp_dept_id foreign key (dept_id) references dept(id);


--删除外键
alter table emp drop foreign key fk_emp_dept_id
```



**外键约束的 删除、更新行为：**

+ no action ：父表删除/更新记录时，首先检查记录是否对应外键，如果有就不允许删除/更新 （与restrict一致）（默认行为）
+ restrict ：父表删除/更新记录时，首先检查记录是否对应外键，如果有就不允许删除/更新 （与no action一致）（默认行为）
+ cascade：父表删除/更新记录时，首先检查记录是否对应外键，如果有就对子表内容也进行更新
+ set null ： 父表删除/更新记录时，首先检查记录是否对应外键，如果有就将子表中该外键设置为null
+ set default ：父表有变更时，子表将外键列设置成一个默认值

```sql
alter table emp add constraint fk_emp_dept_id foreign key (dept_id) references dept(id) on update cascade
on delete cascade;
```



##多表查询

### 多表关系

**一对一：**

在任意一方加入外键，关联对方的主键，并将外键设置为unique

**一对多：**

在" 多 "的一方创建外键，指向” 一 “的一方的主键

**多对多：**

建立一张中间表，中间表中至少包含两个外键，分别关联双方的主键





### 多表查询

**内连接：**

```sql
--隐式内连接
--select 字段列表 from 表1,表2 where 条件
select * from emp, dept where emp.dept_id = dept.id


--显式内连接
--select 字段列表 from 表1 [inner] join 表2 on 连接条件
select * from emp inner joiin dept on emp.dept_id = dept.id;
```

**外连接**

```sql
--左外连接
--包含表1全部的数据，以及表1和表2交集的数据
select e.*, d.name from emp e left out join dept d on e.dept_id = d.id


--右外连接
--包含表2全部的数据，以及表1和表2交集的数据
select e.*, d.name from emp e right out join dept d on e.dept_id = d.id
```





**子连接**







## 事务

事务是一组操作的集合，是一个不可分割的工作单位。MySQL的事务默认是自动提交的，也就是说当执行一条DML语句时，MySQL会立刻隐式地提交事务



### 四大特性（ACID）