package com.chenhuiying.hello;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        String dateString = sc.next();
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy年-MM月-dd日");
        Date date = simpleDateFormat1.parse(dateString);
        String out0 = simpleDateFormat2.format(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int year = calendar.get(Calendar.YEAR);
        String weekday = "";
        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                weekday = "星期日";
                break;
            case Calendar.MONDAY:
                weekday = "星期一";
                break;
            case Calendar.TUESDAY:
                weekday = "星期二";
                break;
            case Calendar.WEDNESDAY:
                weekday = "星期三";
                break;
            case Calendar.THURSDAY:
                weekday = "星期四";
                break;
            case Calendar.FRIDAY:
                weekday = "星期五";
                break;
            case Calendar.SATURDAY:
                weekday = "星期六";
                break;
        }


        System.out.println(out0+"是"+weekday+",是"+year+"年的第"+dayOfYear+"天");

        //""
    }
}
