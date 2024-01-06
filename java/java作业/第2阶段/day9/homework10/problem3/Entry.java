package com.chenhuiying.hello;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.util.*;

public class Entry {

    public static void main(String[] args) throws IOException {
        String src = "C:\\Users\\83538\\Desktop\\abc.txt";
        String target = "C:\\Users\\83538\\Desktop\\def.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(src));
            BufferedWriter bw = new BufferedWriter(new FileWriter(target))
        ){
            String line;
            while((line=br.readLine())!=null){
                line = line.replace("\\s", "");
                String[] parts = line.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
                int num1 = Integer.parseInt(parts[0]);
                int num2 = Integer.parseInt(parts[2]);
                long result=0;
                String operator = parts[1];
                switch (operator) {
                    case "+":
                        result = num1+num2;
                        break;
                    case "-":
                        result = num1-num2;
                        break;
                    case "*":
                        result = num1*num2;
                        break;
                    case "/":
                        result = num1/num2;
                        break;
                }
                line+="="+String.valueOf(result)+"\n";
                bw.write(line);
            }
        }
    }
}