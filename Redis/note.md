# Redis概览

Redis（Remote Dictionary Server），远程词典服务器，是一种NoSQL（Not only SQL）、基于内存的数据库

**特征：**

+ 数据结构丰富：支持字符串、哈希表、集合等数据结构
+ 高性能：将数据存储在内存中，实现了多路复用，读写速度非常快
+ 高扩展性：支持集群模式（主从集群和分片集群），可以通过添加节点来水平扩展系统的容量和性能
+ 持久化：定期将内存中的数据保存到磁盘，确保数据的安全性
+ 多语言支持：方便开发者在各种编程语言中使用



# Redis命令

## Redis数据结构

Redis是一个key-value的数据库，key一般是String类型，value的值多种多样，可以是String，Hash，List，Set，SortedSet等



## 通用命令

+ KEYS：返回与pattern匹配的所有keys。 **不建议在生产环境中使用**，在单线程的情况下会影响性能。支持glob-style模式

  ```
  KEYS pattern
  ```

  + `h?llo` matches `hello`, `hallo` and `hxllo`

  + `h*llo` matches `hllo` and `heeeello`

  + `h[ae]llo` matches `hello` and `hallo,` but not `hillo`

  + `h[^e]llo` matches `hallo`, `hbllo`, ... but not `hello`

  + `h[a-b]llo` matches `hallo` and `hbllo`

    

+ DEL：删除指定的键。如果键不存在，则会被忽略。

  ```
  DEL key [key ...]
  ```

  

+ EXISTS：返回键是否存在。

  ```
  EXISTS key [key ...]
  ```

  如果参数中多次提到同一个存在的key，它将被计算多次。例如：如果存在 **somekey**，**EXISTS somekey somekey** 将返回 2

  

+ EXPIRE：设置key超时。超时后，key将被自动删除。只有删除或覆盖key的命令（包括 DEL、SET、GETSET 和所有 *STORE 命令）才会清除超时

  ```
  EXPIRE key seconds [NX | XX | GT | LT]
  ```

  

+ TTL：返回key的剩余生存时间，单位秒。如果key不存在，命令返回 -2。如果key存在但没有相关过期，命令返回-1。

  ```
  TTL key
  ```

  

## String类型常见命令

Redis的String类型可存储字节序列，包括文本、序列化对象和二进制数组。因此，字符串是能与 Redis 键关联的最简单的值类型，最大空间不能超过512M。字符串通常用于缓存，但也支持其他功能。

+ SET：设置 key 为字符串值。如果 key 已有值，无论其类型如何，都会被覆盖。SET 操作成功后，以前与 key 相关的任何生存时间都将被丢弃。

  ```
  SET key value [NX | XX] [GET] [EX seconds | PX milliseconds |
    EXAT unix-time-seconds | PXAT unix-time-milliseconds | KEEPTTL]
  ```

  + NX -- 仅在密钥不存在的情况下设置密钥。
  + XX -- 仅设置已经存在的密钥。
  + GET -- 返回键值处存储的旧字符串，如果键值不存在，则返回 nil。如果键值不是字符串，会返回错误并中止 SET。
  + EX seconds-- 设置指定的过期时间，单位为秒（正整数）。
  + PX milliseconds-- 设置指定的过期时间，以毫秒为单位（正整数）。
  + EXAT timestamp-seconds -- 以秒为单位（正整数）设置指定的密钥过期时间（Unix 时间）。
  + PXAT timestamp-milliseconds -- 设置密钥过期的指定 Unix 时间，单位为毫秒（正整数）。
  + KEEPTTL -- 保留与密钥相关的有效时间。
    

+ MSET：将给定的键设置为各自的值。

  ```
  MSET key value [key value ...]
  ```

  

+ GET：获取 key 的value。如果键不存在，则返回特殊值 nil。如果 key 存储的值不是字符串，则会返回错误，因为 GET 只能处理字符串值。

  ```
  GET key
  ```

  

+ MGET：返回所有指定键的值。对于每个不包含字符串值或不存在的键，都会返回特殊值 nil。

  ```
  MGET key [key ...]
  ```

  

+ INCR：将存储在key上的数字递增 1。如果键不存在，则在**执行操作前**将其设置为 0。如果键值类型错误或包含无法表示为整数的字符串，将返回错误信息。此操作仅限于 64 位有符号整数。

  ```
  INCR key
  ```

  

+ INCRBY：按increment递增key存储的数字，increment可以为负数。其余同incr。

  ```
  INCRBY key increment
  ```

  

