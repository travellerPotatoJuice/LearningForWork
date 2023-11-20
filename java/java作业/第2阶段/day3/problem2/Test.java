package com.chenhuiying.hello;

public class Test {
    public static void main(String[] args) {
        A a = new A();
        a.methodA(new InterA() {
            @Override
            public void show() {
                System.out.println("我是没有名字的InterA的实现类");
            }
        });
        B b = new B();
        b.show();
    }
}

class A {
    public void methodA(InterA a) {
        a.show();
    }
}

class B implements InterA {
    @Override
    public void show() {
        System.out.println("我是有名字的InterA的实现类");
    }
}

