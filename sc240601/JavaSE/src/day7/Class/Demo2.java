package day7.Class;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Demo2 {
}

//实现：socket接收数据
class Server {
    public static void main(String[] args) throws IOException {
        // 1. 创建服务端对象
        ServerSocket server = new ServerSocket(9999);
        while (true) {
            // 2. 等待客户端连接
            Socket client = server.accept(); //存在阻塞
            System.out.println(client + "连接成功");
            // 3. 获取客户端的输入输出流
            InputStream is = client.getInputStream();
            OutputStream os = client.getOutputStream();

            //4. 通过输出流向客户端写入数据
            String message = "你成功！\n";
            os.write(message.getBytes());

            //5.从客户端读出数据
            //a. 把字节转缓存字符
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String result = br.readLine(); //bug： 需要换行符来结束
            //打印IP + 消息
            System.out.println(client.getInetAddress() + "客户端：" + result);
        }
    }
}

// 客户端读写
class Client {
    public static void main(String[] args) throws IOException {
        // 1.创建客户端对象（ip, 端口）
        Socket client = new Socket("localhost", 9999);
        // 2.创建输入输出流进行网络数据读写

            //b. 创建客户端的输出流
            OutputStream os = client.getOutputStream();
            //String result = br.readLine();
            //DDSystem.out.println("服务端：" + result);
        os.write(("\033[31m红色的小丑🤡加入群聊\033[0m " +
                "\033[32m绿色的狗狗🐕加入群聊\033[0m " +
                "\033[33m黄色的猫🐱加入群聊\033[0m " +
                "\033[34m蓝色的鱼🐟加入群聊\033[0m " +
                "\033[35m紫色的鹰🦅加入群聊\033[0m " +
                "\033[36m青色的大象🐘加入群聊\033[0m " +
                "\033[37m灰色的斑马🦓加入群聊\033[0m " +
                "\033[29m黑色的坤🐓加入群聊\033[0m \n").getBytes(StandardCharsets.UTF_8));

    }
}