+ INCRBYFLOAT：按increment递增key处存储的浮点数字符串，increment可以为负数。如果键不存在，则在执行操作前将其设置为 0。

  ```
  INCRBYFLOAT key increment
  ```

  

+ SETNX：从 Redis 2.6.12 版开始，该命令已被弃用。如果键不存在，则设置键以保存字符串值。在这种情况下，它等于 SET。如果 key 已有值，则不执行任何操作。SETNX 是 "SET if Not eXists "的缩写。

  ```
  SETNX key value
  ```

  

+ SETEX：从 Redis 2.6.12 版开始，该命令已被视为弃用。给key提供value并设置有效期

  ```
  SETEX key seconds value
  ```



## Hash类型

Redis Hash是一种记录类型，其结构是field-value的集合。可以用Hash来表示基本对象和存储计数器分组等。

+ HSET：添加或修改hash类型key的field的value。这条命令会覆盖哈希值中存在的指定字段的值。如果key不存在，则会创建一个新的key。

  ```
  HSET key field value [field value ...]
  ```

  

+ HMSET：从 Redis 4.0.0 版本开始，该命令已被弃用。批量添加或修改hash类型key的field的value

  ```
  HMSET key field value [field value ...]
  ```

  

+ HGET：返回key存储的哈希值中与field相关的value。

  ```
  HGET key field
  ```

  

+ HMGET：批量返回key存储的哈希值中与field相关的value。对于hash中不存在的field的value，都会返回一个 nil 值。

  ```
  HMGET key field [field ...]
  ```

  

+ HGETALL：返回存储在 key 处的hash的所有field和value。

  ```
  HGETALL key
  ```

  

+ HKEYS：返回存储在 key 的所有filed。

  ```
  HKEYS key
  ```

  

+ HVALS：返回key的所有value。

  ````
  HVALS key
  ````

  

+ HINCRBY：按increment递增存储在 key 的哈希值中的filed的value。如果 key 不存在，则会创建一个新的key。如果filed，则在执行操作前将其值设置为 0。

  ```
  HINCRBY key field increment
  ```

  

+ HSETNX：为存储在 key 的哈希值中的field设置值，前提是field不存在。如果 key 不存在，则会创建一个新的key。如果filed已经存在，则此操作无效。

  ```
  HSETNX key field value
  ```

  

## List类型

Redis中的List和Java中的LinkedList类似，可以看作一个双向链表结构，既可以支持正向检索，也支持反向检索。常用于实现堆栈和队列，即存储一个有序数据。

**特点：**

+ 有序
+ 元素可以重复
+ 插入和删除快
+ 查询速度一般



+ LPUSH：将所有指定值插入存储在 key 处的**列表头部**。如果 key 不存在，则在执行推送操作前将其创建为空列表。如果 key 保存的值不是list，则会返回错误信息。

  ```
  LPUSH key element [element ...]
  ```

  

+ RPUSH：将所有指定值插入存储在 key 处的**列表尾部**。如果 key 不存在，则在执行推送操作前将其创建为空列表。如果 key 保存的值不是 list，则会返回错误信息。

  ```
  RPUSH key element [element ...]
  ```

  

+ LPOP：删除并返回存储在 key 处的列表的第一个元素。默认情况下，该命令从列表开头取出一个元素。如果提供了可选的 count 参数，则根据列表的长度，返回至多count个元素（如果列表长度小于count，就只返回count个值），如果列表为空，返回nil

  

+ ```
  LPOP key [count]
  ```

  

+ RPOP：删除并返回存储在 key 处的列表的最后一个元素。默认情况下，该命令从列表末尾弹出一个元素。如果提供了可选的 count 参数，则根据列表的长度，返回至多count个元素（如果列表长度小于count，就只返回count个值），如果列表为空，返回nil

  ```
  RPOP key [count]
  ```

  

+ LRANGE：返回存储在 key 处从索引start到stop的指定元素。索引从0开始。

  ```
  LRANGE key start stop
  ```

  

+ BLPOP：BLPOP 是一种阻塞列表弹出原语。它是 LPOP 的阻塞版本，因为当没有元素从任何给定列表中弹出时，它会阻塞连接。从第一个非空列表的头部弹出一个元素，并按照给定键的顺序进行检查。

  ```
  BLPOP key [key ...] timeout
  ```

  

