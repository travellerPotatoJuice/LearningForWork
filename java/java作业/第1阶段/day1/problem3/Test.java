package com.chenhuiying.hello;

public class Test {
    public static void main(String[] args) {
        Student s = new Student("小明",'男',18,1.78,true);
        System.out.println(s.toString());
    }
}

class Student{
    String name;
    char gender;
    int age;
    double height;
    boolean marriage;

    public Student(String name, char gender, int age, double height, boolean marriage) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.marriage = marriage;
    }

    @Override
    public String toString() {
        return "name='" + name + '\n' +
                "gender=" + gender + '\n' +
                "age=" + age + '\n' +
                "height=" + height + '\n' +
                "marriage=" + marriage;
    }
}