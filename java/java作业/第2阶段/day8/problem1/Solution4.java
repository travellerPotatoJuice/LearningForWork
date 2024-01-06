package com.chenhuiying.hello;

import java.io.File;
import java.util.Scanner;

public class Solution4 {
    public void solution(){
        Scanner sc = new Scanner(System.in);
        String filePath = sc.next();
        File file = new File(filePath);
        System.out.println("The size of dir is: " + file.length()+ " Byte");
    }


}