+ BRPOP：BRPOP 是一种阻塞列表弹出原语。它是 RPOP 的阻塞版本，因为当没有元素从任何给定列表中弹出时，它会阻塞连接。从第一个非空列表的头部弹出一个元素，并按照给定键的顺序进行检查。

  ```
  BRPOP key [key ...] timeout
  ```

  

  



## Set类型

Redis中的Set是由字符串组成的集合，其中的字符串是唯一且无序的。查找速度很快，支持交集、并集、差集等功能。

+ SADD：将指定的成员添加到存储在 key 处的集合中。已是此集合成员的指定成员将被忽略。如果 key 不存在，则会在添加指定成员前创建一个新集合。如果存储在 key 处的值不是一个集合，则会返回错误信息。

  ```
  SADD key member [member ...]
  ```

+ SREM：从存储在 key 处的集合中移除指定的成员。不属于此集合的指定成员将被忽略。如果 key 不存在，则将其视为空集，此命令返回 0。如果 key 处存储的值不是一个集合，则会返回错误信息。

  ```
  SREM key member [member ...]
  ```

+ SISMEMBER：返回成员是否是存储在 key 中的集合的成员。

  ```
  SISMEMBER key member
  ```

+ SCARD：返回set中元素的个数

  ```
  SCARD key
  ```

+ SMEMBERS：获取set中所有的元素

  ```
  SMEMBERS key
  ```

+ SINTER：返回所有给定集合的交集

  ```
  SINTER key [key ...]
  ```

+ SDIFF：返回第一个集合与所有连续集合的差集

  ```
  SDIFF key [key ...]
  ```

+ SUNION：返回所有给定集合的并集

  ```
  SUNION key [key ...]
  ```



## SortedSet类型

Redis的SortedSet是一个可排序的set集合，其中每个元素都带有一个score属性，可以基于score属性对元素排序。底层的实现是一个跳表（SkipList）加一个hash表。具有可排序、元素、查询速度快的特点，常被用来实现排行榜这样的功能。

+ ZADD：添加一对或多对score-member到key所对应的sortedSet

  ```
  ZADD key [NX | XX] [GT | LT] [CH] [INCR] score member [score member
    ...]
  ```

  + **XX**: 只更新已存在的元素。不要添加新元素。
  + **NX**: 只添加新元素。不更新已存在的元素。
  + **LT**: 对于已存在的元素，只有当新score小于当前score时才更新该元素
  + **GT**:  对于已存在的元素，只有当新score大于当前score时才更新该元素
  + **CH**: 将返回值从新增元素数修改为更改元素的总数（更改的元素是指**新添加的元素**和**分值已更新的已有元素**。因此，命令行中指定的元素如果得分与过去相同，则不计算在内）注意：通常 `ZADD` 的返回值只计算新增元素的数量。
  + **INCR**: 指定该选项时，ZADD 的作用与 ZINCRBY 类似。在这种模式下，只能指定一对score-element。

+ ZREM：从存储在 key 处的排序集合中删除指定的成员。不存在的成员将被忽略。如果 key 存在，但不包含排序集合，则会返回错误信息。

  ```
  ZREM key member [member ...]
  ```

+ ZSCORE：返回key所代表的sortedSet中member的score

  ```
  ZSCORE key member
  ```

+ ZRANK：返回key所代表的sortedSet中member的排名。score从低到高进行排序，序号从0开始。withscore参数会在返回值中添加该元素的score

  ```
  ZRANK key member [WITHSCORE]
  ```

+ ZCARD：获取sortedSet中元素的个数

  ```
  ZCARD key
  ```

+ ZCOUNT：返回score在min到max之间的元素的个数

  ```
  ZCOUNT key min max
  ```

+ ZINCRBY：让sortedSet中的member自增，增量为increment。如果member不存在于sortedSet中，则以increment作为其得分。如果 key 不存在，则会创建一个以member为唯一成员的新的sorted set。

  ```
  ZINCRBY key increment member
  ```

+ ZRANGE：获取在start和stop之间的元素

  ```
  ZRANGE key start stop [BYSCORE | BYLEX] [REV] [LIMIT offset count]
    [WITHSCORES]
  ```

  + **BYSCORE**：表示根据Score排序
  + **BYLEX**：表示根据字典序排序
  + **REV**: 反转排序，从高到低排序。score相同时采用反向词典排序
  + **LIMIT offset count**：类似于 SQL 中的 SELECT LIMIT offset, count
  + **WITHSCORES**：在命令回复中补充返回元素的分数

   

