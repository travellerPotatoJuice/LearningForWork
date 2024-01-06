## 一、选择题：

#####   1. 计算机中使用的ASCII码是对( )的编码.<font color=blue size=5>C</font>

A.英文字母

B.英文字母和数字

C.英文字符集

D.英文字符和中文字符

##### 2.标准ASCII码字符集共有(  )个字符编码。 <font color=blue size=5>D</font>

A.256

B.127

C.255

D.128

##### 3. Unicode(utf-8)和GBK编码描述正确的是（）<font color=blue size=5>C</font>

A. utf-8只支持中文

<font color=blue size=3>UTF-8支持多种语言字符集</font>

B. GBK只支持中文

<font color=blue size=3>GBK支持简体和繁体中文，GB2312只支持简体中文。都是支持英文的</font>。

C. Unicode和GBK字符集支持英文也支持中文 

D. GBK不支持中文繁体字

<font color=blue size=3>支持繁体</font>

##### 4. 字节输出流的抽象基类是( ) <font color=blue size=5>A</font>

A. OutputStream类  

B. InputStream类

C. Writer类

D. Reader类

##### 5. 如果在当前目录下不存在Hello.txt 文件，试图编译和运行下面代码会输出什么<font color=blue size=5>C</font>

```java
import java.io.*;
public class Mine {
    public static void main(String argv[]){
         Mine m=new Mine();
         System.out.println(m.amethod());
    }
    public int amethod() {
         try {
             FileInputStream dis= new FileInputStream("Hello.txt");
         }catch (FileNotFoundException fne) {
             System.out.println("No such file found");
             return -1;
         }catch(IOException ioe) {
         } finally{
             System.out.println("Doing finally");
         }
         return 0;
    }
}
```

A. No such file found
B. No such file found ,-1
C. No such file found, Doing finally, -1	
D. 0

##### 6. 下面程序的运行结果是( ) <font color=blue size=5>D</font>

```java
public static void main(String argv[]){ 
   FileOutputStream fos = newFileOutputStream(“c:\demo.txt”);
   fos.write("abc");   
   fos.close();
}
```

A. 在C盘创建文件demo.txt,但文件是空的

B. 在C盘创建文件demo.txt,并写入数据abc

C. 将C盘已有的文件demo.txt中追加写入abc

D. 编译失败

 ##### 7. 当方法遇到异常(编译时异常)又不知如何处理时，下列哪种说法是正确的()<font color=blue size=5>C</font>

A. 捕获异常（try{…}catch{…}）<font color=blue size=3>

B. 抛出异常(throw 异常对象)

C. 声明异常（throws 异常类型）

D. 嵌套异常

##### 8. 【多选题】下面关于try-catch-finally结构的说法错误的是() <font color=blue size=5>AB</font>

A.—个try块后面必须至少带一个catch块或finally块

B.—个try块后面可以带一个或者多个catch块或者finally块

C.try-catch-finally结构中finally块可以没有

D.try-catch-finally结构中如有finally块，则finally块一定被执行 


##### 9. 下面的程序创建了一个文件输出流对象，用来向文件test.txt中输出数据，假设程序当前目录下不存在文件test.txt，编译下面的程序Test.java后，将该程序运行3次，则文件test.txt 的内容是()<font color=blue size=5>D</font>

```java
import java.io.*;
public class Test {
	public static void main(String args[]) {
		try {
			String s="ABCDE ";
			byte b[]=s.getBytes();
			FileOutputStream file= new FileOutputStream("test.txt",true); //看这里
			file.write(b);
			file.close();
		}
		catch(IOException e) {
			System.out.println(e.toString());
		}
	}
}
```

A. ABCABC

B. ABCDE

C. Test

D. ABCDE ABCDE ABCDE

##### 10.  下面说法不正确的是 (    ) <font color=blue size=5>D不确定</font>

A. final是个关键字，用来修饰成员变量(常量)、成员方法(不能被重写)、类(不能被继承)

