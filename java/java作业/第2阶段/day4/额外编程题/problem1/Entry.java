package com.chenhuiying.hello.entry.function.entry.entry;


import java.util.Calendar;
import java.util.Scanner;

public class Entry {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请你输入年份");
        int year = sc.nextInt();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year,2,1);
        calendar.add(Calendar.DATE,-1);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        System.out.println(year + "年的2月有" + day + "天");
        if(day==28){
            System.out.println("今年是平年，共365天");
        }
        else{
            System.out.println("今年是闰年，共366天");
        }
    }

}
