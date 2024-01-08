package com.chenhuiying.hello;

public class MyThread extends Thread {
    private Gift gift;


    public MyThread(Gift gift, String threadName) {
        super(threadName);
        this.gift = gift;
    }

    @Override
    public void run(){
        while(gift.getCount()>=10) {
            gift.sendGift();
        }
        String name = getName();
        if(name.equals("小明")){
            System.out.println(name+"发了"+gift.getXiaoming()+"份礼物");
        }else if(name.equals("小红")){
            System.out.println(name+"发了"+gift.getXiaohong()+"份礼物");
        }
    }

}
