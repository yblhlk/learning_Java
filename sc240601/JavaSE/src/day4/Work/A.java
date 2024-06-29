package day4.Work;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class A {
    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(3); //不需要我们来new
        for (int i = 0; i < 100; i++) {
            int j = i+1;
            ex.execute(()->{
                Thread.currentThread().setName("告"+j+"人");
                String name = Thread.currentThread().getName();
                System.out.println(name + "买到票了！");
            });
        }
        ex.shutdown();
    }
}
