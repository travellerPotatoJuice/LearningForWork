package com.chenhuiying.hello;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Entry {

    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\83538\\Desktop\\abc\\check.txt";
        File file = new File(filePath);
        if(!file.exists()){
            boolean created = file.createNewFile();
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath))){
                bufferedWriter.write("1");
                System.out.println("欢迎使用本软件,第1次使用免费~");
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            int count;
            try(BufferedReader bufferedReader  = new BufferedReader(new FileReader(filePath)))
                {
                    count = Integer.parseInt(bufferedReader.readLine());

                }
            try(BufferedWriter bufferedWriter = new BufferedWriter((new FileWriter(filePath)))){
                if(count<3) {
                    count++;
                    System.out.println("欢迎使用本软件,第"+count+"次使用免费~");
                    String lin = String.valueOf(count);
                    bufferedWriter.write(String.valueOf(count));
                }
                else{
                    System.out.println("本软件只能免费使用3次,欢迎您注册会员后继续使用~");
                }
            }
        }

    }
}