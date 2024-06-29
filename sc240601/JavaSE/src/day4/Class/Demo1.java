package day4.Class;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 测试线程池的创建和使用
public class Demo1 {
    public static void main(String[] args) {
        ExecutorService es = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            es.execute(()->{
                Thread.currentThread().setName("王"+j);
                String name = Thread.currentThread().getName();
                System.out.println(name + "开始进洞");
                for (int i1 = 3; i1 > 0; i1--) {
                    System.out.println("还需要" + i1 + "秒出洞");
                }
                System.out.println(name + "开始出洞");
            });
        }
        es.shutdown();
    }
}
