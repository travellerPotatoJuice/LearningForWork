# 选择题

**1.C**

**2.D**

**3.C**

**4.B**

**5.B**

**6.C**

**7.B**

**8.C**

**9.B**

**10.B**

**11.A**

**12.B**

**13.C**

**14.B**

**15.D**

**16.C**

**17.AC**



# 编程题

## 编程题一

```Java
public class Entry {

    public static void main(String[] args) {
        int father = 177;
        int mother= 165;
        System.out.println("儿子身高为："+getSunHeight(father, mother));
        System.out.println("女儿身高为："+getDaughterHeight(father, mother));
    }

    public static int getSunHeight(int father, int mother){
        return (int)((father+mother)*1.08/2);
    }

    public static int getDaughterHeight(int father, int mother){
        return (int)((father*0.923+mother)/2);
    }

}
```



## 编程题二

```java
 public static void main(String[] args) {

        int num  = 1234;
        int four = num/1000%10;
        int three = num/100%10;
        int two = num/10%10;
        int one = num %10;

        System.out.println(num+"的个位是" + one + ",十位是" + two + ",百位是" + three + ",千位是" + four);

    }
```





## 编程题三

```Java
public static void main(String[] args) {

       int yuxiangrousi = 24;
       int huashengmi = 8;
       int rice = 3;
       int price = yuxiangrousi*3+huashengmi*3+rice*5;
       price = (price>100)?(int)(price*0.9):price;
       System.out.println(price);

    }
```





## 编程题四

```Java
public static void main(String[] args) {

       int tiger1 = 180;
       int tiger2 = 200;
       boolean result = tiger1==tiger2? true: false;
       System.out.println(result);

   }
```





## 编程题五

```java
public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入第一个和尚的身高");
        int first = sc.nextInt();
        System.out.println("请输入第二个和尚的身高");
        int second = sc.nextInt();
        System.out.println("请输入第三个和尚的身高");
        int third = sc.nextInt();
        int tall = Math.max(first,second);
        tall = Math.max(tall,third);
        System.out.println("最高的身高为"+tall+"cm");


    }
```

