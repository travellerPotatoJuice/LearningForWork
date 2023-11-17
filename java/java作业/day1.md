### 选择题

**1. A**

每个类中可以不定义静态成员变量



**2. D**

+ 子类可以继承父类的非私有成员
+ 子类中可以出现和父类一样的成员方法，按照自己的需求去进行方法重写
+ 一个类只能继承一个父类



**3.ABC**

+ 子类中可以出现和父类一模一样的成员方法



**4.C**

父类中被private权限修饰的方法不会被子类继承，自然也不会被重写



**5.D**

static修饰的成员变量如果没被初始化，编译器会自动将其进行初始化，初始化的值取决于成员变量类型



**6.ACD**

静态成员方法无法直接访问非静态成员变量，想要访问的话必须创建对象实例再进行访问



**7.A**

加载类的时候创建静态代码块，创建对象的时候执行实例代码块，实例代码块会先于构造器执行



**8.C**



**9.AD**

+ 实例变量是没有static修饰的变量
+ 静态变量不一定要赋值，如果没有赋值，系统会进行初始化



**10.D**



**11.ACD**



**12.A**





### 今日单词

1. 静态：static
2. 单例：single instance
3. 继承：extends
4. 重写：override
5. 四种权限修饰符：public，private，default，protected





### 简答题

1. **static可以用来修饰什么？各自有何特点？**

   答：static可以用来修饰变量和方法。

   被static修饰的变量称作静态变量/类变量，在类加载时一同创建，被类的所有对象一同共享。

   被static修饰的方法乘坐静态方法/类方法可以直接访问类成员，但不可以直接访问实例成员。

   

2. **static注意事项有哪些？**

   答：在被static修饰的方法中不能出现this关键字。被static修饰的变量在多线程的情况下需要注意安全。

   

3. **单例设计模式是什么意思？什么是懒汉式？什么是饿汉式？**

   答：单例设计模式是确保一个类只能创建出一个对象。

   懒汉式：先不创建对象，等需要获得对象的时候，如果对象是null就创建一个，如果不是null就返回那个已有的对象

   饿汉式：提前创建好一个私有的静态对象，在外部通过公用的静态方法获取对象时将提前创建好的对象返回给外部

   

4. **代码块有几种？分别有何特点和作用？**

   答：有两种，分别是静态代码块和实例代码块

   静态代码块：用花括号扩起来并用static修饰，在类加载的时候自动执行，用于实现对类的初始化。

   实例代码块：直接用花括号扩起来，无需static修饰，每次创建对象的时候会执行实例代码块，并且在构造方法之前执行，用于实现对对象的初始化。

   

5. **继承是什么？为什么要在程序中使用继承？**

   答：继承就是创建一个类extends另一个类。在程序中使用继承能提高代码的复用率，提高了代码的可读性，也能帮助实现多态。

   

6. **方法重写和方法重载是什么样的？**

   答：

   方法重写：是子类和父类有名字相同并且参数列表也相同的方法时，子类可以对方法进行重写，实现自己的需求。重写后，方法的访问遵循就近原则

   方法重载：当有多个同名的方法时，如果参数列表不一样，那么就是实现了方法重载

   

7. **子类构造器有什么特点**

   答：子类构造器的第一行默认会有一个super();（不管写不写都默认存在），如果父类没有无参构造器，那么不写super()会报错，这个时候就需要手动写一个super(参数列表);来调用父类的有参构造器

   

8. **this和super有哪些作用？**

   答：当子类和父类有同名的变量时，可以用this和super指明具体的变量。比如说子类和父类都有一个叫name的成员变量，那么在子类中，this.name指的就是子类的成员变量，super.name指的就是父类的成员变量。



### 排错题

1.有问题。Zi 的父类是Fu，Fu的父类是Ye，所以Zi只会继承add()和show()，无法调用ErShu中的method方法

2.有问题。在B的show()方法里不应该使用super和this关键字，因为show是一个类方法，所以它会在类加载时创建，但是super和this指向的是父类和子类的对象，此时没有父类和子类的对象创建，所以会出现错误。



### 代码题

#### 第一题

```java
public class Employee {
    public String name;
    public String workNum;
    public int wages;
    public void work(){

        System.out.println("hahaha");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public int getWages() {
        return wages;
    }

    public void setWages(int wages) {
        this.wages = wages;
    }
}
```

```java
public class ProjectManager extends Employee{
    private int bonus;
    public ProjectManager(String name, String workNum, int wages, int bonus) {
        this.name = name;
        this.workNum = workNum;
        this.wages = wages;
        this.bonus = bonus;
    }

    @Override
    public void work(){

        System.out.println("和"+this.getBonus()+"的奖金,盯着程序员写代码");
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
```

