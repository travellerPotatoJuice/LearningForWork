package com.chenhuiying.hello;

import java.io.File;
import java.nio.file.Files;
import java.util.Scanner;

public class Solution3 {
    public void solution(){
        Scanner sc = new Scanner(System.in);
        String filePath = sc.next();
        File file = new File(filePath);
        File[] files = file.listFiles();
        for(File f: files){
            int lastIndex = f.getName().lastIndexOf(".");
            String extension = f.getName().substring(lastIndex+1);
            if("java".equals(extension)){
                System.out.println(f.getName());
            }
        }
    }
}
