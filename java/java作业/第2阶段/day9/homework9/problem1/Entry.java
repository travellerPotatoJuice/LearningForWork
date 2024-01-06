package com.chenhuiying.hello;

import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Entry {
    private static final byte XOR_KEY = 0x07;

    public static void main(String[] args) throws IOException {
        String srcFile = "C:\\Users\\83538\\Desktop\\abc.txt";
        String destFile = "C:\\Users\\83538\\Desktop\\def.txt";
        String finalFile = "C:\\Users\\83538\\Desktop\\ghi.txt";

        encrypt(srcFile, destFile);
        System.out.println("加密完成！");

        encrypt(destFile, finalFile);
        System.out.println("解密完成！");
    }

    public static void encrypt(String srcFile, String destFile) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(srcFile));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile))) {
            int b;
            while ((b = in.read()) != -1) {
                b ^= XOR_KEY;
                out.write(b);
            }
        }
    }

}