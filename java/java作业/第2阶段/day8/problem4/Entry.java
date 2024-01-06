package com.chenhuiying.hello;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Entry {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        String path = sc.next();
        File file = new File(path);
        searchFile(file);
    }
    public static void searchFile(File file){
        if(file.isFile()){
            Instant instant = Instant.ofEpochMilli(file.lastModified());
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime time = LocalDateTime.ofInstant(instant, zoneId);
            printResult(file.getName(), file.getPath(), String.valueOf(file.length()), time);
        }
        else{
            File[] files = file.listFiles();
            for(File f:files){
                searchFile(f);
            }
        }
    }

    public static void printResult(String name, String path, String size, LocalDateTime time){
        name = padRight(name,20,' ');
        path = padRight(path,50,' ');
        size = padRight(size,10,' ');
        System.out.println();
        System.out.print(name);
        System.out.print(path);
        System.out.print(size);
        System.out.print(time.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
    }
    public static String padRight(String origin, int length, char ch) {
        while (origin.length() < length) {
            origin = origin + ch;
        }
        return origin;
    }



}