```java
class Programmer extends Employee{
    public Programmer(String name, String workNum, int wages) {
        this.name = name;
        this.workNum = workNum;
        this.wages = wages;
    }

    @Override
    public void work(){

        System.out.println("苦逼地写代码");
    }

}
```

```java
public class Test {


    public static void main(String[] args) {
        Employee e1 = new ProjectManager("chy1", "321321", 200, 100);
        Employee e2 = new Programmer("chy2", "123123", 100);
        printOut(e1);
        printOut(e2);
    }

    public static void printOut(Employee e){
        System.out.println("工号为"+e.getWorkNum()+"的"+e.getName()+",拿着"+e.getWages()+"的工资");
        e.work();
    }

}
```



#### 第二题

```java
public class Animal{
    String name;
    String color;
    int price;
    public void eat(){
        System.out.println(this.name+"在吃饭");
    }
    public void skill(){

    }
}
```

```java
class Dog extends Animal{
    public Dog(String name, String color, int price) {
        this.name = name;
        this.color = color;
        this.price = price;
    }

    @Override
    public void skill(){
        System.out.println(this.name+"看家");
    }

}
```

```java
public class Cat extends Animal {
    public Cat(String name, String color, int price) {
        this.name = name;
        this.color = color;
        this.price = price;
    }
    @Override
    public void skill(){
        System.out.println(this.name+"抓老鼠");
    }
}
```

```java
public class Test {


    public static void main(String[] args) {
        Animal a1 = new Dog("旺财","黑",100);
        Animal a2 = new Cat("咪咪","白",100);
        printOut(a1);
        printOut(a2);
    }

    public static void printOut(Animal a){
        a.eat();
        a.skill();
    }

}
```



#### 第三题

```java
public class PrimaryCard{
    protected double balance;
    private String cardNum;
    private String owner;

    public PrimaryCard() {
    }

    public PrimaryCard(double balance, String cardNum, String owner) {
        this.balance = balance;
        this.cardNum = cardNum;
        this.owner = owner;
        System.out.println("初始 银行卡 - 主卡 余额为："+ balance);
    }

    public void earnMoney(double money){
        balance = balance+money;
        System.out.println("用 主卡 存"+money);
        System.out.println("卡号为："+cardNum+" 户主为："+owner);
        System.out.println("存款成功，余额为"+balance);
    }

    public void getMoney(double money){
        System.out.println("用 主卡 取"+money);

        if(money>this.balance){
            System.out.println("余额不足，取款失败");
        }
        else{
            System.out.println("卡号为："+cardNum+" 户主为："+owner);
            balance = balance - money;
            System.out.println("取款成功，余额为："+ balance);
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
```



```java
class SupplementaryCard extends PrimaryCard{
    private String cardNum;
    private String owner;
    private double overdraft = 1000.0;


    public SupplementaryCard(double balance, String cardNum, String owner) {
        this.balance = balance;
        this.cardNum = cardNum;
        this.owner = owner;
        System.out.println("初始 银行卡 - 副卡 余额为："+balance);
    }

    @Override
    public void earnMoney(double money){
        super.balance = super.balance+money;
        System.out.println("用 副卡 存"+money);
        System.out.println("卡号为："+cardNum+" 户主为："+owner);
        System.out.println("存款成功，余额为"+super.balance+",透支额度："+overdraft);
    }

    @Override
    public void getMoney(double money){
        System.out.println("用 副卡 取"+money);
        if(balance>=money){
            System.out.println("卡号为："+cardNum+" 户主为："+owner);
            balance = balance - money;
            System.out.println("取款成功，余额为："+ balance+",透支额度："+overdraft);
        }
        else if(balance<money&&money<(balance+overdraft)){
            overdraft = overdraft-(money-balance);
            balance = 0;
            System.out.println("取款成功，余额为："+ balance+",透支额度："+overdraft);
        }
        else if(money>(balance+overdraft)){
            System.out.println("取款失败，余额为："+ balance+",透支额度："+overdraft);
        }
    }

}
```

```java
public class Test {


    public static void main(String[] args) {
        PrimaryCard primaryCard = new PrimaryCard(500.0,"12345678","张三");

        primaryCard.earnMoney(600);
        primaryCard.getMoney(700);
        primaryCard.getMoney(1000);
        System.out.println("---------------");
        SupplementaryCard supplementaryCard = new SupplementaryCard(primaryCard.getBalance(),"87654321","李四");
        supplementaryCard.earnMoney(100);
        supplementaryCard.getMoney(200);
        supplementaryCard.getMoney(1000);
    }


}
```

