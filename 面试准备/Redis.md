# 为什么要用分布式缓存

+ 缓存的根本思想就是空间换时间。一方面可以加快访问速度，另一方面可以降低数据库的压力，提高并发能力。
+ 本地缓存读取速度比较快，但是存储容量小，不方便在服务器之间共享，一旦服务器宕机数据就会丢失。
+ 分布式缓存虽然需要耗费一定的时间用于网络通信，但是容量大，方便实现共享，有的分布式缓存实现了持久化机制更加可靠。



# Redis的数据类型有哪些？底层分别是什么数据结构？

+ String：int, SDS
+ Hash：(ZipList,HashTable)-> (ListPack,HashTable)
+ List：(ZipList,LinkedList)->(QuickList)->(ListPack)
+ Set：(IntSet, HashTable)->(IntSet,ListPack,HashTable)
+ Zset：(ZipList,(SkipList,HashTable))->(ListPack, (SkipList, HashTable))
+ Bipmap：String
+ GEO：基于Zset实现
+ HyperLogLog：数组
+ Stream：Radix Tree



## HashTable（Dict）要用拉链法解决哈希冲突，而不用红黑树

理论上的最优解并不一定符合现实需求。红黑树通常是在节点数比较多的情况下比较有优势，当节点数比较少的情况下优势不明显。（这也就是为什么Java的HashMap要设置链表长度为8时才自动转换成红黑树）。

而Redis在设置HashTable的rehash阈值时，设置成了1和5，也就是说碰撞的节点没有达到很长的长度时就已经触发了rehash，在这种场景下红黑树没什么太大的优势。



## 为什么Hash在长度较小时使用ZipList，较大时使用HashTable

+ ZipList不用存储指针，可以节约空间。所以长度较小的时候用ZipList进行遍历搜索，牺牲一点时间换取宝贵的内存空间也是值得的。
+ 长度较大的时候用ZipList存取数据效率就比较低了，而且ZipList需要连续的存储空间，在内存中申请连续的大片内存是不太容易的。就算能申请到，ZipList还需要解决连锁更新的问题。



## 为什么List在长度较小时用ZipList，较大时用LinkedList

+ ZipList不用保存双向的指针，比较节约空间。
+ ZipList需要连续的内存空间，并且长度增大后会有连锁更新的问题，所以不得已只能在长度较长的时候用LinkedList。不过后续引入的ListPack解决了连锁更新的问题，所以在新版本中就直接用ListPack了。



## 为什么Set在长度较小时使用IntSet，较大时使用HashTable

+ 如果元素都是整数，并且元素个数较少时，用IntSet。因为HashTable虽然查询效率高，但是需要保存很多指针，会浪费内存，所以长度比较小的情况下还是可以考虑用IntSet
+ 如果元素较多，为了提升查询效率，只能浪费一些内存保存指针，所以用HashTable来存。



## 为什么Zs et在长度较小时用ZipList，较大时用SkipList+HashTable

+ 元素数量不多的时候，HashTable+SkipList查询性能不明显，而且要存储大量指针，所以采用ZipList来节省内存。因为ZipList本身没有键值对的概念，所以存储的时候需要为element和score各自创建一个entry，然后挨着存储，再根据score进行升序排序。

+ 和上面的问题一样，ZipList一旦长度太长就出问题了，所以在元素数量多的时候不得不采用HashTable+SkipList的方式。HashTable用于实现键值查询的部分，SkipList用于实现排序的部分



# Redis除了可以用来做缓存，还可以做什么？

+ String：存储对象（JSON），分布式锁，常规计数，共享session信息
+ Hash：存储对象（比较适合写多的情况）
+ List：消息队列
+ Set：点赞，共同关注，抽奖
+ Zset：排行榜
+ BitMap：二值统计，签到打卡，布隆过滤器，判断用户是否在线
+ GEO：定位
+ HyperLogLog：网页UV统计，搜索指数统计
+ Stream：消息队列





# Redis的线程模型是怎么样的

Redis实际上在运行的时候存在多个线程的，但是「接收客户端请求->解析请求 ->进行数据读写等操作->发送数据给客户端」这个过程是由单个线程执行的。

在Redis2.6之前，除了主线程外会有两个后台线程，分别处理关闭文件，以及AOF刷盘这两个任务

Redis4.0版本之后，新增了一个后台任务用于释放Redis内存

Redis6.0版本之后，又新增了三个线程用于分摊主线程网络IO的压力



# Redis为什么那么快

+ Redis的事件处理模型设计得比较好，通过IO多路复用减少了阻塞
+ Redis基于单线程实现，没有多线程并发控制和上下文切换的开销
+ Redis中设计了许多优秀的数据结构
+ Redis基于内存读写



# 缓存穿透&缓存雪崩&缓存击穿

这三个问题本质上都是缓存失效，导致大量请求落在了数据库上，数据库的压力陡然加剧。

+ 缓存穿透
  + 数据在缓存和数据库中都不存在
  + 解决方案：布隆过滤器，缓存空对象，加强基础格式校验，加强用户权限校验
+ 缓存雪崩
  + 大量的key几乎同时失效，Redis宕机
  + 解决方案：给key设置随机过期时间，设置逻辑过期时间，设置多级缓存；提高Redis可用性，设置服务熔断机制或者限流机制
+ 缓存击穿
  + 热点key数据失效，缓存数据重建成功前有大量请求落在数据库上
  + 解决方案：互斥锁，设置逻辑过期时间



# Redis持久化方法？

+ RDB：基于快照
  + save&bgsave两种模式
  + 优点是恢复数据快，缺点是数据可靠性不高
+ AOF：基于逻辑文件
  + 三种刷盘策略
  + bgrewriteaof
+ 混合模式：主要是bgrewriteaof的时候用



# Redis主从是怎么实现的？





# Redis的哨兵机制？

+ 监控：基于心跳机制监控Redis集群中的节点，涉及主观下线和客观下线
+ 自动故障恢复：
  + 选举负责故障恢复的sentinel
  + sentinel基于一定的选取策略选出合适的节点，将其设为master
  + 将选举结果广播给所有节点
  + 通知rediscClient master出现了变更
  + 监控老master，当其恢复时将其设为slave





# Redis集群是怎么实现的？







# Redis集群中的数据一致性？





# 如何保证Redis和MySQL的数据一致性？







# Redis的内存淘汰机制是怎么样的？





# Redis事务是什么？支持原子性吗？







# Redis的事务有什么缺陷？怎么解决？





# 大Key是什么？有什么危害？







# 如何发现大Key？如何避免大Key集中过期？



