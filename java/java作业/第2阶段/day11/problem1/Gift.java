package com.chenhuiying.hello;

public class Gift {

    private int count;
    private int xiaohong=0;
    private int xiaoming=0;

    public Gift(int count) {
        this.count = count;
    }

    public void sendGift(){
        String name = Thread.currentThread().getName();
        synchronized (this) {
            if (this.count >= 10) {
                this.count--;
                if (name.equals("小明")) {
                    xiaoming++;
                } else if (name.equals("小红")) {
                    xiaohong++;
                }
            }
        }


    }

    public int getCount(){
        return this.count;
    }

    public int getXiaoming(){
        return xiaoming;
    }

    public int getXiaohong(){
        return xiaohong;
    }

}
