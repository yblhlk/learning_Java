package day7.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Demo3 {
}

//测试socket通过多线程接收数据
class Server2 {
    public static void main(String[] args) throws IOException {
        // 1. 创建客户端socket对象
        ServerSocket server = new ServerSocket(9999);
        // 2. 等待连接
        while(true) {
            Socket client = server.accept();
            System.out.println(client + "连接成功");
            //通过线程来负责每个客户端的读写任务
            new ServerThread(client).start();

        }
    }
}

// 客户端：数据通过Scanner动态写入
class Client2 {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("192.168.0.114", 9999);
        OutputStream os = client.getOutputStream();
        //封装System.in 为缓冲流
        // DDSystem.in 是系统输入流 读取控制台的输入数据
        // Scanner是基于它的封装
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 添加一个客户端线程：负责接收服务端返回的数据。
        while(true) {
            System.out.println("请输入你要向服务端写入的内容：");
            String msg = br.readLine();
            os.write((msg + "\n").getBytes(StandardCharsets.UTF_8));
        }
    }
}

// 服务端线程：目的是监控每一个人的的数据读写
class ServerThread extends Thread {
    Socket client;

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));
            while(true) {
                String clientMsg = br.readLine();
                System.out.println(client.getInetAddress() + ": " + clientMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}