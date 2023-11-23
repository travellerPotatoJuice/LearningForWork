package com.chenhuiying.hello.entry.function.entry.entry;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Entry {

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入入职日期，格式为xxxx年xx月xx日");
        String input = sc.next();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = simpleDateFormat.parse(input);

        long joinDay = date.getTime();
        long toDay = System.currentTimeMillis();
        long result = toDay - joinDay;

        long day = result/1000/60/60/24;
        int year = (int) (day/365);
        System.out.println("您入职已有"+year+"年");

    }

}
