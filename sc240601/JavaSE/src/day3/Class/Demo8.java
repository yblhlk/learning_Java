package day3.Class;

// 测试volatile可以保证数据的可见性
public class Demo8 {
    public static void main(String[] args) {
        new My1().start();
        new My2().start();

    }
}

class Money {
    public static int money = 100000;
}

class My1 extends Thread {
    @Override
    public void run() {
        while(Money.money == 100000) {
            System.out.println();
        }
        System.out.println("存款被偷了！！！");
    }
}

class My2 extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Money.money = 50000;
    }
}

// 原因在于：
// 主内存：线程访问共享变量的位置
// 线程工作内存：每个线程运行都要一个自己独立的空间。用于执行自己线程的内容。
// 原因在于：当变量money没有被volatile 修饰的时候
// my1先开始 先把主内存money变量 读取到自己工作内存中
// my2睡眠1秒后 去修改money变量的值 先在自己的工作内存中去修改 再去修改主内存的值
// money 修改为50000后 my1线程由于不知道对方工作内存的结果 认为money一直是100000

// 当变量money被volatile修饰的时候，
// my2修改完后 主内存的值也会刷新
// my2的工作内存money会失效 强制刷新主内存的值
// my2修改了 my1就可见了