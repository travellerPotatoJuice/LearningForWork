package com.chenhuiying.hello.domain;

public class Student {          // 学生类

    private String name ;       // 学生姓名
    private int height ;        // 学生身高

    public Student(String name, int height) {      // 有参构造方法
        this.name = name;
        this.height = height;
    }

    // get和set方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}
