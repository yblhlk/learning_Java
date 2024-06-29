package day4.Class;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 测试可缓存的线程池
public class Demo3 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            es.execute(()->{
                System.out.println(Thread.currentThread().getName());
            });
        }
        es.shutdown();
    }
}
