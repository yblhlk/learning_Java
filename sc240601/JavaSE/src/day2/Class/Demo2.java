package day2.Class;

public class Demo2 {
    public static void main(String[] args) {
        Demo2 d = new Demo2();
        new Thread(()-> d.a(), "A").start();
        new Thread(()-> d.a(), "AA").start();
        new Thread(()-> d.b(), "B").start();
        new Thread(()-> Demo2.c(), "C").start();
        new Thread(()-> d.d(), "D").start();

        // 2. 同步代码块
        // synchronized(对象) -> 锁this
        // synchronized(类名.class) -> 锁类对象
        new Thread(()->{
            synchronized(Demo2.class){
                String name = Thread.currentThread().getName();
                System.out.println(name + "开始执行同步代码块");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "结束执行同步代码块");
            }
        }, "E").start();

    }

    // 同步方法，锁的是this
    public synchronized void a() {
        String name = Thread.currentThread().getName();
        System.out.println(name + "开始执行a方法");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "结束执行a方法");
    }

    // 同步方法，锁的是this
    public synchronized void b() {
        String name = Thread.currentThread().getName();
        System.out.println(name + "开始执行b方法");
        System.out.println(name + "结束执行b方法");
    }

    // 静态同步方法，锁的是类对象（Demo2.class)
    public static synchronized void c() {
        String name = Thread.currentThread().getName();
        System.out.println(name + "开始执行c静态同步方法");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "结束执行c静态同步方法");
    }

    public static void d() {
        String name = Thread.currentThread().getName();
        System.out.println(name + "开始执行d方法");
        System.out.println(name + "结束执行d方法");
    }
}