B. finally是异常处理的一部分，它里面的代码正常情况下永远被执行

C. finalize是Object类中定义的，用于垃圾回收，由垃圾回收器调用。

D. final、finally、finalize三个关键字都可以在类的内部

##### 11. 【多选题】如果需要从文件中读取数据，则可以在程序中创建哪一个类的对象（）<font color=blue size=5>AD</font>

A.FileInputStream

B.FileOutputStream   

C. DataOutputStream   

D. BufferedInputStream

##### 12. 文件输出流的构造方法是public FileOutputStream(String name,Boolean append) throwsFileNotFoundException，当参数append的值为true时，表示()<font color=blue size=5>B</font>

A.创建一个新文件

B. 在原文件的尾部添加数据

C. 覆盖原文件的内容

D. 在原文件的指定位置添加数据

------

## 二、今日方法：

1. **String类中能完成编码、解码的方法名：**

   + 编码方法：getByte(); getBytes(String charsetName)
   + 解码方法：String(byte[] bytes); String(byte[] bytes, String charsetName)

   

2. **IO流中的字节输入流所涉及到的类名，以及方法名：**

   + FileInputStream：available(); read(); 
   + BufferedInputStream：available(); read();
   + DataInputStream：read(); readBoolean(); readByte(); readInt();

   

3. **IO流中的字节输出流所涉及到的类名，以及方法名：**

   + FileOutputStream：write();
   + BufferedOutputStream：flush(); write()
   + DataOutputStream：flush(); size(); write(); writeFloat(); writeInt();

------

## 三、简答题：

1. **什么是字符集，有哪些常见的字符集？**

   + 字符集是计算机中用于表示字符的编码方案，定义了字符与二进制编码之间的映射关系

   + 常见的字符集有

     + ASCII：用于表示英文字符，能表示128个字符。
     + GB2312：国家标准。共收录了6763个汉字字符和682个非汉字字符
     + GBK：国家标准扩展。在国家标准的基础上增加了超过20000个汉字。支持简体和繁体
     + Unicode：统一码。使用固定长度编码，能表示的范围比UTF-8更广。
     + UTF-8：Unicode的一种编码方式，使用可变长的编码方式，只能表示Unicode字符集中的一部分。

     

2. **UTF-8和GBK有什么区别？**

   + 编码方式不同。UTF-8可变长，用1~4个字节表示不同的字符。GBK采用双字节编码。
   + 支持范围不同：UTF-8支持表示的字符更多。GBK主要针对中文进行编码

   

3. **IO流是用来做什么的？IO流如何分类的？**

   + IO流用于在程序和外部数据源之间进行输入和输出操作。

   + 分类

     + 根据流的方向分：Input（输入流），Output（输出流）
     + 根据流中数据的最小单位分：字节流，字符流

     

4. **IO流中的字节流可以操作什么类型的文件？有哪些注意事项？**

   + 字节流可以用于读写所有类型的二进制文件，不适合读写文本文件（文本文件采用字符编码，用字节流进行读写时可能会导致无法正确解析）

   + 注意事项：

     + 需要考虑读写过程中的异常，及时进行异常捕获及处理
     + 使用完流之后要及时关闭流以释放资源
     + 在进行大文件操作的时候，最好考虑使用缓冲来提高性能
     + 在多线程的情况下需要考虑同步机制

     

5. **try...catch...finaly中的finaly用来干嘛的？有何特点？能不能直接写try...finally？**

   + 用途：finally通常被用于释放资源，断开连接等清理工作
   + 特点：finally中的代码会在try块和catch块结束后执行，即便try或者catch块中出现异常，或者有return语句，finally块中的语句也一定会执行。
   + <font color=blue>（不确定）</font>可以直接写try...finally。

   


------

## 四、排错题：

##### 排错题1：

可以正常运行。结果是222\n 333\n 3

