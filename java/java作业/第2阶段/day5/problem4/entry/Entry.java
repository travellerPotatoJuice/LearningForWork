package com.chenhuiying.hello.entry;


import com.chenhuiying.hello.domain.Awards;

import java.util.Arrays;
import java.util.Random;

public class Entry {

    public static void main(String[] args) {

        // 定义奖项数组,并为其设置值
        Awards[] awards = new Awards[4] ;
        awards[0] = new Awards("一等奖" , 1) ;
        awards[1] = new Awards("二等奖" , 2) ;
        awards[2] = new Awards("三等奖" , 3) ;
        awards[3] = new Awards("谢谢惠顾" , 4) ;

        // 补全代码
        awards[0].setLow(0);
        awards[0].setHigt(1);
        awards[1].setLow(1);
        awards[1].setHigt(3);
        awards[2].setLow(3);
        awards[2].setHigt(6);
        awards[3].setLow(6);
        awards[3].setHigt(10);

//        System.out.println(binarySearch(awards, 6).getName());
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            System.out.println("恭喜xxx获得：" + binarySearch(awards, random.nextInt(10)).getName());
        }
    }

    // 二分查找实现
    public static Awards binarySearch(Awards[] awards , int num) {
        int loc = awards.length / 2;
        Awards middle = awards[loc];
        if (middle == null || num < middle.getLow()){
            return binarySearch(Arrays.copyOfRange(awards,0,loc),num);
        }else if (num >= middle.getHigt()){
            return binarySearch(Arrays.copyOfRange(awards,loc + 1,awards.length + 1),num);
        }else {
            return middle;
        }
    }

}
