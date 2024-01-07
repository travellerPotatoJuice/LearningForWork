package com.chenhuiying.hello;


import java.io.*;
import java.util.Scanner;

public class Entry {
    static String songPath = "C:\\Users\\83538\\Desktop\\abc";

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println("请选择您要进行的操作：1：查询所有歌曲  2：根据歌曲名称复制  3：根据歌曲名称删除 4：退出");
            int line = Integer.parseInt(sc.nextLine());
            switch(line) {
                case 1:
                    printList();
                    break;
                case 2:
                    duplicateSong(sc);
                    break;
                case 3:
                    deleteSong(sc);
                    break;
                case 4:
                    System.out.println("退出系统");
                    flag=false;
            }
        }
    }

    public static void printList(){
        System.out.println("存在以下歌曲：");
        File file = new File(songPath);
        File[] files = file.listFiles();
        for(File f:files){
            System.out.println(editName(f.getName()));
        }
    }
    public static void duplicateSong(Scanner sc){
        String originPath = null;
        System.out.println("请输入要复制的歌曲名称:");
        String songName = sc.nextLine();
        File[] files = new File(songPath).listFiles();
        File originFile = null;
        for(File f:files){
            String filename = editName(f.getName());
            if(filename.equals(songName)){
                originPath = f.getPath();
                originFile = f;
                break;
            }
        }
        if(originPath==null){
            System.out.println("该歌曲不存在");
            return ;
        }
        System.out.println("请输入存储路径:");
        String path = sc.nextLine();
        File duplicate = new File(path);

        if(!duplicate.exists()){
            System.out.println("路径不存在，复制失败");
        }
        else{
            path += "\\"+originFile.getName();
            duplicate = new File(path);
            try(BufferedReader reader = new BufferedReader(new FileReader(originPath));
                BufferedWriter writer = new BufferedWriter((new FileWriter(duplicate)))){

                }catch (IOException e){
                    e.printStackTrace();
                }
            System.out.println("复制成功");

        }
    }
    public static void deleteSong(Scanner sc){
        System.out.println("请输入要删除的歌曲名称:");
        String songName = sc.nextLine();
        File songDir = new File(songPath);
        File[] files = songDir.listFiles();
        String filename = null;
        for(File f:files){
            filename = editName(f.getName());
            if(filename.equals(songName)){
                System.out.println("歌曲" + f.getName() + "已经成功删除");
                f.delete();
                break;
            }
        }
        if(filename==null) {
            System.out.println("歌曲不存在");
        }
    }

    public static String editName(String filename){
        int index = filename.lastIndexOf(".");
        return (index==-1)?filename:filename.substring(0,index);
    }

}