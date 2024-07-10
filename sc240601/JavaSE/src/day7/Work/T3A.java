//a.模拟文件复制功能，手动输入文件地址   和目标地址
//    实现文件复制， 在控制台上显示“XXX文件已复制10%”，
//    “XXX文件已复制20%”……“XXX文件已复制100%”，
//    “XXX复制完成！”

package day7.Work;

import java.io.*;
import java.nio.Buffer;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class T3A {
    public static void main(String[] args) throws IOException {
        //用来统计程序运行的时间
        long start=System.currentTimeMillis();

        //1. 创建两个文件对象A, B，将A的内容拷贝到B中
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入要拷贝的文件的地址:");
        String Path = sc.next();
        System.out.print("请输入目标文件的地址:");
        String goalPath = sc.next();
        File A = new File(Path);
        File B = new File(goalPath);

        //2.获取要拷贝的A文件的大小
        // file.length() : 获取文件大小，单位字节
        double fileSize = A.length()*1.0;
        double currentSize = 0;

        //3.使用字节输入流，从文件A中读取内容
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(A));
        //4.使用字节输出流，向文件B写入内容
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(B));

        //使用字节输入流，从文件A中读取内容 向 文件B中写入
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = in.read(buffer)) != -1) {
            out.write(buffer);
            currentSize += len;
            double bl = currentSize/fileSize *100;
            String sbl = String.format("%.2f", bl);
            System.out.println(A.getName() + "已复制" + sbl  + "%");
            long end = System.currentTimeMillis();
           System.out.println("当前运行时间：" + ((end - start)/1000) + "ms");
        }
        System.out.println(A.getName() + "已复制完成！");

        // 关闭流
        in.close();
        out.close();
    }
}

// java文件操作报错：java.io.FileNotFoundException：D..（拒绝访问）
// 原因：FileOutputStream读取流的时候如果是文件夹，就会出错，无论怎么读，都拒绝访问，应该在读取的目录后面加上文件名！
