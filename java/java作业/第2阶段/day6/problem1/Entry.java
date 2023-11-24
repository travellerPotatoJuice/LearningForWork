package com.chenhuiying.hello.entry.function.entry.entry;


import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Entry{
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>(Arrays.asList("郭靖","黄蓉","黄药师","老顽童","瑛姑"));
        ArrayList<String> actions = new ArrayList<>(Arrays.asList("吃芥末","学羊叫5声","做10个俯卧撑","喝一杯啤酒","蛙跳10个"));
        for(String s : names){
            Random random = new Random();
            int actionIndex = random.nextInt(5);
            String action = actions.get(actionIndex);
            System.out.println(s+"抓到的阄为："+action);
        }

    }
}

