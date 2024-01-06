package com.chenhuiying.hello;

public class Entry {
    public static void main(String[] args) {
        int day = 10;
        int num = 1;
        System.out.println(count(day,num));
    }

    public static int count(int day, int num) {
        if(day==1){
            return num;
        }
        else{
            return count(--day,(num+1)*2);
        }
    }


}
