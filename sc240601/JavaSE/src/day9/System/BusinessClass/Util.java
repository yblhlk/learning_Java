package day9.System.BusinessClass;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Util {
    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    public static void costDescription() {
        // 读取"资费说明.txt"并打印出来
        File file = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\SaveLocal\\资费说明.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String l = "";
            while ((l =br.readLine()) != null) {
                System.out.println(l);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteLoginCache() {
        //每次退出系统前都要清空本次登录的缓存文件。
        File LoginCaches = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\LoginCache");
        File[] lc = LoginCaches.listFiles();
        for (File file : lc) {
                file.delete();
        }
    }
}
