package day4.Class;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// 可周期定长线程池
public class Demo4 {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newScheduledThreadPool(3);
        // 1. 基础操作 execute()
        // 和普通的定长线程池一样，也是限定了线程池的长度
//        for (int i = 0; i < 100; i++) {
//            ex.execute(()-> {
//                DDSystem.out.println(Thread.currentThread().getName());
//            });
//        }
        ex.shutdown();

        // 2. 支持延迟操作 （是子类自己的方法，所以不能用父类对象来引用）
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
        //ses.schedule(Runnable, 首次延迟时间， 时间单位)
        for (int i = 0; i < 10; i++) {
            ses.schedule(() -> {
                System.out.println(Thread.currentThread().getName());
            }, 3, TimeUnit.SECONDS); //3秒后启动线程池
        }

        // 3. 支持周期操作 是定时开启线程池？还是定时开启线程池中的线程？
        //ses.schedule(Runnable, 首次延迟时间，周期时间，时间单位)
        for (int i = 0; i < 10; i++) {
            ses.scheduleAtFixedRate(() -> {
                System.out.println(Thread.currentThread().getName());
            }, 3, 2,TimeUnit.SECONDS); //3秒后启动线程池，2秒为一个周期
        }
        ses.shutdown();
    }
}
