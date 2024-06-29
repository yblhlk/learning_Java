package day4.Class;

// 通过可周期定长线程池：模拟燃放烟花的过程
// 首先点燃引线需要5秒 之后每2秒 发射一个烟花
// 一个有12个烟花 烟花发射完毕 打印新年快乐

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Demo5 {
    static volatile int count = 12; // 控制烟花的数量
    //也可以使用AtomicTnteger来保证原子性和可见性
    static AtomicInteger acount = new AtomicInteger(12);
    // .get() : 获取数值
    // .getAndDecrement() ： 自减

    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        System.out.println("点燃引信");
        ses.scheduleAtFixedRate(()->{
            if(acount.get()==12) System.out.println("烟花来咯");
            System.out.println("正在发射第" + (13 - acount.get()) + "枚烟花");
            acount.getAndDecrement();
        }, 5, 2, TimeUnit.SECONDS);

        while(acount.get() != 0) {}
        // 监听烟花是否为最后一枚，是就关闭线程池
        System.out.println("新年快乐!");
        ses.shutdown();
    }
}
