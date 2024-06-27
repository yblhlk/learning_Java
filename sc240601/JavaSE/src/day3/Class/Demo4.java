package day3.Class;

import java.util.concurrent.atomic.AtomicInteger;

// 乐观锁CAS的实现
public class Demo4 {
    // 要通过Java内置的一个线程安全的计数器(AtomicInteger)。
    AtomicInteger count = new AtomicInteger(0);
    public void increment(){
        int currentValue; // 当前值
        int newValue; // 保存新的值
        String name = Thread.currentThread().getName();

        while(true) {
            currentValue = count.get(); //获取当前值
            newValue = currentValue + 1; // 如果可以修改 要把计数器的数字+1

            //这里中间是加锁的代码区域，但还需要回滚，不用回滚还不能完全实现（失败了要进行回滚）

            //表示尝试修改: 计数器的值 和 currentValue 是一样的 没有
            //计数器的值更新为 newValue 否则失败。
            //count 和 currentValue 进行比较，相等就更新为newValue 并且返回true。不相等就不
            boolean b = count.compareAndSet(currentValue, newValue);
            if(b) break;
            else System.out.println(name + "尝试更新数据，但失败！" + "当前值为" + currentValue + "已经被其他线程更新成：" + count.get());;

        }
    }

    public static void main(String[] args) {
        Demo4 d = new Demo4();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                d.increment();
            }
        }, "A");
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 10000; i++) {
                d.increment();
            }
        }, "B");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("最终计数器的值为：" + d.count.get());
    }
}
