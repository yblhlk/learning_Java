package day7.Class;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Demo1 {
    public static void main(String[] args) {
    }
}

// 测试服务端
class TestServer {
    public static void main(String[] args) throws IOException {
        //1. 创建服务端socket
        ServerSocket server = new ServerSocket(9999);
        while (true) {
            Socket client = server.accept(); //阻塞（又一种阻塞情况）
            System.out.println(client);
        }
    }
}

// 测试客户端
class TestClient {
    public static void main(String[] args) throws IOException {
        //1. 创建客户端socket(ip, 端口号）
        Socket client = new Socket("192.168.0.114", 9999);
        // 无法连接报：ConnectException连接异常，一个运行时异常
    }
}