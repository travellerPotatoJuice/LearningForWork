package com.chenhuiying.hello.domain;

public class Awards {           // 奖项

    private String name ;       // 奖项名称
    private int weight ;        // 权重
    private int low ;           // 权重区间最小值
    private int higt;           // 权重区间最大值

    public Awards(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigt() {
        return higt;
    }

    public void setHigt(int higt) {
        this.higt = higt;
    }
}
