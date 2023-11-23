package com.chenhuiying.hello.entry.function.entry.entry;


import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Entry {

    public static void main(String[] args){
        double low = -10.8;
        double high = 5.9;
        int count = 0;
        for(double i=Math.ceil(low);i<=high;i++){
            if(Math.abs(i)>6||Math.abs(i)<2.1){
                count++;
            }
        }
        System.out.println("个数为:"+count);
    }

}
