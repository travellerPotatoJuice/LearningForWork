# 硬件结构

## 存储系统体系结构

离CPU越近，速度越快，价格越贵：

磁盘 < 内存 <（L3 Cache < L2 Cache < L1 Cache < 寄存器） ----->  CPU



**寄存器：**

用于临时存放指令、数据和地址等信息。是CPU中最快速、最直接访问的存储单元。包括：数据寄存器，地址寄存器，指令寄存器，程序计数器，状态寄存器。

一般要求在半个时钟周期内完成读写，也就是0.x纳秒的级别



**Cache：**

一般用SRAM（Static Random-Access Memory, 静态随机存储器）实现，掉电后数据会丢失。

**L1 Cache**：每个CPU核心都有，通常分成指令缓存和数据缓存，2\~4个时钟周期内完成读写，大小几十KB~几百KB

**L2 Cache**：每个CPU核心都有，10~20个时钟周期内完成读写，大小在几百 KB 到几 MB

**L3 Cache**：多个CPU共用，20\~60个时钟周期内完成读写，大小几MB~几十MB

![image-20240407210906509](image/image-20240407210906509.jpg)

**内存：**

一般用DRAM（Dynamic Random-Access Memory, 静态随机存储器）实现。数据会被存储在电容里，电容会不断漏电，所以需要「定时刷新」电容。速度大概在200~300时钟周期。



**硬盘：**

断电后数据不会丢失，固态硬盘叫SSD，机械硬盘叫HDD

内存比SSD快10~100倍，内存比HDD快10w倍

![image-20240407210630371](image/image-20240407210630371.jpg)



<font size=5>**利用CPU Cache进行代码优化：**</font>

**Cache line**（缓存行）是计算机体系结构中缓存中的基本单位，它是缓存中存储数据的最小单元。CPU每次从内存中读取数据时，都会以Cache Line的大小为基本单位进行读取



全相联：

+ **对比标记。**将地址中的标记和所有Cache Line中的标记进行比较
+ **查看偏移量。**标记匹配且**有效位为1**的情况下，获取要读取的地址在块中的偏移量并读取数据。



直接映射：

+ **计算Cache行号**。也就是根据主存地址中的特定部分计算出这个数据会被映射到Cache中的哪一行
+ **查看有效位**。主存中的数据被修改等情况会导致Cache中的数据被认定为无效的，此时有效位会被修改。如果查找数据时发现有效位为0，则直接从内存中读取数据。
+ **对比标记**。由于多个主存块可能会被映射到一个Cache Line中，所以需要确认需要读取的数据的标记是否和Cache Line中存放的来自同一个贮存块。
+ **查看偏移量。**获取要读取的地址在块中的偏移量并读取数据。



组相联：

+ **计算组号。**根据主存地址计算数据会被映射到哪个组里
+ **对比标记。**将地址中的标记和**组内**所有Cache Line中的标记进行比较
+ **查看偏移量。**标记匹配且**有效位为1**的情况下，获取要读取的地址在块中的偏移量并读取数据。



![image-20240407214739059](image/image-20240407214718805.jpg)



通过以上的映射方法，可以发现，如果CPU命中缓存的命中率越高，CPU运行完所有任务的时间就更短。缓存分为**数据缓存**和**指令缓存**，所以我们要分开考虑：

+ 提升**数据缓存**命中率：按照内存中的存储顺序读写元素。

+ 提升**指令缓存**命中率：不要在分支之内进行频繁跳跃。（CPU有动态分支预测技术，尽量让某个分支的执行集中一点，比如说在一个循环内频繁进行if-else判断，尽量让命中if分支的操作集中一点）（例如有两个操作，一个要将数组中小于50的数据变为0，另一个要将数组进行排序，先排序再修改的效果就会比先修改再排序的效果好）

+ 提升**多核CPU**的缓存命中率：将线程绑定在某个CPU核心上运行。L1 Cache和L2 Cache是CPU核心独有的，如果一个线程在不同CPU之间来回切换，就不能很好地利用Cache了



