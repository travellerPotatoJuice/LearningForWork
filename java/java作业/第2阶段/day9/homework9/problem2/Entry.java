package com.chenhuiying.hello;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Entry {

    public static void main(String[] args) throws IOException {
        String src = "C:\\abc";
        String dest = "G:\\";
        copy(src,dest);

    }
    public static void copyFile(String src, String dest){
        try(BufferedReader br = new BufferedReader(new FileReader(src));
            BufferedWriter bw = new BufferedWriter(new FileWriter(dest))) {
            String line;
            while((line = br.readLine())!=null){
                bw.write(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy(String src, String dest){
        File srcFile = new File(src);
        String fileName = srcFile.getName();
        String newPath = dest+"\\"+fileName;
        if (srcFile.isFile()){
            copyFile(src,newPath);
        }
        else if(srcFile.isDirectory()){
            File newFile = new File(newPath);
            newFile.mkdir();
            File[] files = srcFile.listFiles();
            for(File f:files){
                copy(f.getPath(),newPath);
            }
        }
    }

}