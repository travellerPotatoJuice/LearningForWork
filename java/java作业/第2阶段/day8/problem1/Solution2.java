package com.chenhuiying.hello;

import java.io.File;
import java.util.Scanner;

public class Solution2 {

    public void solution(){
        Scanner sc = new Scanner(System.in);
        String filePath = sc.next();
        File file = new File(filePath);
        if(!file.exists()){
            System.out.println("文件路径不存在");
        }
        else{
            deleteFiles(file);
        }
    }

    public void deleteFiles(File file){
        File[] files = file.listFiles();
        if (files!=null){
            for(File f: files){
                if(f.isDirectory()){
                    deleteFiles(f);
                }
                else{
                    f.delete();
                }
            }
        }
        file.delete();
    }
}
