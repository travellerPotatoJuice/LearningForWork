# 选择题

**1.ACD**

+ 从Collection的strea()方法获取

+ 从Arrays.stream(Object [])获取

+ 从Stream类的静态方法中获取

+ 从Files类的lines（）方法中获取

  ...........共有七种获取途径



**2.ABD**

**3.ABD**

**4.A**

+ 过滤长度不为3的名字，跳过前两个元素，最后计数

**5.CD**

+ listFiles()用于获取当前目录下所有的一级文件对象
+ File类属于java.io包

**6.B**

+ File类只能对文件本身进行操作，不能读写文件里面存储的数据。IO类才用于读写数据

**7.A**

**8.D**

**9.C**

+ 由于递归需要频繁进行方法调用和栈的创建和销毁，递归效率往往比迭代低
+ 递归一定要有出口，否则会无限递归
+ 即使有出口条件，递归次数也受栈的大小的制约

**10.AC**



# 今日方法

1. Map集合中的常用方法名，以及方法功能：

   ```java
   答：
   //获取Map大小
   public int size();
   
   //清空map集合
   public void clear();
   
   //判断Map是否为空
   public boolean  isEmpty();
   
   //根据键值获取对应值
   public V get(Object obj);
   
   //根据键值删除整个元素，并返回键值对应的元素
   public V remove(Object key);
   
   //判断是否包含某个键
   public boolean containsKey(Object key)
   
   //判断是否包含某个值
   public boolean containsValue(Object value)
   
   //获取Map集合的全部键，以Set的形式返回
   public Set<K> keySet();
   
   //获取Map集合的全部值，以Collection的形式返回
   public Collection<V> values();
   ```

2. Stream流中的常用方法名，以及方法功能：

   ```java
   答：
   //获取集合的Stream流
   default Stream<E> stream();
   
   //获取当前数组的stream流
   public static <T> Stream<T> stream(T[] array); 
   
   //获取当前接收数据的stream流
   public static <T> Stream<T> of(T...values);
   //用于根据指定的条件过滤流中的元素
   Stream<T> filter(Predicate <? super T> predicate);
   
   //对流中元素进行排序
   Stream<T> sorted();
   
   //重载的sorted方法，基于自定义的比较规则
   Stream<T> sorted(Coomparator<? super T> comparator);
   
   //用于限制流中元素的数量
   Stream<T> limit(long maxSize);
   
   //用于跳过流中的元素
   Stream<T> skip(long n);
   
   //去除流中(地址不同)的重复元素，如果需要内容一样就认为重复要重写hashCode和equals
   Stream<T> distinct();
   
   //用于将流中的每个元素映射成另一个元素。
   <R> Stream<R> map(Function<? super T, ? extends R> mapper);
       
   //将两个流进行合并，如果流的数据类型不一样，默认合并成Object类型的流
   static <T> Stream<T> concat(Stream a, Stream b);
   
   //对流中的每个元素执行指定的操作
   void forEach(Consumer action);
   
   //用于统计流中元素的数量
   long count();
   
   //根据指定的比较规则找出值最大的对象
   Optional<T> max(Comparator<? super T> comparator);
   
   //根据指定的比较规则找出值最小的对象
   Optional<T> min(Comparator<? super T> comparator);
       
   //将元素收集到一个集合中
   R collect(Collector collector);
   List<Student> student1 = students.stream().filter(a -> a.getHeight() > 170).collect(Collectors.toList());
   
   //将流中的元素转换为一个数组。
   Object[] toArray();
   ```

3. File对象中的常用方法名，以及方法功能：

   ```Java
   答：
   //创建一个File对象
public File(String pathname);
   
   //根据父路径和子路径名字创建文件对象
   public File(String parent, String child);
   
   //根据父路径对应文件对象和子路径名字创建文件对象
   public File(File parent, String child);
   
   //判断当前文件对象，对应的文件路径是否存在，存在返回true
   public boolean exists();
   
   //判断当前文件对象指代的是否是文件
   public boolean isFile();
   
   //判断当前文件对象指代的是否是文件夹
   public boolean isDirectory();
   
   //获取文件名称
   public String getName();
       
   //获取文件大小,返回字节数
   public long length();
   
   //获取文件最后修改时间
   public long lastModified();
   
   //获取创建文件对象时，使用的路径
   public String getPath();
   
   //获取绝对路径
   public String getAbsolutePath();
   
   //判断当前文件对象，对应的文件路径是否存在，存在返回true
   public boolean exists();
   
   //判断当前文件对象指代的是否是文件
   public boolean isFile();
   
   //判断当前文件对象指代的是否是文件夹
   public boolean isDirectory();
   
   //获取文件名称
   public String getName();
       
   //获取文件大小,返回字节数
   public long length();
   
   //获取文件最后修改时间
   public long lastModified();
   
   //获取创建文件对象时，使用的路径
   public String getPath();
   
   //获取绝对路径
   public String getAbsolutePath();
   
   //获取当前目录下所有的一级文件名称
   public String[] list();
   
   //获取当前目录下所有的一级文件对象
   public File[] listFiles();
   ```
   
   

# 简答题

1. **HashMap和LinkedHashMap集合有何区别？什么情况下使用哪种集合？**

   + HashMap的底层数据结构是哈希表，LinkedHashMap在其基础上增加了一个双向链表，用于维护元素的顺序。所以HashMap中的元素是无序的，而LinkedHashMap中的元素是有序的。
   + 如果对元素顺序没有要求，可以选择HashMAp；如果需要保持元素的访问顺序，建议选择LinkedHashMap。
   + 

