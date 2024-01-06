package com.chenhuiying.hello;

import java.io.File;
import java.util.Scanner;

public class Solution4 {
    public void solution(){
        Scanner sc = new Scanner(System.in);
        String filePath = sc.next();
        File file = new File(filePath);
        long count = 0 ;
        if(file.isDirectory()) {
            count += countLength(file, count);
        }
        else count = file.length();
        System.out.println("The size of dir is: " + count+ " Byte");
    }

    public long countLength(File file, long count){
        File[] files = file.listFiles();
        for(File f: files){
            if(f.isDirectory()){
                count += countLength(f,count);
            }
            count+=f.length();
        }
        return count;
    }
}
