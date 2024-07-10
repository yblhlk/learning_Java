package day7.Work;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

//b.参考课堂第3 或 4个案例(多人聊天室 或者 多线程实现Socket 选择一个重新完成一遍)
public class T3B {
}

class Server {
    // 保存连接上服务器（加入群聊）的客户端socket
    static List<Socket> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 1. 创建ServerSocket对象作为服务器
        ServerSocket server = new ServerSocket(9999);
        // 一个端口不能同时开两个服务端不然会报 BindException, 表示此端口已经被占用
        // 套接字异常：SocketException，表示连接出现问题，通常是连接错误：服务器IP错误，服务器关闭，服务器防火墙没有关闭

        // 2. 等待Socket对象连接服务器对象
        while (true) {
            Socket client = server.accept(); //阻塞
            clients.add(client); // 将连接上服务器的客户端socket保存起来。
            System.out.println("机器：" + clients + "已经连接上服务器，当前用户数量：" + clients.size());

            // 为接收每个客户端Socket的信息而开辟对应线程处理其各自的任务。
            // 其任务有两点：1.接收从客户端发送来的信息 2.将信息广播给其他客户端
            new Thread(new ServerThread(client), clients.toString()).start();
        }
    }
}

// 为接收每个客户端Socket的信息而开辟对应线程处理其各自的任务。
// 其任务有两点：1.接收从客户端发送来的信息 2.将信息广播给其他客户端
class ServerThread extends Thread {
    Socket client;

    public ServerThread(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        // 1.接收从客户端发送来的信息
        try {
            //创建字符输入流，从客户端读入信息
            BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String name = is.readLine();//按行读取，先读取名字
            System.out.println("用户\033[32m【" + name + "】\033[0m，进入聊天室");
            String msg = "";
            //循环读取用户信息
            while (true) {
                msg = is.readLine(); //按行读取，以\n终止，记住一定要加\n
                msg = name + "说：" + msg;
                System.out.println(msg);

                // 接收到quit信号就退出
                if (msg.equalsIgnoreCase("quit") || msg.equalsIgnoreCase("q")) {
                    Server.clients.remove(client);
                    System.out.println("用户：" + name + "退出聊天室，当前聊天室剩余" + Server.clients.size() + "人。");
                    return;
                } else {
                    // 将接收到的信息发送给其他用户
                    for (Socket c : Server.clients) {
                        if (c != client) {
                            try {
                                c.getOutputStream().write((msg + "\n").getBytes(StandardCharsets.UTF_8));
                            } catch (IOException e) {
                                // 向客户端写入信息异常，可能是客户端异常退出，在这里删除客户端的信息
                                Server.clients.remove(client);
                                System.out.println("用户：" + name + "异常退出聊天室。");
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("服务器读取消息失败！机器：" + client + "异常退出！");
        }
    }
}

// 客户端任务：1.向服务端写入数据（控制台输入）
//           2.接收服务端返回的数据
// 因为这两个步骤需要同时进行，所以要有两个线程。
class Client {
    public static void main(String[] args) throws IOException {
        // 0. 客户端连接服务端（这一步是socket编程的基础）
        Socket client = new Socket("localhost", 9999);
        //2. 接收服务端返回的数据（通过客户端的输入流：client.getInputStream，来获取数据）
        //因为1，2两个任务需要同时进行，所以要有两个线程。
        new ClientThread(client).start();
        //1. 向服务端写入数据(通过客户端的输出流：client.getOutputStream）
        // 从控制台获取用户输入的数据，将其转换为缓冲流
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        //a. 输入姓名 (输入的第一行就是姓名）
        System.out.println("请输入你的姓名：");
        String name = in.readLine();
        OutputStream os = client.getOutputStream();
        os.write((name + "\n").getBytes(StandardCharsets.UTF_8)); // 因为是readLine()来读取所以一定要加\n
        //b. 向服务端写入信息(把控制的的信息发送到服务端使用client.getOutputStream)
        while (true) {
            //因为是死循环，所以接收消息的线程不能写在它的下面
            System.out.println("发送消息：");
            String msg = in.readLine(); //从控制台获取消息
            if (msg != null) {
                os.write((msg + "\n").getBytes(StandardCharsets.UTF_8));//向服务端写入消息，因为是readLine()来读取所以一定要加\n
                if (msg.equalsIgnoreCase("quit") || msg.equalsIgnoreCase("q")) {
                    return; //退出聊天室，不能看到别人发的消息了。
                }
            }
        }
    }
}

//2. 接收服务端发来的数据（通过客户端的输入流：client.getInputStream，来获取数据）
class ClientThread extends Thread {
    private Socket client;

    public ClientThread(Socket c) {
        client = c;
    }

    @Override
    public void run() {
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String msg = null;
            while (true) {
                msg = is.readLine();
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}