2. **Stream流是什么？用来干什么的？**

   Stream流是JDK8新增的一套API，用于操作集合或者数组中的数据
   
   

3. **Stream流有哪些特点？**

   + Stream流本身是不可变的，使用Stream流进行操作的时候，原始数据不会发生改变。
   + Stream流使用惰性求值的方式进行操作。Stream流分成中间操作和终止操作，中间操作会返回一个新的Stream流对象，但不会立即执行，只有执行终止操作的时候，整个操作流才会被触发。
   + Stream流支持将多个操作串联起来形成一个流水线，从而实现复杂的数据处理流程

   

4. **Stream流和IO流是一样的吗？分别用来干什么的？**

   不一样。

   + Stream流用于对集合数据进行流式处理，例如对数据进行筛选、转换、聚合等。其本质是用一种简洁高效的方式处理数据。
   + IO流用于读写数据，可以用于数据传输、文件处理、网络编程等。

   

5. **File类用来干什么的？**

   是用来对文件本身进行操作的，例如获取文件的属性，对文件进行增删。但不能读写文件里存储的内容。

   

6. **什么是递归？需要注意什么？**

   + 递归就是函数或算法在运行的时候调用自身的过程，分成直接递归和间接递归。直接递归是在函数或方法中直接调用自身，间接递归是多个函数和方法之间相互调用实现递归链。

   + 注意事项：

     + 在设计递归方法的时候要留出递归出口，避免递归陷入无限递归；

     + 由于递归操作会进行压栈处理，所以在递归的时候要注意递归深度，避免出现栈溢出的问题；

     + 递归会导致重复计算的问题，从而降低运算的性能，设计递归算法时可以引入一些记忆方法避免重复的计算。

       

# 排错题

1. 不能正常运行。由于Stream是一次性使用的，在使用sorted对stream进行排序之后，这个流就关闭了，无法再调用forEach()方法。应该将代码改成：

   ```Java
   public static void main(String[] args) {
           List<Integer> list = Arrays.asList(6, 3, 4, 5, 1, 2);
           Stream<Integer> stream = list.stream();
           stream.sorted((o1, o2) -> o2 - o1);
           stream.forEach(System.out::println);
           stream.close();
       }
   ```

2. sorted中重写的比较器内容错误，原代码是升序排序。

   Collectors.joining()方法用于连接字符串，并且要求流的类型是CharSequence（String，StringBuilder等），原题中的写法获取的是Stream<Student>类型的流，Collectors.joining()无法对其进行转换。

   应该将代码修改为：

   ```java
   public static void main(String[] args) {
           Student s1 = new Student("郭靖", 175);
           Student s2 = new Student("黄蓉", 155);
           Student s3 = new Student("黄药师", 180);
           Student s4 = new Student("欧阳锋", 170);
           Student s5 = new Student("穆念慈", 160);
           List<Student> list =Arrays.asList(s1, s2, s3, s4, s5);
           // 需要将集合中的Student对象，按照下面需求完成代码：
           // 1. 先将身高不足160的学生过滤
           // 2. 再按照年龄进行降序排序
           // 3. 最后将name属性，拼接成一个字符串，之间使用'-'隔开
           Stream<Student> stream1 = list.stream().filter(s -> s.getHeight() >= 160);
           Stream<Student> stream2 = stream1.sorted((o1, o2) -> o2.getHeight() - o1.getHeight());
           String collect = stream2.map(Student::getName).collect(Collectors.joining("-"));
           System.out.println(collect);
       }
   ```

   

3. TreeMap默认按照key进行排序，是不能按照Values排序的。如果一定要将Map中的结果按照values排序，可以将Map中的键值对提取到List中排序再进行输出：

   ```java
   public static void main(String[] args) {
           // 要求map按照中的数据，按照学生的年龄进行降序排序。
           Map<String,Student> map = new TreeMap<>();
           Student s1 = new Student("郭靖", 175);
           Student s2 = new Student("黄蓉", 155);
           Student s3 = new Student("黄药师", 180);
           Student s4 = new Student("欧阳锋", 170);
           Student s5 = new Student("穆念慈", 160);
           map.put("南京",s1);
           map.put("上海",s2);
           map.put("长沙",s3);
           map.put("西安",s4);
           map.put("成都",s5);
   
           List<Map.Entry<String,Student>> list = new ArrayList<>(map.entrySet());
           list.sort((name1, name2) -> name2.getValue().getHeight() - name1.getValue().getHeight());
           for(Map.Entry<String,Student> entry : list){
               System.out.println(entry);
           }
       }
   ```

   

4. 执行结果是ClassCastException。因为在使用TreeMap时没有提供Comparator时，TreeMap会默认使用键的自然顺序来进行排序，而在本题中键的类型是Student，如果Student类没有实现Comparable接口，或者构造TreeMap时没有提供自定义的Comparator，会出现ClassCastException。解决方法如下：

   ```java
   public static void main(String[] args) {
           TreeMap<Student, String> treeMap = new TreeMap<>(Comparator.comparing(Student::getName));
           treeMap.put(new Student("111", 16), "北京");
           treeMap.put(new Student("222", 16), "西安");
           treeMap.forEach(((student, address) -> System.out.println(student + "----->" + address)));
   }
   ```

   

