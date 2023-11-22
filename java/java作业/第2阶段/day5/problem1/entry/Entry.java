package com.chenhuiying.hello.entry;


import com.chenhuiying.hello.domain.Student;

import java.util.Arrays;
import java.util.Comparator;

public class Entry {

    public static void main(String[] args) {

        // 创建存储学生对象的数组，并且将学生对象添加到数组中
        Student[] students = new Student[5] ;
        students[0] = new Student("郭靖" , 175) ;
        students[1] = new Student("黄蓉" , 155) ;
        students[2] = new Student("黄药师" , 180) ;
        students[3] = new Student("欧阳锋" , 170) ;
        students[4] = new Student("穆念慈" , 160) ;

        // 调用sort方法排序
        sort(students);

        // 遍历集合
        for(int x = 0 ; x < students.length ; x++) {
            System.out.println(students[x]);
        }

    }

    // 排序方法
    public static void sort(Student[] students) {

        Arrays.sort(students, new Comparator<Student>(){

            @Override
            public int compare(Student o1, Student o2) {
                return o1.getHeight()-o2.getHeight();
            }
        });
        // 补全代码

    }

}
