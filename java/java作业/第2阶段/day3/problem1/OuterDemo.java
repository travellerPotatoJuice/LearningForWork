package com.chenhuiying.hello;

class Outer {
    static Inter method(){
        return new A();
    }

}

class A implements Inter{
    public void show(){
        System.out.println("HelloWorld");
    }
}
public class OuterDemo {
    public static void main(String[] args) {
        Outer.method().show();
    }
}