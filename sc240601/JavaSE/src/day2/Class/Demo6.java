package day2.Class;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

//基于Lock锁实现购票功能，票的总数：100
//模拟两个窗口同时购票 有200个购票需求
public class Demo6 {
    static int sum = 100;
    static ReentrantLock r = new ReentrantLock();
    public  void buy ()
    {
        String name = Thread.currentThread().getName();
        //防止死锁可使用tryLock()方法进行加锁，如果在一段时间内没有获取到锁，就会放弃获取
        //我们还可以设置tryLock()方法等待的时间。 int + TimeUnit.SECONDS
        try {
            if(r.tryLock(5, TimeUnit.SECONDS)){
                try{
                    if(sum > 0){
                        sum--;
                        Thread.sleep(200);
                        System.out.println(name + "：出售车票一张，还剩余车票：" + sum);
                    }
                    else{
                        System.out.println(name + "：抱歉车票已售罄。");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    r.unlock();
                }
            }
            else {
                System.out.println("系统拥挤 >_< !");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        Demo6 d = new Demo6();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                d.buy();
            }
        }, "窗口一");
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                d.buy();
            }
        }, "窗口二");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("剩余车票：" + sum);
    }
}
