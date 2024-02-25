# select

+ id：在命名空间中的唯一标识符
+ parameterType：用于指定传递给SQL语句的参数类型。默认值为unset（Mybatis可以自动解析）
+ resultType：SQL语句返回结果的类型，适合查询结果只有一个对象的情况。如果返回的是集合，则设置为集合内元素的类型。
+ resultMap：对外部 resultMap 的命名引用，适合查询结果有多个对象的情况。
+ flushCache：清空本地缓存和二级缓存，默认值为false
+ useCache：将本条语句返回结果进行二级缓存，默认值为true
+ timeout：驱动程序等待数据库返回请求结果的秒数，未及时获得结果则抛出异常
+ fetchSize：尝试让驱动每次批量返回的结果行数值
+ statementType：STATEMENT，PREPARED 或 CALLABLE中的一个。设置是否进行预编译。
+ resultSetType：FORWARD_ONLY，SCROLL_SENSITIVE, SCROLL_INSENSITIVE 或 DEFAULT中的一个。用于设置结果集回滚方式以及对数据库更新的敏感控制。
+ databaseId：数据库厂商标识
+ resultOrdered：
+ resultSets：列出语句执行后返回的结果集并赋予每个结果集一个名称，多个名称之间以逗号分隔



# insert、update、delete

+ id：在命名空间中的唯一标识符
+ parameterType：用于指定传递给SQL语句的参数类型。默认值为unset（Mybatis可以自动解析）
+ flushCache：清空本地缓存和二级缓存，默认值为false
+ timeout：驱动程序等待数据库返回请求结果的秒数，未及时获得结果则抛出异常
+ statementType：STATEMENT，PREPARED 或 CALLABLE中的一个。设置是否进行预编译。
+ useGeneratedKeys：（仅适用于insert和update）是否使用自动生成的主键
+ keyProperty：（仅适用于insert和update）指定能唯一识别对象的属性
+ keyColumn：（仅适用于insert和update）设置生成键值在表中的列名
+ databaseId：数据库厂商标识



# selectKey

+ keyProperty：
+ keyColumn：
+ resultType：结果类型
+ order：BEFORE或AFTER。如果是BEFORE，它会首先生成主键，设置keyProperty再执行插入语句。如果是AFTER，会先执行插入语句再执行selectKey中的语句。
+ statementType：STATEMENT，PREPARED 或 CALLABLE中的一个。设置是否进行预编译。