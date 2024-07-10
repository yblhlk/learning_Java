//b.从键盘输入接收一个文件夹路径,打印出该文件夹下所有的.java后缀的文件名
//    拓展: 如果同时还包括所有子文件该如何实现

package day7.Work;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入文件夹的路径:");
        String path = sc.next();
        //1. 创建文件对象
        File file = new File(path);
        //2. 递归扫描整个文件夹
        recursion(file);
    }

    public static void recursion(File file) {
        // 递归终止条件
        if(file == null)
            return;
        File[] files = file.listFiles();
        for (File f : files) {
            // 如果是文件
            if(f.isFile()){
                // 判断是否以java为后缀
                if(f.getName().endsWith(".java")) {
                    System.out.println(f.getAbsoluteFile());
                }
            }

            // 如果是文件夹
            if(f.isDirectory()) {
                recursion(f);
            }
        }
    }
}
