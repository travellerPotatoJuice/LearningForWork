#### 一、选择题

##### 1. Java语言中提供了一个（）线程，自动回收动态分配的内存 <font color=blue size=5>D</font>

A. 异步 

B. 消费者

C. 守护 

D. 垃圾收集

##### 2. Java语言避免了大多数的（）是错误的。<font color=blue size=5>C</font>

A. 数组下标越界 

B. 算术溢出 

C. 内存泄露 

D. 非法的方法参数

##### 3. 有三种原因可以导致线程不能运行，它们是（）<font color=blue size=5>ABC</font>

A．等待 

B．阻塞 

C．休眠 

D．由于I/O操作而阻塞

##### 4.当（）方法终止时，能使线程进入死亡状态。<font color=blue size=5>A</font>

A．run 

B．setPrority

C．sleep

D．yield

##### 5.用（）方法可以改变线程的优先级。<font color=blue size=5>B</font>

A．run 

B．setPrority 

C．yield 

D．sleep

##### 7.线程通过（）方法可以休眠一段时间，然后恢复运行。<font color=blue size=5>D</font>

A．run 

B．setPrority 

C．yield 

D．sleep

##### 8. 关于下列同步说法错误的是 ()<font color=blue size=5>D</font>

 A. 同步代码块可以锁住指定代码,同步方法是锁住方法中所有代码

 B. 同步代码块可以指定锁对象,同步方法不能指定锁对象

 C. 对于非static方法,同步锁是this

 D. 对于static方法,同步锁是调用此方法的对象 **<font color=red size=3> 是该方法所属的类</font>**

------



#### 二、今日方法：

1. **线程同步涉及到的关键字以及类名和方法名：**

   关键字：synchronized

   类名：ReentrantLoc

   方法名：

   + lock()
   + unlock()

   

2. **线程六种状态的名称：**

   1. 新建（New）：当通过创建Thread对象来实例化一个新的线程时，线程处于新建状态。此时线程已经被创建，但还没有开始执行。
   2. 可运行（Runnable）：在新建状态之后，调用线程的`start()`方法会使线程进入可运行状态。在可运行状态下，线程可能正在执行，也可能正在等待系统资源（如CPU时间片）。
   3. 运行（Running）：当线程获得了CPU时间片并开始执行时，线程处于运行状态。在运行状态下，线程会执行它的任务代码。
   4. 阻塞（Blocked）：线程可能会进入阻塞状态，原因包括等待I/O操作、等待获取锁、等待其他线程的通知等。在这种情况下，线程暂时停止执行，并释放CPU资源。
   5. 等待（Waiting）：线程可能会调用一些等待方法，使其进入等待状态，直到其他线程发出通知或中断它。常见的等待方法包括`Object.wait()`、`Thread.sleep()`和`Condition.await()`等。
   6. 终止（Terminated）：线程执行完它的任务代码或者出现异常时，线程将进入终止状态。一旦线程处于终止状态，它将不再运行。

   

3. **线程通信涉及的方法名称：**

   + wait()
   + notify()
   + notifyAll()

   

4. **线程工具类中，能创建不同线程池的方法名称：**

   + newFixedThreadPool(int nThreads)
   + newSingleThreadExecutor()
   + newCachedThreadPool()
   + newScheduledThreadPool(int corePoolSize)

------

#### 三、简答题：

1. **多线程是不是就是线程不安全？为什么会发生线程安全？**

   + 多线程是不一定就是线程不安全。

   + 当多个线程同时对同一资源进行读写操作时，可能会出现线程安全问题

   

2. **有几种方式解决线程安全？需要注意什么？**

   + 用互斥锁（synchronized)来限制多个线程对共享资源的访问
   + 用重入锁（ReentrantLock）提供的锁机制
   + 用原子类，如AtomicInteger，AtomicLong等

   

3. **简述通过ThreadPoolExecutor创建线程池时，构造方法对应的7个参数分别表示什么意思？并说明常见的任务的拒绝策略都有哪些？**

   ```Java
   public ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory, RejectedExecutionHandler handler)
   ```

   + **参数**
     + corePoolSize：指定线程池的核心线程数量
     + maximumPoolSize：指定最大线程数量
     + keepAliveTime：指定临时线程存活时间
     + unit：临时线程存活时间的单位
     + workQueue：线程池任务队列
     + threadFactory：线程工厂
     + handler：线程池拒绝策略
   + **常见拒绝策略**
     + ThreadPoolExecutor.AbortPolicy：默认策略，丢弃任务抛异常
     + ThreadPoolExecutor.DiscardPolicy：丢弃任务不抛异常
     + ThreadPoolExecutor.DiscardOldestPolicy：抛弃队列中等待最久的任务
     + ThreadPoolExecutor.CallerRunsPolicy：主线程调用任务的run方法，绕过线程池直接执行

4. **为什么要使用线程池，线程池有什么优势？**

   + 线程池能提高线程的复用率，减少线程创建和上下文切换的成本
   + 线程池能实现对线程生命周期的自动化管理
   + 线程池实现了任务队列，能自动按照一定的策略对任务进行调度
   + 线程池能动态地控制线程的并发运行

   

