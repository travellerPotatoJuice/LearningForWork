package com.chenhuiying.hello;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Entry {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String origin = "C:\\Users\\83538\\Desktop\\abc.txt";
        String target = "C:\\Users\\83538\\Desktop\\def.txt";
        File file1 = new File(origin);
        double file1Length = (double)file1.length();
        String fileName = file1.getName();
        File file2 = new File(target);
        DecimalFormat df = new DecimalFormat("##%");
        try(BufferedReader br = new BufferedReader(new FileReader(origin));
            BufferedWriter bw = new BufferedWriter(new FileWriter(target));){
            String line;
            int length = 0;
            while((line=br.readLine())!=null){
                bw.write(line);
                length += line.length();
                double sd = length/file1Length;
                String format = df.format(length/file1Length);
                System.out.println(fileName+"文件复制了："+format);
            }
            System.out.println(fileName+"文件复制完毕！");
        }









    }

}