## 缓存内存的数据一致性

<font size=5>**Cache和内存的一致性**</font>

（CPU、缓存、内存）之间的关系类似于（服务器，缓存，数据库）之间的关系



**写直达（write through）**

**强一致性**，要确保每次对数据的更新都会同步到内存。

+ 判断数据在不在缓存
  + 不在，直接写回内存
+ 在，先写回缓存，再写回内存

每次写都要写回内存，速度变慢了



**写回（write back）**

**弱一致性**，延迟写，写完缓存不立刻写回内存，只在不得不写回内存的时候才写

+ 判断数据在不在缓存
  
  + 在，直接写缓存
  
+ 不在，查看block里存放的别的内存地址的数据是不是脏的
  
  + 脏的，将数据写回内存
  
+ 不是脏的，从内存中读取自己要写的数据到cache

+ 写cache并且标记为脏的

  ​        

-----------------------

<font size=5>**不同核心内Cache的一致性**</font>

两个原则。一个是CPU核心对共享的值进行修改之后，需要让别的CPU知道自己修改了（**写传播**）。第二个原则是不同 CPU 核心里对数据的操作顺序，必须在其他核心看起来顺序是一样的（**事务的串行化**）



**MESI协议**

**总线嗅探**：当某个 CPU 核心更新了 Cache 中的数据，要把该事件广播通知到其他核心。（需要CPU一直监听别的CPU的消息，CPU每次更新都要广播，无法实现事务串行化）

MESI协议通过四个状态：已修改（Modified），独占（Exclusive），共享（Shared），已失效（Invalidated）来为Cache标记状态，设计了一个有限状态自动机来满足写传播和事务串行化。



**伪共享**

多个在不同的CPU核心上运行的线程，试图访问和修改不同但邻近的内存位置时，就会出现伪共享问题。

具体来说就是这些线程**不处理同一个数据**，但是他们处理的数据又会被加载到同一个Cache Line中，MESI协议操作的基本单位是Cache Line，所以就会导致产生一些不必要的操作。



**避免伪共享**：

+ 内存对齐。在定义变量时使用宏定义让物理内存地址上连续的变量处于不同的Cache Line中，用空间换时间。
+ 数据填充。定义一些空白字符，填充到变量之前。

---------------------







# 内存管理

## 虚拟内存

**为什么要有虚拟内存？**

- **扩展内存**。在逻辑上将磁盘空间作为了内存的一部分，使进程的运行内存超过物理内存大小。因为程序运行具有局部性原理，CPU 访问内存会有很明显的重复访问的倾向性，对于那些没有被经常使用到的内存，我们可以把它换出到物理内存之外，比如硬盘上的 swap 区域。
- **内存保护**。由于每个进程都有自己的页表，所以每个进程的虚拟内存空间就是相互独立的。进程也没有办法访问其他进程的页表，所以这些页表是私有的，这就解决了多进程之间地址冲突的问题。
- **内存访问控制**。页表里的页表项中除了物理地址之外，还有一些标记属性的比特，比如控制一个页的读写权限，标记该页是否存在等。在内存访问方面，操作系统提供了更好的安全性。
- **简化编程模型**。程序员不需要关心物理内存的具体布局和大小，可以在更高层次上进行编程。这使得编写大型复杂程序变得更加容易。



**系统内存紧张时会发生什么？**（和JVM的垃圾回收机制有共通之处）

1. 后台内存回收。物理内存紧张时会唤醒kswapd内核线程**异步回收**内存，这个过程不会阻塞进程的执行。
2. 直接内存回收。异步回收速度跟不上内存申请速度时，会开始同步回收内存，此时会阻塞进程的执行。
3. 触发OOM（Out of Memory）机制。直接内存回收仍然无法满足此次物理内存申请时，触发OOM机制。

