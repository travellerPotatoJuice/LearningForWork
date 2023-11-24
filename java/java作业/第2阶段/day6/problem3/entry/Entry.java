package com.chenhuiying.hello.entry.function.entry.entry.entry;



import com.chenhuiying.hello.entry.function.entry.entry.service.AtmService;
import com.chenhuiying.hello.entry.function.entry.entry.service.IllegalInput;
import com.chenhuiying.hello.entry.function.entry.entry.service.NoMoney;
import com.chenhuiying.hello.entry.function.entry.entry.service.impl.AtmServiceImpl;

import java.time.Period;
import java.util.Scanner;

public class Entry {

    public static void main(String[] args) {

        // 键盘录入取钱金额
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入取钱金额: ");
        double drawMoney = sc.nextDouble();

        // 通过多态的方式创建AtmService对象
        AtmService atmService = new AtmServiceImpl();

        // 调用drawMoney方法进行取钱
        double money = atmService.drawMoney(drawMoney);


        // 输出剩余金额
        try {
            System.out.println("恭喜你，取钱成功! 本次取钱金额为：" + drawMoney + ", 剩余金额：" + money);
        } catch (IllegalInput e) {
            e.printStackTrace();
        } catch (NoMoney e) {
            e.printStackTrace();
        }

    }
}