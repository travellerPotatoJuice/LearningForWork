package com.chenhuiying.hello;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        ArrayList<LocalDate> totalRestDaysList = new ArrayList<>();
        ArrayList<LocalDate> thisMonthRestDaysList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入年份和月份:");
        String input = sc.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        YearMonth yearMonth = YearMonth.parse(input,formatter);

        LocalDate inputDate = LocalDate.of(yearMonth.getYear(),yearMonth.getMonth(),1);
        LocalDate firstRestDay = LocalDate.of(2020,2,1);

        System.out.println("最大天数:"+inputDate.lengthOfMonth());
        LocalDate inputDate2 = inputDate.withDayOfMonth(inputDate.lengthOfMonth());

        System.out.println("间隔天数:"+ChronoUnit.DAYS.between(firstRestDay,inputDate2));

        int day = 0;
        for(LocalDate date =firstRestDay; date.isBefore(inputDate2.plusDays(1)); date = date.plusDays(1)){
            if(day == 0 ){
                totalRestDaysList.add(date);
            }
            day = (day+1)%4;
        }

        for(LocalDate date : totalRestDaysList){
            if(date.isAfter(inputDate)){
                thisMonthRestDaysList.add(date);
            }
        }

        System.out.println("全部休息日："+totalRestDaysList);
        System.out.println("本月休息日："+thisMonthRestDaysList);
        System.out.println("本月休息天数："+thisMonthRestDaysList.size());
        String out = "";
        for(LocalDate ld: totalRestDaysList){
            DayOfWeek dayOfWeek = ld.getDayOfWeek();
            if(dayOfWeek == DayOfWeek.SATURDAY||dayOfWeek == DayOfWeek.SUNDAY){
                out = out + "["+ld+"] ";
            }
            else{
                out = out + ld +" ";
            }
        }
        System.out.println(out);


    }
}
