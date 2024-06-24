package day1;

// 继承Thread类 重run()
// 在java中 和Thread相关的类 才是线程 才能使用线程的常用方法
public class Demo1 extends Thread{
    @Override
    public void run() {
        // 通过当前执行的线程来获取线程名称(Thread.currentThread() : 获取当前线程， getName(): 获取名字)
        String name = Thread.currentThread().getName();
        // 这个run方法就表示线程的执行逻辑
        for (int i = 0; i < 10; i++) {
            System.out.println(name + " " + i);
        }
    }

    // 运行main方法也会开启一个线程：名字为main（主线程）
    public static void main(String[] args) {
        Demo1 d1 = new Demo1();
        d1.setName("d1"); // 设置线程的名字
        Demo1 d2 = new Demo1();
        d2.setName("d1"); // 设置线程的名字

        //d1.run()不是开启线程，只是用main函数在用对象调用方法。
//        d1.run();
//        d2.run();

        //d1.start()才是开启线程，执行顺序看谁先抢占到CPU
        d1.start();
        d2.start();
    }
}
