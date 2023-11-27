package com.chenhuiying.hello;

import java.util.*;

public class Entry {
    public static void main(String[] args) {
        TreeSet<Integer> balls = new TreeSet<>();
        for(int i=1;i<34;i++){
            balls.add(i);
        }
        ArrayList<Integer> getRed = new ArrayList<>();
        int getBlue = 0;
        for(int i=0;i<6;i++){
            getRed.add(getBalls(balls));
        }
        remove(balls);
        getBlue = getBalls(balls);
        System.out.println("红球："+getRed.toString()+"  蓝球："+getBlue);


    }
    public static int getBalls(TreeSet<Integer> balls){
        Random ball= new Random();
        int index = ball.nextInt(balls.size());
        Iterator<Integer> iterator = balls.iterator();
        int j = 0;
        while(iterator.hasNext()){
            int n = iterator.next();
            if(j==index){
                iterator.remove();
                return n;
            }
            j++;
        }
        return 0;
    }

    public static void remove(TreeSet<Integer> balls){
        Iterator<Integer> iterator = balls.iterator();
        while(iterator.hasNext()){
            int n = iterator.next();
            if(n>16)iterator.remove();
        }
    }
}