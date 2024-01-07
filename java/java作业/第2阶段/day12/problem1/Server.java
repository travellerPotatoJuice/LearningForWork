package com.chenhuiying.hello;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("-------------服务器启动成功---------------");
        while(true){
            Socket socket = serverSocket.accept();
            new ServerThread(socket).start();
        }
    }
}
