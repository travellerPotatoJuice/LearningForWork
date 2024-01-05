# 选择题

**1.D**

**2.B**

**3.A**

**4.CD**

+ Map里没有contains方法

**5.BC**

**6.D**

1. Set<Map.Entry<K,V>> entrySet(); 获取所有“键值对”的集合（）
2. 增强for循环的时候，Map中的元素类型为Map.Entry<K,V>
3. getKey()和getValue()获取值和键

**7.D**

**8.C**

**9.AB**

**10.BD**

+ B.先用keySet获取所有的键值，然后对所有键值取对应的值，是正确的
+ D.用entrySet()获取所有键值对的结合，然后对所有的键值对进行遍历，是正确的

**11.C**

+ 可变参数只能在参数列表的最后一个
+ 只允许包含一个可变参数
+ 调用一个包含可变参数的方法时，可以不传入参数，一个参数，多个参数，或数组

**12.ACD**

+ 可变参数相当于一个数组，是可以打印的，不过打印出来会是一个地址值





# 简答题

1. **Set集合有什么特点？TreeSet、HashSet分别有何特点？**

   答：

   + Set集合中的元素是无序，无索引，不重复的。
   + TreeSet可排序，无索引，不重复，基于红黑树实现。
   + HashSet无序，无索引，不重复，基于哈希表实现，是一种给增删改查数据的性能都比较好的数据结构

   

2. **HashSet底层数据结构是什么？请简述？**

   答：HashSet的底层数据结构是哈希表。在JDK8之前，哈希表=数组+链表；在JDK8之后，哈希表=数组+链表+红黑树。

   + 当创建一个HashSet时，底层默认会创建一个长度为16的数组，负载因子为0.75。
   + 存入数据时，对数据进行求余运算，如果对应索引是null就存入，如果不是null就调用equals方法将要存入元素和该索引位置处已存在的元素进行比较。若相等就不存，若不相等就存。
   + 如果数组中的元素达到了（当前容量*负载因子）的长度，会对数组进行扩容到到原数组长度两倍左右的长度，并将原哈希表中的元素重新计算长度存入新哈希表，原哈希表丢弃。

   

3. **LinkedHashSet和HashSet有何区别？**

   答：LinkedHashSet是HashSet的子类，但它是有序的，它和HashSet的区别在于其额外引入了一个双链表，用于记录元素的顺序

   

   

4. **可变参数本质是什么？使用时需要注意什么？**

   答：本质是一个数组

   注意事项：

   + 可变参数对外是可变参数，对内是数组

   + 一个形参列表中，只能有一个可变参数
   + 可变参数必须放在形参列表的最后面

   

5. **TreeSet集合中两个比较器的区别？什么时候使用？**

   答：

   + 让TreeSet中存储的类实现Comparable接口，在类中重写compareTo方法指定比较的规则
   + 通过调用TreeSet集合有参数的构造器（在参数列表里创建Comparator对象，然后重写compare方法）

   

6. **Map集合有什么特点？TreeMap、HashMap分别有何特点？**

   答：

   + Map集合是双列集合，其中的元素都以<Key,Value>的形式存在
   + TreeMap：按照大小默认升序排序，不重复，无索引
   + HashMap：无序，不重复，无索引

   

7. **Map集合能直接遍历吗？有几种遍历方式？**

   答：不能直接遍历，有三种遍历方式：

   + 利用keySet方法获取键的集合，然后利用get方法根据键获取值
   + 利用Set<Map.Entry<K,V>> entrySet();获取所有键值对集合，遍历该集合中的所有元素。
   + 利用JDK8后新增的forEach方法，在参数列表中new 一个BiConsumer的匿名内部类，再用Lambda表达式改写。



# 排错题

1. TreeSet是一个有序的集合，所以在创建TreeSet时没有指定比较器，并且Student类没有实现Comparable接口时会出现这个错误。

   + 如果想先按照学生的年龄进行从大到小排序，则需要将代码更改如下：

     ```Java
     public class Student implements Comparable<Student>{
         private int age;
         private String name;
     
         public Student(int age,String name){
             this.age = age;
             this.name = name;
         }
     
         @Override
         public String toString() {
             return "Student{" +
                     "age=" + age +
                     ", name='" + name + '\'' +
                     '}';
         }
     
         @Override
         public int compareTo(Student o) {
             return o.age-this.age;
         }
     
     }
     ```

     

   + 如果年龄相同再按照学生的姓名进行排序，则需要将代码更改如下：

     ```Java
     public class Student implements Comparable<Student>{
         private int age;
         private String name;
     
         public Student(int age,String name){
             this.age = age;
             this.name = name;
         }
     
         @Override
         public String toString() {
             return "Student{" +
                     "age=" + age +
                     ", name='" + name + '\'' +
                     '}';
         }
     
         @Override
         public int compareTo(Student o) {
             if(o.age!=this.age)return o.age-this.age;
             return this.name.compareTo(o.name); //调用字符串的compareTo方法
         }
     }
     ```

     

2. Set集合中最终会有五个元素，因为Set中的元素是不重复的。

3. 会报错。Person类里没有有参构造器，同时set是一个TreeSet类型的对象，所以需要声明比较器。将代码改成：

   ```Java
   import java.util.Set;
   import java.util.TreeSet;
   
   
   class Person implements Comparable<Person>{
       private int idCard;
       private String userName;
       public  Person(int idCard, String userName){
           this.idCard = idCard;
           this.userName = userName;
       }
       @Override
       public int compareTo(Person p) {
           return p.idCard-this.idCard;
       }
   
       @Override
       public String toString() {
           return "Person{" +
                   "idCard=" + idCard +
                   ", userName='" + userName + '\'' +
                   '}';
       }
   
   }
   
   public class Entry {
       public static void main(String[] args) {
           Set<Person> set = new TreeSet<>();
           set.add(new Person(154, "zhaoliu"));
           set.add(new Person(101, "zhangsan"));
           set.add(new Person(101, "zhangsan"));
           set.add(new Person(133, "wangwu"));
           set.add(new Person(115, "zhangsan"));
           set.add(new Person(115, "zhangsan"));
           set.add(new Person(112, "lisi"));
           System.out.println(set);
       }
   }
   ```
4. 可以正确遍历，如果把key换成String类型就不能正确遍历了，需要将代码改成：

   ```Java
   public class Entry{
       public static void main(String[] args) {
           Map <String,String> map = new HashMap<>();
           map.put("1","a");
           map.put("2","b");
           map.put("3","c");
           map.put("4","d");
           map.put("5","e");
   
           Set<String> keys = map.keySet();
           for (String key: keys) {
               String value = map.get(key);
               System.out.println(key + " - " + value);
           }
       }
   }
   ```

   