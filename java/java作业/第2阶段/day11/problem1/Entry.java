package com.chenhuiying.hello;


public class Entry {

    public static void main(String[] args) {
        Gift gift = new Gift(100);
        MyThread xiaohong = new MyThread(gift,"小红");
        MyThread xiaoming = new MyThread(gift,"小明");
        xiaoming.start();
        xiaohong.start();
    }
}