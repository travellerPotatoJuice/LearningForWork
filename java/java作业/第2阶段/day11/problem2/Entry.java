package com.chenhuiying.hello;


public class Entry {

    public static void main(String[] args) throws InterruptedException {
        Lottery lottery = new Lottery();
        lottery.init();
        MyThread box1 = new MyThread(lottery,"抽奖箱1");
        MyThread box2 = new MyThread(lottery,"抽奖箱2");
        box1.start();
        box2.start();
        box1.join();
        box2.join();
        int max1 = lottery.one.stream().mapToInt(Integer::intValue).max().getAsInt();
        int max2 = lottery.two.stream().mapToInt(Integer::intValue).max().getAsInt();
        if(max1>max2){
            System.out.println("在此次抽奖过程中,抽奖箱1中产生了最大奖项,该奖项金额为"+max1+"元");
        }else{
            System.out.println("在此次抽奖过程中,抽奖箱2中产生了最大奖项,该奖项金额为"+max2+"元");
        }

    }
}