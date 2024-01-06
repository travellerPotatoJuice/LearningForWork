package com.chenhuiying.hello;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

public class Solution1 {
    public void solution(){
        Scanner sc = new Scanner(System.in);
        if(sc.hasNext()){
            File file = new File(sc.next());
            if(!file.exists()){
                file.mkdir();
            }
        }
    }
}
