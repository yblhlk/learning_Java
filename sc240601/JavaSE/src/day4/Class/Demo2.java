package day4.Class;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 测试可定长线程池 ：用于创建固定大小线程池的静态方法
public class Demo2 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(3); //设置线程池的长度
        for (int i = 0; i < 10; i++) {
            int j = i;
            es.execute(()->{
                Thread.currentThread().setName("Thread-" + j);
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        es.shutdown(); //关闭线程池，不会影响线程池中正在执行的线程
        while(true) {
            // isshutdown(): 判断线程池有没有关闭
            // isTerminated(): 判断线程池中的所有线程有没有完成
            if(es.isTerminated()) {
                break;
            }
        }

        //ExecutorService es1 = Executors.newFixedThreadPool(3);
        ExecutorService es1 = Executors.newCachedThreadPool();
        int[] Times = {1000, 3000, 4000, 2000, 5000};
        String[] names = {"雷闪","激流","千刃","岩刺","凤羽"};
        // 获取系统当前时间的毫秒数。
        long start = System.currentTimeMillis();
        for(int i = 0; i < 5; i++) {
            int j = i;
            es1.execute(()->{
                System.out.println("开始训练技能" + names[j]);
                try {
                    Thread.sleep(Times[j]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(names[j] + "技能训练完毕");
            });
        }
        es1.shutdown();
        while(true) {
            // isshutdown(): 判断线程池有没有关闭
            // isTerminated(): 判断线程池中的所有线程有没有完成
            if(es1.isTerminated()) {
                long end = System.currentTimeMillis();
                System.out.println("技能已经演练完毕，耗时" + (end - start));
                break;
            }
        }
    }
}