```java
// 以下代码是否能正常运行？结果是什么？
public class Demo3 {
       public static void main(String[] args) {
        int a = show();
        System.out.println(a);
    }

    public static int show() {
        try {
            System.out.println(1 / 0);
            System.out.println(111);
            return 1;
        } catch (Exception e) {
            System.out.println(222);
            return 2;
        } finally {
            System.out.println(333);
            return 3;
        }
    }
}
```

##### 排错题2：

因为编码和解码过程使用了不同的字符集，所以运行结果会是一堆乱码。getBytes()方法默认使用的是UTF-8字符集。

解决方式：

+ 方式一：把s.getBytes()改成s.getBytes("gbk");
+ 方式二：把new String(bytes,"gbk")改成new String(bytes,"UTF-8"); 

```java
// 以下代码运行后，控制台输出结果是什么？ 如何解决？
public class Demo3 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String s = "黑马IT全国第一";
        // 编码
        byte[] bytes = s.getBytes();
        // 解码
        String s2 = new String(bytes,"gbk");
        System.out.println(s2);
    }
}
```



##### 排错题3：

运行不会报错，但存在问题。文件中会内容会变成：中国你好！你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~你好呀~....

该代码的含义是，当fis读取到内容的时候，会一直往文件中写入”你好呀~“，所以输入流能一直读到数据，输出流会一直往外写数据。

```java
// 以下代码，运行后会有问题吗？为什么？文件中会有什么数据？
// 文件abc.txt已有数据：中国你好！
public class Demo4 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("d:\\abc.txt");
        FileOutputStream fos = new FileOutputStream("d:\\abc.txt",true);
        int len;
        while((len = fis.read())!= -1){
            fos.write("你好呀~".getBytes());
        }
        fis.close();
        fos.close();
    }
}
```

------

## 五、代码题：

##### 第一题：分析以下需求，并用代码实现

**训练目标**：

​	掌握java中字节输入流和字节输出流的基本使用，以及理解其在实际开发中的应用

**需求背景**：

​	为了保证磁盘文件数据的安全性，就需要对原始文件进行加密存储，再使用的时候再对其进行解密处理。加密解密原理：对原始文件中的每一个字节数据进行更改，然后将更改以后的数据存储到新的文件中。

**需求描述**: 

​	编写一个加密解密程序。

**实现提示**：

​	1、通过字节输入流读取原始文件中的每一个字节数据然后对其进行处理

​	2、通过字节输出流将处理以后的字节数据写入到另外一个文件中

​	3、对字节数据的处理方式可以使用异或运算符异或一个数

​	4、为了提高加解密的效率可以考虑使用高效的字节输入和输出流



------

##### 第二题：	分析以下需求，并用代码实现	

**训练目标**：

​	掌握java中字节输入流和字节输出流的基本使用

**需求背景**：

​	将D盘中某文件夹中的所有文件，复制到E盘中某文件夹中，要求E盘复制的文件夹，必须同D盘中被赋值的文件夹名称一致，文件夹中的内容也必须一致。

**需求描述**: 

​	模仿windows中的，Ctrl + C和Ctrl + V功能，编写一个文件复制程序。

****实现提示****：

	1. 必须使用递归，才能拿到D盘中的每一个文件和文件夹，进行复制
	2. 通过File对象可以获取文件夹和文件的名称，这样才能保证文件名一致
	3. 使用IO流中的字节流对文件进行复制

------

##### 第三题：【选做题】分析以下需求，并用代码实现

**训练目标**：

​	掌握java中字节输入流和字节输出流的基本使用。

**需求描述**：

​	文件复制的同时，在控制台显示文件复制的进度，进度以百分比表示。

**实现提示**：

```java
DecimalFormat df = new DecimalFormat("##%");
String format = df.format(3.0 / 10);
System.out.println(format);  // 30%
```

![](C:/Users/83538/Desktop/第二阶段：基础加强13天课程（资料）/全部作业/day09作业/作业09/image/1.jpg)