主要有两类内存可以被回收，都是基于LRU算法的，优先回收不常访问的内存

+ 文件页：回收干净页的方式是直接释放内存，回收脏页的方式是先写回磁盘后释放内存
+ 匿名页：这部分内存没有实际载体，**回收的方式是通过 Linux 的 Swap 机制**



**4GB的物理内存上是否可以申请8G的内存?**

+ 考虑**操作系统位数**。32位操作系统寻址空间是4GB（1GB内核空间+3GB用户空间），8G超过其寻址空间大小，所以会分配失败。64位操作系统寻址空间是16EB（128T内核空间+128T用户空间+剩下的未定义空间，8G在其寻址空间大小之内，所以会分配成功。
+ 考虑**虚拟内存**。进程申请内存是申请虚拟内存，只要不读写这个虚拟内存，操作系统就不会分配物理内存。所以在64位操作系统下，可以成功分配内存。
+ **考虑Swap机制**。



## 如何避免预读失效和缓存污染

**什么是预读失效和缓存污染：**

传统LRU中，用链表来实现内存管理，链表头部的数据是最近使用的，链表尾部的数据是最久没被使用的，每次读入新块时淘汰尾部的块。

+ 预读失效：为了充分利用局部性原理而多读入的块在未来并没有被命中。
+ 缓存污染：批量读取数据将真正的热点数据淘汰出了内存，从而导致命中率下降。



**解决预读失效的方法：**

+ Linux里将链表划分成了**活跃LRU链表**和**非活跃LRU链表**，活跃链表内存最近被访问过的内存页，非活跃链表中存很少被访问的内存页。预读页直接进入非活跃链表，被访问时才插入活跃链表。（非活跃链表比较短，可以避免预读页长时间停留在内存中）
+ MySQL里将链表划分成了**young区域**和**old区域**，被访问的页加入到young区域的头部，预读的页插入到old区域的头部，被访问时才插入young区域的头部。（old区域在链表中占比较小，可以避免预读页长时间停留在内存中）。



**解决缓存污染的方法：**

提高页面进入链表活跃区域的门槛

+ Linux，在内存页被第二次访问时，才从非活跃链表移入到活跃链表
+ MySQL，在内存页被第二次访问时，判断和第一次访问时间的差距，如果小于1秒（可设置），就不做处理。如果大于1秒，则将其移动到young区域。



# 进程管理

## 进程



**进程状态：**

- 创建状态（*new*）：进程正在被创建时的状态；
- 运行状态（*Running*）：该时刻进程占用 CPU；
- 就绪状态（*Ready*）：可运行，由于其他进程处于运行状态而暂时停止运行；
- 阻塞状态（*Blocked*）：该进程正在等待某一事件发生（如等待输入/输出操作的完成）而暂时停止运行，这时，即使给它CPU控制权，它也无法运行；**此时进程仍然处在内存之中**

- 结束状态（*Exit*）：进程正在从系统中消失时的状态；

- 阻塞挂起状态：进程**在外存（硬盘）**并等待某个事件的出现；
- 就绪挂起状态：进程**在外存（硬盘）**，但只要进入内存，即刻立刻运行；

【阻塞状态和挂起状态的区别是，阻塞状态是在等待某个事件完成，进程还是在内存中的。挂起状态时，进程被暂时换出到硬盘里了。】

![image-20240408101248609](image/image-20240408101248609.jpg)

**PCB：**

进程控制块（process control block， PCB），PCB中主要包括以下信息：

+ 进程描述信息
  + 进程标识符：标识进程
  + 用户标识符：标识进程归属的用户
+ 进程控制和管理信息：
  + 进程当前状态
  + 进程优先级
+ 资源分配清单
  + 打开文件列表
  + 使用IO设备信息
  + 内存地址和虚拟内存地址信息
+ 





**进程上下文切换：**

发生时机：

