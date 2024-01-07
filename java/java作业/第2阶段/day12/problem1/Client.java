package com.chenhuiying.hello;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        //create socket
        int port = 8888;
        Socket socket = new Socket("127.0.0.1", port);
        String path = "C:\\Users\\83538\\Desktop\\aaa\\bbb.txt";
        File file = new File(path);
        try (DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
             BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            System.out.println("客户端启动啦");
            while ((line = bufferedReader.readLine()) != null) {
                dos.writeUTF(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        socket.close();
    }
}
