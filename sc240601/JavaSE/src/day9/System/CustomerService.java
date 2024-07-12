package day9.System;

import day9.System.BusinessClass.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomerService {
    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(9999);
            System.out.println("摸鱼，等待用户连接中……");
            Socket client = s.accept();
            //1. 创建线程接收用户发来的消息
            new Thread(()->{
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    while(true) {
                        String msg = br.readLine();
                        if(msg != null) {
                            System.out.println(msg);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
            //2. 向客户发送消息
            while (true) {
                String msg = "";
                msg = Util.sc.next();
                if (msg != null) {
                    client.getOutputStream().write((msg + "\n").getBytes(StandardCharsets.UTF_8));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
