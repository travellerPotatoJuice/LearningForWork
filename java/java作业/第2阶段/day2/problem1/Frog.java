package com.chenhuiying.hello;

public class Frog extends Animal implements Swim{

    @Override
    public void eat(){
        System.out.println("青蛙吃虫");
    }

    @Override
    public void swimming(){
        System.out.println("青蛙会蛙泳");
    }
}