5. **简述线程池的工作流程？**

   + 线程池创建
   + 任务提交
   + 任务排队
   + 任务执行
   + 线程调度
   + 异常处理
   + 线程销毁

------

#### 四、排错题：

##### 排错题1：分析当执行如下程序的ThreadDemo01的main方法的时候，在控制台会输出什么内容？如果想一直输出"执行了======"应该对该程序如何改造【只允许对flag变量进行相关修改】？并且说明原因？

```Java
public class MyThread extends Thread{

    private boolean flag = false;
    public boolean isFlag(){
        return flag;
    }

    @Override
    public void run(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        this.flag = true;
        System.out.println("flag=" + flag);
    }
}

```

```Java
import java.util.*;

public class Main{
    public static void main(String[] args) {
        MyThread myThread= new MyThread();
        myThread.start();
        while(true){
            if(myThread.isFlag()){
                System.out.println("执行了======");
            }
        }
    }
}
```



<font color=blue size=4>控制台会输出flag=true</font>

<font color=blue size=4>因为在Java中，线程之间对共享变量的修改不一定会立即对其他线程可见。当flag的值被MyThread修改后，它会将值更新到线程的本地内存中，而不是写入主内存，所以主线程在读取flag的值时不一定能读到最新的值。因此就陷入了循环当中</font>

<font color=blue size=4>只需要把MyThread中flag的初始值设置为true就可以了</font>

##### 排错题2：以下代码在控制台输出结果是什么？多运行几次试试？如果结果不是如你所愿，请试着猜一下原因~

<font color=blue size=4>已经送了2个冰淇淋
已经送了3个冰淇淋
已经送了2个冰淇淋
已经送了7个冰淇淋
已经送了5个冰淇淋
已经送了4个冰淇淋</font>

<font color=blue size=4>出现这样的结果是因为：</font>

+ <font color=blue size=4>创建的多个线程共享一个MyAtomThread对象，这些线程之间的运行是异步的。</font>
+ <font color=blue size=4>当线程A读取count的值时，线程B可能已经修改了count的值，并进行了输出，此时线程A仍然对旧的值进行操作，并进行输出。</font>

#### 五、代码题：

##### 第一题：分析以下需求，并用代码实现

**训练目标**：

​	掌握java中多线程基本使用

**需求描述**：

​	有100份礼品，小红，小明两人同时发送，当剩下的礼品小于10份的时候则不再送出，利用多线程模拟该过程并将线程的名称打印出来。并最后在控制台分别打印小红，小明各自送出多少分礼物。

##### 第二题：分析以下需求，并用代码实现

**训练目标**：

​	掌握java中多线程基本使用

**需求描述**：

```java
有一个抽奖池,该抽奖池中存放了奖励的金额,该抽奖池中的奖项为 {10,5,20,50,100,200,500,800,2,80,300,700}; 
	创建两个抽奖箱(线程)设置线程名称分别为“抽奖箱1”，“抽奖箱2”，随机从抽奖池中获取奖项元素并打印在控制台上,格式如下:
	
	1.每次抽出一个奖项就打印一个(随机)
		抽奖箱1 又产生了一个 10 元大奖
		抽奖箱1 又产生了一个 100 元大奖
		抽奖箱1 又产生了一个 200 元大奖
		抽奖箱1 又产生了一个 800 元大奖	
		抽奖箱2 又产生了一个 700 元大奖			
		//.....

	2.每次抽的过程中，不打印，抽完时一次性打印(随机)
		在此次抽奖过程中，抽奖箱1总共产生了6个奖项，分别为：10,20,100,500,2,300最高奖项为300元，总计额为932元
		在此次抽奖过程中，抽奖箱2总共产生了6个奖项，分别为：5,50,200,800,80,700最高奖项为800元，总计额为1835元

	3.每次抽的过程中，不打印，抽完时一次性打印(随机)
		在此次抽奖过程中，抽奖箱1总共产生了6个奖项，分别为：10,20,100,500,2,300最高奖项为300元，总计额为932元
		在此次抽奖过程中，抽奖箱2总共产生了6个奖项，分别为：5,50,200,800,80,700最高奖项为800元，总计额为1835元
		在此次抽奖过程中,抽奖箱2中产生了最大奖项,该奖项金额为800元
		
	以上打印效果只是数据模拟,实际代码运行的效果会有差异
```

------

##### 第三题：【选做题】分析以下需求，并用代码实现

**训练目标：**

​	掌握java中多线程的线程通信基本使用

**需求描述：**	 

​	用两个线程玩猜数字游戏，第一个线程负责随机给出1~100之间的一个整数，第二个线程负责猜出这个数。
要求：

1. 每当第二个线程给出自己的猜测后，第一个线程都会提示“猜小了”、“猜 大了”或“猜对了”。
2. 猜数之前，要求第二个线程要等待第一个线程设置好 要猜测的数。
3. 第一个线程设置好猜测数之后，两个线程还要相互等待，其原则是：
   - 第二个线程给出自己的猜测后，等待第一个线程给出的提示；
   - 第一个 线程给出提示后，等待第二个线程给出猜测，如此进行，直到第二个线程给 出正确的猜测后，两个线程进入死亡状态。 











