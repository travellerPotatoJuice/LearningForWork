package com.chenhuiying.hello;

public class Test {
    public static void main(String[] args) {
        People p = new People("张三",5.1);
        System.out.println(p.getMoney());
        System.out.println(p.receiveMoney("李四",8.5));
        System.out.println(p.receiveMoney("王五",20.0));
    }
}

class People{
    String name;
    double money;


    public People(String name, double money) {
        this.name = name;
        this.money = money;
    }

    public String getMoney() {
        return this.name+"目前的余额是：\n"+this.money;
    }

    public String receiveMoney(String name, double money){
        this.money+=money;
        return this.name+"接收"+name+"的红包后余额是：\n"+this.money;
    }
}