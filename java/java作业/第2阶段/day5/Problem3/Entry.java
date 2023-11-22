package com.chenhuiying.hello.entry.function.entry;

import java.util.Random;
import java.util.function.Function;

public class Entry {

    public static void main(String[] args) {

        // 定义一个存储学生姓名的数组
        String[] userNames = {"郭靖", "黄药师", "黄蓉", "欧阳锋", "老顽童", "瑛姑"};

        // 在等号右边补全代码实现getUserName方法调用


        Function<String[], String> function = strings -> userNames[new Random().nextInt(userNames.length)];
        String userName = getUserName(function, userNames);
        System.out.println("请" + userName + "同学回答问题！");

    }

    // 从数组中随机获取一个元素
    public static String getUserName(Function<String[], String> function, String[] userNames) {
        return function.apply(userNames);
    }

}
