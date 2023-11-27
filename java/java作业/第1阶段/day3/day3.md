# 选择题

**1.D**

**2.C**

**3.B**

**4.A**

**5.B**

**6.D**

**7.D**

**8.D**

**9.D**

**10.**

**11.B**

**12.C**

**13.D**

**14.C**

**15.C**

**16.B**



# 编程题

## 第一题

```Java
public class Entry{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入三个数值");
        int a = sc.nextInt();
        int b = sc.nextInt();
        int max = 0;
        if(a>b)max = a;
        else max = b;
        int c = sc.nextInt();
        if(c>max) max = c;
        System.out.println("最大的数为:"+max);
    }
}
```



## 第二题

```Java
public class Entry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" 请输入作为程序员的你的工作的工龄:");
        int workYear = sc.nextInt();
        System.out.println(" 请输入作为程序员的你的基本工资为:");
        int wages = sc.nextInt();
        int increaseWages = 0;
        if (wages >= 10 && wages < 15) {
            increaseWages = 20000;
        } else if (wages >= 5) {
            increaseWages = 10000;
        } else if (wages >= 3) {
            increaseWages = 5000;
        } else if (wages >= 1) {
            increaseWages = 3000;
        }
        System.out.println("您目前工作了" + workYear + "年，基本工资为 "
                + wages + "元, 应涨工资" + increaseWages + "元,涨后工资" +
                (int)(wages + increaseWages) + "元");
    }
}
```



## 第三题

```Java
public class Entry {
    public static void main(String[] args) {
        int sum = 0;
        for(int i=1;i<101;i++){
            if((i%3==0)&&(i%5==0)){
                sum+=i;
                System.out.println(i);
            }
        }
        System.out.println("这些数字的和是："+sum);
    }
}
```



## 第四题

```Java
public class Entry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入大于100的三位数");
        int input  = sc.nextInt();
        int sum = 0;
        int count = 0;
        for(int i=100;i<input+1;i++){
            if((i%10!=7)&&(i/10%10!=5)&&(i/100%10!=3)){
                System.out.println(i);
                sum+=i;
                count++;
            }
        }
        System.out.println("数字的个数为"+count);
        System.out.println("数字的和为"+sum);
    }
}
```



## 第五题

```Java
public class Entry {
    public static void main(String[] args) {
        int count = 0;
        for(int i=1000;i<10000;i++){
            int first  = i%10;
            int second = i/10%10;
            int third = i/100%10;
            int fourth = i/1000%10;
            if(first+fourth == second+third){
                count++;
                System.out.println(i);
            }

        }
        System.out.println("以上满足条件的四位数总共有"+count+"个");
    }
}
```

