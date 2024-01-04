# 选择题

**1.C**

**2.D**

**3.B**

**4.C**

**5.D**

**6.C**

**7.B**

**8.C**

**9.C**

**10.AC**



# 编程题

## 第一题

```Java
public class Entry {
    public static void main(String[] args) {
       int[] arrs = {1,2,3,4,5};
       for(int i=0;i<arrs.length;i++){
           System.out.print(arrs[i] + " ");
       }
    }
}
```



## 第二题

```Java
public class Entry {
    public static void main(String[] args) {
       int[] arrs = {100,50,90,60,80,70};
       int sum=0;
       for(int i=0;i<arrs.length;i++){
           sum+=arrs[i];
       }
       System.out.println("数组元素和："+sum);
    }
}
```



## 第三题

```Java
public class Entry {
    public static void main(String[] args) {
       int[] arrs = {100,50,90,60,80,70};
       int min=Integer.MAX_VALUE;
       for(int i=0;i<arrs.length;i++){
           if(arrs[i]<min){
           		min = arrs[i];
           }
       }
       System.out.println("数组中最小的元素为："+min);
    }
}
```



## 第四题

```Java
public class Entry {
    public static void main(String[] args) {
       int[] arrs = {100,50,90,60,80,70};
       int max=Integer.MIN_VALUE;
       for(int i=0;i<arrs.length;i++){
           if(arrs[i]>max){
           		max = arrs[i];
           }
       }
       System.out.println("数组中最小的元素为："+max);
    }
}
```



## 第五题

```java
public class Entry {
    public static void main(String[] args) {
        int[] arrs = {100,50,90,60,80,70};
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        int sum = 0;
        for(int i=0;i<arrs.length;i++){
            if(arrs[i]>max){
                max = arrs[i];
            }
            else if(arrs[i]<min){
                min = arrs[i];
            }
            sum+=arrs[i];
        }
        sum = sum-min-max;
        System.out.println("去掉最大值和最小值后的平均值为："+sum/(arrs.length-2));
    }
}
```

