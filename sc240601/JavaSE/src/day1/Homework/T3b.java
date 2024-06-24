package day1.Homework;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class T3b implements Runnable{
    static boolean flag = true;
    @Override
    public void run() {
        int T = 1000;
        int S = 0;
        int F = 100;
        String name = Thread.currentThread().getName();
        System.out.println("请输入" + name + "的爬山速度：");
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();

        while(flag){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            S += v;
            if(S >= F){
                System.out.println(name + "已经爬了" + S + "米");
                F += 100;
            }
            if(S >= T){
                System.out.println(name + "已经爬到了山顶。");
                flag = false;
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new T3b(), "高家明");
        Thread t2 = new Thread(new T3b(), "赵佳佳");

        t1.start();
        t2.start();
    }
}
