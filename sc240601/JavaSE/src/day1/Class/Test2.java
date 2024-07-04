package day1.Class;

// 创建三个线程ABC每个线程只打印名称
// 要求启动三个线程 无论如何运行结果都是：A B C
public class Test2 {
    public static void main(String[] args) {
        Thread c = new Thread(()->{
            while(true)
            {
                System.out.println(Thread.currentThread().getName());

            }
        }, "C");

        Thread b = new Thread(()->{

            while(true)
            {
                System.out.println(Thread.currentThread().getName());
                try {
                    c.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B");

        Thread a = new Thread(()->{
            while(true)
            {
                System.out.println(Thread.currentThread().getName());
                try {
                    b.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A");

        a.setPriority(Thread.MAX_PRIORITY);
        b.setPriority(Thread.NORM_PRIORITY);
        c.setPriority(Thread.MIN_PRIORITY);

        a.start();
        b.start();
        c.start();
    }
}
