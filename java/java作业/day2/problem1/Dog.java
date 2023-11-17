package com.chenhuiying.hello;

class Dog extends Animal implements Swim{


    @Override
    public void eat(){
        System.out.println("狗啃骨头");
    }

    @Override
    public void swimming(){
        System.out.println("狗会狗刨");
    }
}