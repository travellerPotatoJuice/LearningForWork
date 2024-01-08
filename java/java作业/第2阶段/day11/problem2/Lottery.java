package com.chenhuiying.hello;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lottery {
    List<Integer> list = new ArrayList<>();
    List<Integer> one = new ArrayList<>();
    List<Integer> two = new ArrayList<>();
    public void init(){
        list.add(10);
        list.add(5);
        list.add(20);
        list.add(50);
        list.add(100);
        list.add(200);
        list.add(500);
        list.add(800);
        list.add(2);
        list.add(80);
        list.add(300);
        list.add(700);
    }

    public void solution1(){
        Random random = new Random();
        int index = random.nextInt(list.size());
        int prize = list.get(index);
        System.out.println(Thread.currentThread().getName()+"又产生了一个" + prize + "元大奖");
    }

    public void solution2(){
        Random random = new Random();
        int index = random.nextInt(list.size());
        int prize = list.get(index);
        if(Thread.currentThread().getName().equals("抽奖箱1")){
            one.add(prize);
        }else{
            two.add(prize);
        }
    }

}