+ 进程用完了当前时间片
+ 被更高优先级的进程抢占
+ 硬件中断
+ 系统资源不足时
+ 进程通过sleep（）把自己挂起

【看状态转换图就可以了，无非就是时间片用完进入就绪态，或者挂起，或者阻塞】



**进程通信方式**

+ 管道：一种半双工的通信方式，通信数据都遵循**先进先出**原则，生命周期随进程
+ 消息队列：保存在内核中的消息链表，支持进程根据类型读取（不同类型串在不同链表中），但仍然遵循先进先出，不支持随机读取，生命周期随操作系统
+ 共享内存：不同进程各自拿出一块虚拟地址空间，映射到相同的物理内存中。可以随机访问，但需要额外的同步机制。
+ 信号量：其实不是为了让进程之间交换数据，而是为了实现进程同步和互斥，也就是用于管理对共享资源的访问
+ 信号：用于进程间通信和处理异步事件的机制
+ Socket：不同主机上的进程，同一主机上的不同进程都可以用socket通信。本地socket通信时只需要绑定一个本地文件即可



## 线程

把一个大任务拆分成若干个小任务执行，但是这些小任务之间又需要共享一些数据。如果为每个小任务都创建一个进程，那需要解决共享数据的问题，比较复杂，而且进程创建和撤销，以及切换的时候代价太大。所以需要引入线程的概念，它们既可以分别执行各自的小任务，又可以共享存储空间。

【也就是控制上是独立的，数据上是共享的】



用户级线程和内核级线程的区别其实在于，用户级线程管理线程的指令，以及所管理的数据结构都存储在用户空间，而内核级线程则存储在内核空间。

**用户线程：**通过用户级线程库实现。线程切换的代价比较小，无需在用户态和核心态之间进行转换。【无需切换到核心态执行指令，以及存取核心空间的数据】。

**内核线程：**操作系统真正调度的单位



<font size=5>**CPU线程选择策略**</font>

在Linux中有三种调度类：Deadline和Realtime是应用于实时任务的，Fair是应用于普通任务的

+ Deadline：使用Deadline调度器，运行队列为dl_rq。
+ Realtime：使用RT调度器，运行队列为rt_rq。
+ Fair：使用CFS调度器，运行队列为cfs_rq。

以上调度类的优先级是Deadline > Realtime > Fair，所以cpu会按“ dl_rq > rt_rq > cfs_rq” 的顺序选取任务。





## 进程调度

+ 先来先服务（FCFS）：对短作业不利
+ 最短作业优先（SJF）：对长作业不利，可能会导致饥饿
+ 高响应比优先（HRRN）：优先权 = （等待时间+要求服务时间）/要求服务时间。等待时间一样，短作业优先。要求服务时间一样，等待时间长的优先。【理想型算法，进程要求服务的时间是未知的】
+ 时间片轮转：每个作业分配一个时间片【时间片长度不好设置】
+ 最高优先级调度（HPF）：分成静态优先级（创建时分配优先级）和动态优先级（根据进程变化动态调整）。还分成抢占式和非抢占式。
+ 多级反馈队列：

![image-20240408125947856](image/image-20240408125947856.jpg)



## 同步互斥

同步：多个线程之间按照一定的顺序协调执行

互斥：用于保护共享资源不被多个线程同时访问或修改





## 锁

**死锁：**多个线程/进程在获取共享资源时陷入循环等待

-----------

**<悲观锁>**

**自旋锁**：当获取不到锁的时候就会一直while循环，不做任何事情。除非他主动放弃CPU，或者其他线程可以抢占CPU，否则将一直自旋下去。（因为线程A得不到CPU，不会执行后续代码释放锁，线程B一直请求不到锁，就一直自选不放弃CPU，陷入了死锁状态）。注意：自旋锁是基于CAS+while的，CAS本身是乐观锁

