package com.chenhuiying.hello;

public class TV {
    String name;

    public TV(String name) {
        this.name = name;
    }

    public void play(String channel){
        System.out.println(this.name+"电视正在播放"+channel+"节目");
    }
}
