package com.chenhuiying.hello;

import java.io.*;
import java.util.Scanner;

public class Solution5 {
    public void solution() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String filePath = sc.next();
        File file = new File(filePath);
        if(file.isDirectory()){
            findFile(file);
        }
        else{
            readContent(file);
        }
    }
    public void findFile(File file){
        File[] files = file.listFiles();
        for(File f:files){
            if(f.isDirectory()){
                findFile(f);
            }
            else{
                if(f.length()<200){
                    readContent(f);
                }
            }

        }
    }

    public void readContent(File file){

            try(BufferedReader br = new BufferedReader(new FileReader(file));
            ){
                String line;
                System.out.println(file.getName());
                while((line = br.readLine()) != null){
                    System.out.println(line);
                }
                System.out.println("------------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
