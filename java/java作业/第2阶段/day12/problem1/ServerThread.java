package com.chenhuiying.hello;

import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerThread extends Thread {

    private Socket socket;
    private final static String WORD = "操";

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("客户端启动成功");
        File file = new File("C:\\Users\\83538\\Desktop\\ccc.txt");

        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            while (true) {
                try {
                    String line = dis.readUTF();
                    Pattern pattern = Pattern.compile(WORD);
                    Matcher matcher = pattern.matcher(line);
                    String result = matcher.replaceAll("*");
                    bufferedWriter.write(result);
                }catch (Exception e){
                    dis.close();
                    bufferedWriter.close();
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
