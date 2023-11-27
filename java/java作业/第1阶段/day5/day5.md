# 选择题

**1.D**

**2.A**

**3.C**

**4.B**

**5.B**

**6.D**

**7.A**

**8.A**

**9.ABCD**

**10.AD**

**11.A**

**12.B**

**13.A**

**14.C**

**15.B**

**16.B**

**17.B**

**18.BC**



# 编程题

## 题目一

```Java
public class Main{
    public static void main(String[] args) {
        System.out.println("请输入两个小数");
        Scanner sc = new Scanner(System.in);
        double a= sc.nextDouble();
        double b = sc.nextDouble();
        double min  = getMin(a,b);
        System.out.println("较小值是："+min);
    }

    public static double getMin(double a,double b){
        return (a>b)?b:a;
    }
}
```



## 题目二

```Java
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入三个整数数字: ");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int max = getMax(a, b, c);
        System.out.println("最大值: " + max);
    }

    public static int getMax(int a, int b, int c) {
        int max = Math.max(a,b);
        max= Math.max(max,c);
        return max;
    }
}
```





## 题目三

```Java
public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入1个小数：");
        double input = sc.nextDouble();
        System.out.println(Math.abs(input));
    }
}
```