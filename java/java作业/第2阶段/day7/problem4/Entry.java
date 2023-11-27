package com.chenhuiying.hello;

import java.util.*;

public class Entry {
    public static void main(String[] args) {
        HashMap<Integer,String> stations = new HashMap<>();
        init(stations);
        print(stations);
        String up = "";
        String down = "";
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("请输入上车站:");
            up = sc.next();
            if(validation(up,stations)){
                break;
            }
            System.out.println("站点不存在，请重新输入");
        }
        while(true){
            System.out.println("请输入到达站:");
            down = sc.next();
            if(validation(down,stations)){
                break;
            }
            System.out.println("站点不存在，请重新输入");
        }
        int stationNum = getStationNum(up,down,stations);
        int price = getPrice(stationNum);
        System.out.println("从[" + up + "]到[" + down + "]共经过" + stationNum + "站,收费"+
                price+"元，大约需要"+stationNum*2+"分钟");


    }
    public static void init(HashMap<Integer, String> stations){
        stations.put(1,"黄花机场T1T2");
        stations.put(2,"木马塅");
        stations.put(3,"大路村");
        stations.put(4,"龙峰");
        stations.put(5,"曹家坪");
        stations.put(6,"檀木桥");
        stations.put(7,"龙华");
        stations.put(8,"韶光");
        stations.put(9,"东湖");
        stations.put(10,"农科院农大");
        stations.put(11,"隆平水稻博物馆");
        stations.put(12,"花桥");
        stations.put(13,"人民东路");
        stations.put(14,"芙蓉区政府");
        stations.put(15,"朝阳村");
        stations.put(16,"窑岭湘雅二医院");
        stations.put(17,"迎宾路");
        stations.put(18,"烈士公园南");
        stations.put(19,"湘雅医院");
        stations.put(20,"文昌阁");
        stations.put(21,"六沟陇");
        stations.put(22,"湘雅三医院");
        stations.put(23,"白鸽咀");
        stations.put(24,"湖南工商大学");
        stations.put(25,"涧塘");
        stations.put(26,"麓谷公园");
        stations.put(27,"麓谷体育公园");
        stations.put(28,"长丰");
        stations.put(29,"和馨园");
        stations.put(30,"长庆");
        stations.put(31,"一师范西校区");
        stations.put(32,"中塘");
        stations.put(33,"象鼻窝");
        stations.put(34,"谢家桥");

    }


    public static void print(HashMap<Integer, String> stations){
        Set<Integer> stationNum = stations.keySet();
        Iterator<Integer> it = stationNum.iterator();
        while(it.hasNext()){
            Integer num = it.next();
            System.out.println(num+" - "+stations.get(num)+"   ");

        }
    }

    public static boolean validation(String up, Map<Integer,String> stations){
        return stations.containsValue(up);
    }

    public static int getStationNum(String up, String down,HashMap<Integer, String> stations){
        Set<Integer> stationNumber = stations.keySet();
        int first=0;
        int second=0;
        Iterator<Integer> it = stationNumber.iterator();
        while(it.hasNext()){
            int num = it.next();
            if(stations.get(num).equals(up)){
                first = num;
            }
            if(stations.get(num).equals(down)){
                second = num;
            }
        }
        return second-first;
    }

    public static int getPrice(int stationNum){
        if(stationNum<=3)return 3;
        else if(stationNum<=5)return 4;
        else{
            return Math.min(10,4+(stationNum-5)*2);
        }
    }
}