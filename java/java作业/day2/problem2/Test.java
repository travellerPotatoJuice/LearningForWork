package com.chenhuiying.hello;

public class Test {
    public static void main(String[] args) {
        TV tv = new TV("东方卫视");
        RemoteControl rc = new RemoteControl();
        rc.control(tv);
    }
}