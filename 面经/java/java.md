# Java基础

## 方法

### 方法的返回值

**按方法的返回值和参数类型进行分类：**

+ 无参数无返回值的方法
+ 有参数无返回值的方法
+ 有返回值无参数的方法
+ 有返回值有参数的方法



### 静态方法和实例方法、

+ 调用方式
  + 静态方法有两种调用方式
  + 实例方法只有一种调用方式
+ 访问限制
  + 静态方法只允许访问静态成员
  + 实例方法允许访问所有成员



 **静态方法为什么不能调用非静态成员：**

静态方法和非静态成员创建的时间不同



### 重载和重写

**重载：一个方法能根据输入数据的不同，做出不同的处理**

+ 同一个类中
+ 方法名字相同，参数不同
+ 发生在编译期

**重写：子类对父类允许访问的方法的实现过程进行重新编写**

+ 子类对父类
+ 方法名，参数列表必须相同
+ 子类方法返回值类型小于等于父类
+ 子类方法抛出的异常范围小于等于父类
+ 子类访问修饰符范围大于等于父类
+ 构造方法无法被重写（构造方法名必须与类名相同，重写方法需要和父类方法名称相同，出现矛盾）
+ 发生在运行期

**总结**：方法重写遵循两同两小一大。

1. 方法名，形参列表相同
2. 子类返回值类型和抛出异常范围更小
3. 子类访问权限比父类更大



## Java值传递

### 形参和实参

**形式参数**：Parameters。用于定义方法的参数，接收实参。

**实际参数**：Arguments。用于传递给方法的参数。



### 值传递和引用传递

**值传递：**方法接收的是实参值的拷贝，会创建副本

**引用传递：**方法接收的是实参引用的对象在堆中的地址，不会创建副本，对形参的修改会影响到实参。



**Java中只有值传递！！！**

+ 如果参数是基本类型，传递的就是基本类型字面量值的拷贝
+ 如果参数是引用类型，传递的就是对象地址值的拷贝



### 为什么Java不引入引用传递

引用传递会对对象本身造成修改，影响安全性，也会提高操作的复杂度



## 面向对象

**封装：将对象的状态信息隐藏在对象内部**

**继承：使用已存在的类为基础创建新类**

+ 子类拥有父类对象的所有属性和方法（包括私有属性和方法，但是不能访问）
+ 子类可以拥有自己的属性和方法
+ 子类可以用自己的方法实现父类的方法

**多态：父类的引用指向子类的实例**

+ 对象类型和引用类型之间之间存在继承(类)/实现(接口)的关系
+ 引用类型变量发出的方法调用到底是哪个类中的方法，必须在程序运行期间才能确定
+ 多态不能调用”子类存在，父类不存在“的方法
+ 如果子类重写了方法，则执行子类的。如果子类没有重写，则执行父类的。



### 对象相等和引用相等

对象相等：内存中存放的内容相等

引用相等：指向的内存地址相等



### 接口和抽象类

**共同点：**

+ 不能被实例化
+ 都可以包含抽象方法
+ 都可以有默认实现的方法



**不同点：**

+ 类只能继承于一个类，但可以继承于多个接口

+ 接口主要用于对类的行为进行约束

  抽象类主要用于代码复用，强调从属关系

+ 接口中的成员变量只能是public static final类型的，无法被修改也必须有初始值。

  抽象类中的成员变量默认为default，可以在子类中被重新定义，也可以被重新赋值



### 深拷贝、浅拷贝、引用拷贝

深拷贝：完全复制整个对象，包括对象所包含的内部对象

浅拷贝：外部对象不同，内部对象共享

引用拷贝：外部和内部对象都共享



## Object

### ==和equals()

==：基本数据类型比较值，引用数据类型比较地址（本质都是比较引用类型变量的值，不过基本数据类型存的是数据值，引用数据类型存放的是对象地址）

equals()：重写之前等同于”==“，重写后可自定义比较功能



### hashCode()

有多种生成方式，在OpenJDK8中默认是”使用线程局部状态来实现Marsaglia's xor-shift 随机数生成“，

将元素添加进容器时，先用hashCode进行比较，再用equals比较，可以大大缩小查找成本。

+ 如果两个对象的hashCode相等，这两个对象不一定相等
+ 如果两个对象的hashCode值相等，equals返回值也为true，则这两个对象相等
+ 如果两个对象的hashCode不相等，这两个对象一定不相等





**重写equals()方法的时候必须重写hashCode()方法：**

如果不重写，可能会出现equals判断相等的两个对象，hashCode不相等的情况



## String

### String、StringBuffer、StringBuilder

|               | 可变性                      | 线程安全性 | 性能                                 |
| ------------- | --------------------------- | ---------- | ------------------------------------ |
| String        | 不可变                      | 线程安全   | 每次对其进行改变都会生成一个新的对象 |
| StringBuffer  | 继承自AbstractStringBuilder | 线程安全   | 对StringBuffer对象本身进行操作       |
| StringBuilder | 继承自AbstractStringBuilder | 非线程安全 | 性能较StringBuffer好一些             |

**总结：**

+ 操作少量数据：String

+ 单线程下操作大量数据：StringBuilder

+ 多线程下操作大量数据：StringBuffer

  

**String的不可变性：**

```Java
public final class String implements java.io.Serializable, Comparable<String>, CharSequence {
    private final char value[];
  //...
}//Java9之后，采用byte数组来存储字符串
```

