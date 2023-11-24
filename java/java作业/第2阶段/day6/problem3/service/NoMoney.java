package com.chenhuiying.hello.entry.function.entry.entry.service;

public class NoMoney extends RuntimeException{
    public NoMoney(){
        System.out.println("账户余额不足");
    }
}
