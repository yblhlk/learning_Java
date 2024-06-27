package day3.Class;

import java.util.concurrent.locks.ReentrantLock;

public class Demo5 {

    private static ReentrantLock lock = new ReentrantLock();
    public void deadlock() {
        lock.lock();
        deadlock();
        lock.lock();
    }

    public static void main(String[] args) {
        Demo5 d1 = new Demo5();
        Demo5 d2 = new Demo5();
        new Thread(()->{
            synchronized (d1) {
                System.out.println("线程1获取d1的锁，等待获取d2的锁");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (d2) {
                    System.out.println("线程1获取d1和d2两把锁");
                }
                System.out.println("线程1释放d2的锁");
            }
        }, "线程1").start();
        new Thread(()->{
            synchronized (d2) {
                System.out.println("线程2获取d2的锁，等待获取d1的锁");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (d1) {
                    System.out.println("线程2获取d1和d2两把锁");
                }
                System.out.println("线程2释放d2的锁");
            }
        }, "线程2").start();
    }
}