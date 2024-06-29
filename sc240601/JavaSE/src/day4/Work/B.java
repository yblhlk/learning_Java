package day4.Work;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class B {
    public static void main(String[] args) throws InterruptedException {
        //开辟一个可缓存的线程池
        ExecutorService ex = Executors.newCachedThreadPool();
        //模拟周一到周期天
        for (int i = 1; i <= 7; i++) {
            Thread.sleep(2000);
            if(i > 5){
                //周末人多
                for (int i1 = 0; i1 < 10000 * i; i1++) {
                    ex.execute(()->{
                        String name = Thread.currentThread().getName();
                        System.out.println(name + "有一个人前来办理任务");
                    });
                }
            } else {
                //工作日人少
                for (int i1 = 0; i1 < 1000 * i; i1++) {
                    ex.execute(()->{
                        String name = Thread.currentThread().getName();
                        System.out.println(name + "有一个人前来办理任务");
                    });
                }
            }
        }
        ex.shutdown();
    }
}
