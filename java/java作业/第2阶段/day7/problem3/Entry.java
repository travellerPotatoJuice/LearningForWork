package com.chenhuiying.hello;

import java.util.*;

public class Entry {
    public static void main(String[] args) {
        String input = "1.2, 3.4, 5.6, 7.8, 5.56, 44.55";
        String[] nums = input.split(", ");
        Map<Object,Object> maps = new HashMap<>();
        Collection collection = new ArrayList();
        for(String s: nums){
            String[] SplitString = s.split("\\.");
            maps.put(SplitString[0],SplitString[1]);
        }
        Set<Object> set = maps.keySet();
        System.out.println(set.toString());
        for(Object o: set){
            collection.add(maps.get(o));
        }
        System.out.println(collection.toString());
    }
}