+ value[]被final修饰，value存放的数组地址不可变。（数组内容还是可以变的，所以需要private）
+ value[]被private修饰，并且不提供对value进行操作的方法（确保数组内容也不可改变）
+ String类被final修饰，不能被子类继承。（子类无法破坏其不可变性）



### 字符串拼接

Java语言本身不支持运算符重载，"+"和"+="是专门为String类重载过的运算符，也是Java中仅有的两个重载过的运算符。

**+、+=：**每次拼接创建单个StringBuilder，再调用append()方法，会创建过多StringBuilder对象（JDK9之后就没有这样的问题了）

**append：**直接创建StringBuilder对象调用append()方法不会有这样的问题



**如果String的值在程序编译期无法确定，则会被视为对象。如果String的值在程序编译期可以确定，会被视为常量**

```Java
final String str1 = "str";
final String str2 = "ing";
String str3 = str1+str2;//常量池中的对象

String str4 = "str";
String str5 = "ing";
String str6 = str4+str5;//String对象

final String str7 = "str";
final String str8 = getStr();
String str9 = str7+str8; // String对象
public static String getStr() {
      return "ing";
}

```



字符串对象引用用"+"进行拼接时，返回的是一个String对象

但如果用final声明字符串对象引用，将会被视为常量池中的常量



### 字符串常量池

位于堆内存中，池中的字符串对象是唯一的



### String#intern

String.intern()是一个native方法，用于将指定的字符串对象的引用保存在字符串常量池中

+ 如果常量池中保存了该引用：直接返回该引用
+ 如果常量池中没有保存该引用：在常量池中创建一个指向该字符串对象的引用并返回



## 异常

### Exception和Error

java.lang.Throwable

+ Exception：程序本身可以处理的异常。通过catch捕获。
  + Checked Exception：受检查异常，必须处理（不catch或者throw的话就没办法编译通过）
  + Unchecked Exception：不受检查异常，可以不处理（不catch或者throw的也可以编译通过）
+ Error：程序无法处理的错误



### try-catch-finally

try：用于捕获异常，其后可以接零个或多个catch块，如果没有catch块则必须跟一个finally块

catch：用于处理try捕获到的异常

finally：无论异常是否被捕获，finally块中的语句都会被执行

**注意：**在一些特殊情况下，finally中的代码不会被执行。例如：程序所在的线程死亡，执行finally之前虚拟机被终止运行，关闭CPU



### try-with-resources

用于自动管理资源的释放，和try-catch-finally相比在代码结构上更加简洁





## 泛型

### 为什么要实现泛型

+ 更好地实现代码重用，无需为每种类型编写独立的方法
+ 可以在编译时检查代码，避免运行时进行类型转换，提高程序性能



### 泛型类、泛型接口、泛型方法

```Java
//泛型类。实例化时必须指明泛型类型
public class Generic<T>{
    private T key;

    public Generic(T key) {
        this.key = key;
    }

    public T getKey(){
        return key;
    }
}

//泛型接口。实现时可以不指定类型
public interface Generator<T> {
    public T method();
}

//泛型方法
public static <E> void printArray(E[] inputArray){
    for (E element:inputArray){
       System.out.printf( "%s ", element );
    }
    System.out.println();
}
```



## 反射

定义：以编程的方式解析类中的各种成分





## 注解

JDK5引入的特性，可被视为一种特殊的注释（可被人和程序读取），本质是一个继承了Annotation的接口



### 注解的解析方式

+ 编译期直接扫描
+ 运行期通过反射处理



## SPI

Service Provider Interface

### SPI和API的区别

API：接口存在于实现方。

SPI：接口存在于调用方。调用方确定接口规则，由不同厂商根据规则实现接口。



### SPI的优缺点

优点：提高接口设计灵活性

缺点：需要遍历加载所有实现类，不能按需加载；多个ServiceLoader同时load时会出现并发问题



## 序列化和反序列化

### 什么是序列化与反序列化

序列化：将数据结构或对象转换成可取用格式

反序列化：将可取用格式转换成数据结构或对象



### 不想序列化的字段

不想序列化的字段使用transient关键字修饰

**注意：**

+ transient只能修饰变量，不能修饰类和方法
+ transient修饰的变量在反序列化后会变为类型的默认值
+ static变量不属于对象，属于类，所以无论有没有transient关键字修饰，都不会被序列化



### JDK自带序列化

+ 不支持跨语言调用
+ 序列化后的字节数组体积较大，传输成本会增大
+ 存在安全问题，反序列化的数据可以被用户控制从而出现非预期对象

因此一般使用Hessian，Kryo，Protobuf等序列化协议





## 语法糖

**Syntactic sugar：指的是编程语言为了方便程序员开发程序而设计的一种特殊语法**

Java中真正支持语法糖的是Java编译器而不是JVM。Java语法糖在执行时需要先经过编译器进行解糖，JVM才能正确识别。



### 常见语法糖

泛型、自动拆装箱、变长参数、枚举、内部类、增强for循环、try-with-resources、lambda表达式等





## I/O

### 为什么要区分字节流和字符流





UTF-8：英文占1个字节，中文占3个字节

Unicode：任何字符都占2个字节

GBK：英文占1个字节，中文占2个字节

### Java IO中的设计模式



### BIO、NIO、AIO