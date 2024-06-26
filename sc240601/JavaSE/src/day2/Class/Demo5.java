package day2.Class;

import java.util.concurrent.locks.ReentrantLock;

// 测试Lock锁
public class Demo5 {
    // lock锁: 有排它锁功能 也有公平锁和非公平锁的功能
    ReentrantLock lock = new ReentrantLock(); //它锁的就是自己

    public void test()
    {
        String name = Thread.currentThread().getName();
        try{
            lock.lock(); //加锁
            System.out.println(name + "开始执行");
            Thread.sleep(4000);
            System.out.println(name + "结束执行");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); //释放锁，为了防止异常发生导致锁没有释放，会把释放锁放到finally里面
        }
    }

    public static void main(String[] args) {
        Demo5 d = new Demo5();
        Demo5 d2 = new Demo5();
        new Thread(()->d.test(), "线程一").start();
        new Thread(()->d2.test(), "线程二").start();
    }
}
