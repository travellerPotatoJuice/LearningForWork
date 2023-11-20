package com.chenhuiying.hello;

import java.util.Scanner;

public class RemoteControl {
    public void control(TV tv){
        System.out.println("遥控器控制电视");
        Scanner s = new Scanner(System.in);
        String channel = s.next();
        tv.play(channel);
    }
}
