package day2.Class;

import java.util.concurrent.locks.ReentrantReadWriteLock;

// 测试读写锁
public class Demo7 {
    static int data; //假设这是共享的数据
    ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    // 读取共享数据的方法
    public void read() {
        String name = Thread.currentThread().getName();
        try {
            // 获取读锁
            rwlock.readLock().lock();
            System.out.println(name + ": 进行为期两秒的数据读取操作");
            Thread.sleep(2000);
            System.out.println(name + ": 读取操作结束。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放读锁 , 始终要记住释放锁要在finally中
            rwlock.readLock().unlock();
        }
    }

    // 对共享数据进行写入的方法
    public void write() {
        String name = Thread.currentThread().getName();
        try {
            // 获取写锁
            rwlock.writeLock().lock();
            System.out.println(name + ": 进行为期两秒的数据写入操作" + data);
            data += 100;
            Thread.sleep(2000);
            System.out.println(name + ": 写入操作结束。" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放读锁 , 始终要记住释放锁要在finally中
            rwlock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        Demo7 d = new Demo7();
        int i = 0;
        for (; i < 5; i++) {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    d.read();
                }
            }, "线程" + i).start();
            new Thread(()->{
                for (int j = 0; j < 10; j++)
                    d.write();
            }, "线程" + i).start();
        }

        for (; i < 15; i++) {
        }
    }
}
