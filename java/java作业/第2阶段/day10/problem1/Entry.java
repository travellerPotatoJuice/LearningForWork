package com.chenhuiying.hello;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import sun.reflect.generics.tree.Tree;


public class Entry {

    public static void main(String[] args) throws DocumentException {
        String filePath = "C:\\Users\\83538\\Desktop\\abc\\hello.xml";
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(filePath);
        Map<Integer,User> map = new TreeMap<>();

        //get root
        Element root = document.getRootElement();
        for(Element user: root.elements("user")){
            int id = Integer.parseInt(user.attributeValue("id"));
            String name = user.elementText("name");
            String sex = user.elementText("sex");
            String address = user.elementText("地址");
            String password = user.elementText("password");
            User u = new User(name,sex,address,password);
            map.put(id,u);
        }

        Set<Integer> key = map.keySet();
        for(int k:key){
            System.out.println();
            System.out.print("id=" + k +"  ");
            System.out.print(map.get(k).toString());
        }

    }

}