package com.chenhuiying.hello;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.util.*;

public class Entry {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filePathGlobal = "D:\\javaProject\\javaLearning\\helloworld.app\\src\\com\\chenhuiying\\hello\\global";
        String filePathChinese = "D:\\javaProject\\javaLearning\\helloworld.app\\src\\com\\chenhuiying\\hello\\chinese";
        File file1 = new File(filePathGlobal);
        File file2 = new File(filePathChinese);
        TreeMap<Integer,String> global = new TreeMap<>();
        TreeMap<Integer,String> chinese = new TreeMap<>();
        ArrayList<Film> arrayList = new ArrayList<>();
        try (BufferedReader br1 = new BufferedReader(new FileReader(file1));
             BufferedReader br2 = new BufferedReader(new FileReader(file2));) {
            setMap(br1,global);
            setMap(br2,chinese);
            System.out.println("------------全球排名top3-------------");
            printRank(global,0,2);
            System.out.println("------------中国排名top6~10-------------");
            printRank(chinese,5,9);
            putIntoCollection(arrayList,global);
            putIntoCollection(arrayList,chinese);
        }
    }

    public static void setMap(BufferedReader br, TreeMap<Integer,String> map) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            int rankStart = line.indexOf("第");
            if(rankStart!=-1) {
                //get rank
                int rankEnd = line.indexOf("名");
                int rank = Integer.parseInt(line.substring(rankStart+1, rankEnd));
                //get name
                int nameStart = line.indexOf("《");
                int nameEnd = line.indexOf("》");
                String name = line.substring(nameStart + 1, nameEnd);
                //get score
                int scoreIndex = line.lastIndexOf("：");
                String score = line.substring(scoreIndex + 1);
                map.put(rank,name+"|"+score);
            }
        }
    }

    public static void printRank(TreeMap<Integer,String> map, int start, int end){
        Set<Integer> nums = map.keySet();
        for(int num:nums){
            if(start>end)break;
            if(start<=end){
                start++;
                String info = map.get(num);
                int index = info.indexOf("|");
                info = info.substring(0,index);
                System.out.println("第"+start+"名： "+info);
            }
        }
    }

    public static void putIntoCollection(ArrayList<Film> arrayList, TreeMap<Integer,String> map){
        Set<Integer> nums = map.keySet();
        int count=5;
        for(int num:nums){
            if(count==0)break;
            String info = map.get(num);
            int index = info.indexOf("|");
            String name = info.substring(0,index);
            double score = Double.parseDouble(info.substring(index+1));
            arrayList.add(new Film(name,score));
            count--;
            }
        }

}
