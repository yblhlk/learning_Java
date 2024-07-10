package day7.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// 模拟多人聊天室
public class Demo4 {
}

// 服务端有两个任务：
// 1. 与客户端连接，并将客户端Socket对象保存起来
// 2. 接收每个客户端的消息，并将其广播给其他客户端
// 任务1只需要一个线程，但任务2有几个客户端连接就要开几个线程，来单独接收这些客户端的消息
class Server4{
    public static List<Socket> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        //1.创建服务端对象
        ServerSocket server = new ServerSocket(9999);
        //2.死循环 等待无数个客户端连接 保存进入聊天室的客户端对象
        System.out.println("===================开启多人聊天室=================");
        while (true) {
            Socket client = server.accept();
            list.add(client); //类似于进入聊天室

            System.out.println("聊天室" + list.size() + "人");
            //3.为每一个客户端创建一个服务线程，用来读取从客户端发来的消息，并消息将其转发给其他客户端
            new ServerThread4(client).start();
        }

    }
}

class ServerThread4 extends Thread{
    Socket client;
    public ServerThread4(Socket client){
        this.client = client;
    }
    //3.为每一个客户端创建一个服务线程，用来读取从客户端发来的消息，并消息将其转发给其他客户端
    @Override
    public void run() {
        // a. 读取客户端发来的消息
        //    a1. 创建一个缓冲流从客户端的输入流中读取数据（缓冲流里放字符流，字符流由客户端对象的字节流创建）
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while(true) {
                // 从缓冲流里读出一行数据，以\n结束，不遇到\n不结束
                String msg = br.readLine();
                if(msg != null) {
                    System.out.println(msg);
                    //退出群聊
                    if(msg.contains("esc")) {
                        Server4.list.remove(client);
                        System.out.println("当前聊天室的人数：" + Server4.list.size() + "人。");
                        break;
                    }
                }
        // b. 把读取到的消息转发给其他客户端
                for (Socket s : Server4.list) {
                    if(s != client){ //对象只需要比较地址
                        try {
                            s.getOutputStream().write((msg+"\n").getBytes(StandardCharsets.UTF_8));
                        } catch (IOException e) {
                            //非法退出会一直找不到该线程，不正常退出，没有从集合中删除，所以每次遍历都会报错。
                            //DDSystem.out.println(s + "退出了");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// 客户端任务：1.向服务端写入数据（控制台输入）
//           2.接收服务端返回的数据
// 有两个需要同时处理的任务，所以需要两个线程
class Client4 {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 9999);
        //开启客户端线程 接收服务端发来的消息（单独开一个线程用来接收消息）
        new ClientThread4(client).start();
        //1. 控制台输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //2. 向服务端写入
        OutputStream os = client.getOutputStream();
        System.out.println("请输入你的姓名:");
        String name =br.readLine();
        os.write((name + "进入聊天室\n").getBytes(StandardCharsets.UTF_8));
        while(true) {
            System.out.println("请输入内容，按esc退出聊天室");
            String msg = br.readLine();
            if(msg != null) {
                if("esc".equals(msg)) {
                    os.write((name + "退出聊天室\n").getBytes());
                    break;
                }else {
                    os.write((name + "说：" + msg + "\n").getBytes());
                }
            }
        }
        System.out.println("退出聊天室。");
    }
}

//3. 客户端线程 接收服务端发来的消息（单独开一个线程用来接收消息）
class ClientThread4 extends Thread {
    Socket client;
    public ClientThread4(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            while(true) {
                String msg = br.readLine();
                if(msg != null) System.out.println(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//[32m绿色的狗狗🐕加入群聊[0m
//[31m红色的小丑🤡加入群聊[0m