package com.chenhuiying.hello;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入对应的付款方式标号");
        int code = sc.nextInt();
        System.out.println(Type.getNameByValue(code));
    }
}

