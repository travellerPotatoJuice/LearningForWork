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
        String src = "C:\\Users\\83538\\Desktop\\abc";
        String target = "C:\\Users\\83538\\Desktop\\def";
        File srcFile = new File(src);
        File tarFile = new File(target);
        File[] files = srcFile.listFiles();
        List<File> list = Arrays.stream(files).filter(file -> file.getName().endsWith(".java")).collect(Collectors.toList());

        for(File javaFile:list){
            String filename = javaFile.getName();
            String destFileName = filename.replaceAll("\\.java",".txt");
            try(BufferedReader br = new BufferedReader(new FileReader(javaFile));
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File(tarFile,destFileName)))
            ){
                String line;
                while((line=br.readLine())!=null){
                    bw.write(line);
                }

            }
        }



    }
}