# Redis的Java客户端

+ Jedis：学习成本低，线程不安全
+ lettuce：基于Netty实现，支持同步异步和响应式编程，线程安全。支持Redis的哨兵模式，集群模式和管道模式
+ Redisson：基于Redis实现的分布式Java数据结构集合。包含了诸如Map，Queue，Lock，Semaphore等强大的功能



## Jedis

### 使用步骤

1. 引入依赖
2. 建立连接
3. 使用Jedis，方法名与Redis一致
4. 释放连接



### Jedis线程连接池

```Java
public class JedisConnectionFactory{
    private static final JedisPool jedisPool;
    
    static{
        //配置连接池
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(8);
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMinIdle(0);
        jedisPoolConfig.setMaxWaitMillis(200);
        //创建连接池对象
        jedisPool = new JedisPool(jedisPoolConfig,"host","port",1000,"password");
    }
    //获取Jedis对象
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
```



## Spring Data Redis

### 简介

SpringData是Spring中数据操作的模块，包括对各种数据库的继承，其中对Redis的集成模块就叫做SpringDataRedis

+ 提供了对不同Redis客户端的整合（Jedis，Lettuce）
+ 提供了RedisTemplate统一API来操作Redis
+ 支持Redis的发布订阅模型
+ 支持Redis哨兵和Redis集群
+ 支持基于Lettuce的响应式编程
+ 支持基于JDK，JSON，字符串，Spring对象的数据序列化和反序列化
+ 支持Redis的JDKCollection实现



SpringDataRedis中提供了RedisTemplate工具类，其中封装了各种对Redis的操作，并且将不同数据类型的操作API封装到了不同的类型中 


| API                         | 返回值类型      | 说明                      |
| --------------------------- | --------------- | ------------------------- |
| redisTemplate.opsForValue() | valueOperations | 操作**String**类型数据    |
| redisTemplate.opsForHash()  | HashOperations  | 操作**Hash**类型数据      |
| redisTemplate.opsForList()  | ListOperations  | 操作**List**类型数据      |
| redisTemplate.opsForSet()   | SetOperations   | 操作**Set**类型数据       |
| redisTemplate.opsForZSet()  | ZSetOperations  | 操作**SortedSet**类型数据 |
| redisTemplate               | s               | 通用命令                  |



### 使用步骤

1. 引入依赖

   ```xml
   <dependency>
   	<groupId>org.springframework.boot</groupId>
   	<artifactId>spring-boot-starter-data-redis</artifactId>
   </dependency>
   ```

   

2. 配置文件



# 黑马点评实战

## 短信登录

### 基于Session实现登录

**发送短信验证码：**

+ 用户提交手机号
+ 校验手机号
  + 手机号满足格式要求，则生成验证码
  + 手机号不满足格式要求，则要求用户重新提交手机号
+ 生成验证码
+ 将验证码保存到session
+ 发送验证码



**短信验证码登录与注册：**

+ 用户提交手机号和验证码
+ 校验验证码
  + 手机号与验证码匹配
  + 手机号与验证码不匹配则要求用户重新提交
+ 根据手机号查询用户是否存在
  + 用户存在，则保存用户到session
  + 用户不存在，则创建新用户，并将用户保存到数据库后再将其保存到session



**校验登录状态：**

+ 用户携带cookie发起请求
+ 从session中获取用户
+ 判断用户是否存在
  + 用户不存在，则对其进行拦截
  + 用户存在，则将用户保存到ThreadLocal中
+ 放行



###  基于Redis实现登录

**session共享问题：**当在不同服务器间切换请求时会导致数据丢失的问题，因为它们不共享session存储空间。

**解决方案：**Redis可以实现不同服务器之间的数据共享，同时还是内存存储（读写速度很快）



**和Session方案的区别：**

+ 生成验证码后将验证码保存到Redis而不是Session，保存内容的Key为手机号，value为验证码
+ 保存用户到Redis时key为随机token，value为用户信息，并且将token返回给前端。
+ 校验登录状态时，前端携带token作为key，并且从redis中获取用户信息



**注意事项：**

+ 选择合适的数据结构，key，存储粒度
+ 生成code阶段：将code存储到Redis时需要设置过期时间，避免数据堆积
+ 登录校验阶段：
  + 利用hutool工具中的BeanUtil下的copyProperties方法隐藏user中的部分信息，将其转换成UserDTO对象（信息更少的对象）
  + 利用BeanUtil下的beanToMap方法

