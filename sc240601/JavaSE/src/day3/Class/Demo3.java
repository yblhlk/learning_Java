package day3.Class;

import java.util.concurrent.locks.ReentrantLock;

// 公平锁和不公平锁
public class Demo3 {
    // 根据有参构造：true 表示公平锁， false 表示不公平锁， 默认值为false；
    ReentrantLock lock = new ReentrantLock(true); // 开启公平锁
    public void test(){
        String name = Thread.currentThread().getName();
        try {
            lock.lock();
            System.out.println(name + "获得了锁，开始执行……");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(name + "结束执行，解锁");
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        Demo3 d = new Demo3();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                while (true)
                    d.test();
            }, "wang"+i).start();
        }
    }
}
