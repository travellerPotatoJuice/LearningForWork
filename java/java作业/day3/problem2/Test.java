package com.chenhuiying.hello;

public class Test {
    public static void main(String[] args) {
        A aobject = new A();
        aobject.methodA(new InterA(){
            @Override
            public void show() {
                System.out.println("我是没有名字的InterA的实现类");
            }
        });
        InterA another =  new InterA() {
            @Override
            public void show() {
                System.out.println("我是有名字的InterA的实现类");
            }
        };
        aobject.methodA(another);
    }
}

class A {
    public void methodA(InterA a) {
        a.show();
    }
}