**互斥锁**：获取不到锁的时候，不用自旋，操作系统会被线程进行休眠，等锁释放了再唤醒该线程，不过这个过程需要切换两次上下文【休眠线程的时候一次，唤醒线程让它成为就绪态了以后还会有一次】，每次切换大概要几十纳秒到几微秒，如果可以确定获取锁的线程执行时间很短，可以直接使用自旋锁，减少上下文切换带来的成本。

**读写锁：**包括读锁和写锁。根据实现不同可以分为读优先锁，写优先锁，公平读写锁。**读优先锁**指的是，当读线程A持有锁时，写线程B获取锁会被阻塞，但后续来的读线程C可以获取读锁。**写优先锁是**，当读线程A持有锁时，写线程B获取锁会被阻塞，但后续来的读线程C不能获取读锁，要等写线程写完之后才能获取读锁。**公平读写锁是**，不管读线程还是写线程，都按先进先出的原则获取锁

****

**<乐观锁>**

乐观锁的工作方式是：先修改完共享资源，再验证这段时间内有没有发生冲突，如果没有其他线程在修改资源，那么操作完成，如果发现有其他线程已经修改过这个资源，就放弃本次操作。

由于全程并没有加锁，所以它也叫无锁编程。乐观锁虽然去除了加锁解锁的操作，但是一旦发生冲突，重试的成本非常高，所以**只有在冲突概率非常低，且加锁成本非常高的场景时，才考虑使用乐观锁。**



## 一个进程可以创建多少个线程

+ 从线程占用的空间考虑：每创建一个线程就要分配一定的空间，所以线程的数量取决于进程的空间大小
+ 从linux系统的系统参数考虑：linux有系统级别的参数来控制整个系统的最大线程数。比如“系统支持的最大线程数”“系统全局pid号的限制”“一个进程允许拥有的虚拟内存的大小”



## 线程崩溃了，进程一定会崩溃吗

在进程中，**各个线程的地址空间是共享的**，既然是共享，那么某个线程对地址的非法访问就会导致内存的不确定性

操作系统为了保证系统安全，所以针对非法内存访问会发送一个 SIGSEGV 信号。如果进程没有注册信号处理函数，操作系统会根据信号类型执行对应的信号处理函数（一般会让相关的进程崩溃）。如果进程注册了信号处理函数，则会根据进程自己注册的处理函数逻辑进行处理。

虚拟机内部定义了信号处理函数，而在信号处理函数中对这两者做了额外的处理让 JVM 不崩溃



# 文件系统

## 文件系统基本组成

文件系统是操作系统中负责管理文件的子模块。它负责文件的创建、存储、组织、检索和删除等操作。本质上就是一个软件。

Linux文件系统中，有以下两种重要的数据结构：

+ 索引节点inode。是文件的唯一标识，和文件一一对应。用于记录文件的**元数据**（即描述文件属性的信息，而不是文件内容本身）及**指向文件数据块的指针**。
+ 目录项dentry。用于存储文件名和对应的索引节点号，使得文件系统可以通过文件名定位和访问文件。目录项和索引节点是多对一关系，一个文件可以有多个别名。（注意：目录项既指组成目录的条目，又指一种存储在内存中的数据结构。实际上就是将要使用的目录项从目录文件中读取到内存，并且缓存在内存）



## 文件I/O

+ 缓冲IO和非缓冲IO
  + 缓冲IO：利用中间缓冲区临时存储数据，读写操作时都先操作缓冲区。适合频繁的小数据读写。
  + 非缓冲IO：每次执行读写操作时都直接访问磁盘。适合实时数据读写。
+ 直接IO与非直接IO





# 网络系统

## 什么是零拷贝

+ 不使用DMA：CPU负责将数据从磁盘控制器缓冲区拷贝到Page Cache，再将数据从Page Cache拷贝到用户缓冲区。
+ 使用DMA（Direct Memory Access）：数据搬运的工作全部交给 DMA 控制器，而 CPU 不再参与任何与数据搬运相关的事情，这样 CPU 就可以去处理别的事务。