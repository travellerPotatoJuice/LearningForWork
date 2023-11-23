package com.chenhuiying.hello.entry.function.entry.entry;


import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Entry {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入新商品的价格");
        double num = sc.nextDouble();
        double[] oldPrice = {23.6,34.4,35.3,36.2,78.8,89.6,100.3};
        double[] newPrice = new double[oldPrice.length+1];
        System.arraycopy(oldPrice,0,newPrice,0,oldPrice.length);
        newPrice[newPrice.length-1] = num;
        Arrays.sort(newPrice);
        System.out.println(Arrays.toString(newPrice));
    }

}
