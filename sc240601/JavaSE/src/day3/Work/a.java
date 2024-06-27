package day3.Work;

import sun.misc.Lock;

import java.util.concurrent.locks.ReentrantLock;

public class a{
    private ReentrantLock lock1 = new ReentrantLock();
    private ReentrantLock lock2 = new ReentrantLock();

    public void run1() {
        String name = Thread.currentThread().getName();
        lock1.lock();
        System.out.println(name + "成功申请到锁1");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.lock();
        System.out.println(name + "成功申请到两把锁");
        lock2.unlock();
        lock1.unlock();
    }
    public void run2() {
        String name = Thread.currentThread().getName();
        lock2.lock();
        System.out.println(name + "成功申请到锁2");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock1.lock();
        System.out.println(name + "成功申请到两把锁");
        lock1.unlock();
        lock2.unlock();
    }

    public static void main(String[] args) {
        a a = new a();
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{a.run1();}, "线程1").start();
            new Thread(()->{a.run2();}, "线程2").start();
        }

    }
}

// 在Java中，如果你使用synchronized关键字或者ReentrantLock，它们都是支持重入的，这意味着一个线程可以多次获取同一个锁而不会造成阻塞。ReentrantLock允许一个线程多次获得同一个锁，并且会记录获取锁的次数。每次线程获取锁时，计数增加；每次线程释放锁时，计数减少。只有当计数减少到0时，锁才会真正被释放，其他线程才能获取到该锁。
// 由于这种重入性，单个线程递归申请同一个锁不会导致死锁。线程可以在已经持有锁的情况下再次成功申请到锁，而不会阻塞自己。当然，这并不意味着递归申请锁是一个好的做法，因为它可能导致栈溢出（StackOverflowError），如果递归深度过深的话。
//
//总结一下，递归申请锁不会导致死锁，因为：
//死锁是多线程间的现象，而递归申请锁是单个线程的行为。
//Java中的锁机制（如synchronized和ReentrantLock）支持重入，允许单个线程多次获取同一个锁。