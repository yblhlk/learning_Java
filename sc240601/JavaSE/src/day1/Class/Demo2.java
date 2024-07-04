package day1.Class;

// 实现Runnable接口 实现run()
// 他不是线程，只是线程的执行规则，如果创建线程还是借助于Thread类
// 这种方式和使用线程池的方式差不多。
public class Demo2 implements Runnable{
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 10; i++)
            System.out.println(name + " " + i);
    }

    public static void main(String[] args) {
        Demo2 d = new Demo2();     // d就是一个执行规则，没有setName() & start()
        Thread t1 = new Thread(d); // t才是线程
        Thread t2 = new Thread(d); // t才是线程
        //t1.setName("1");
        t1.start();
        t2.start();

        // 2. 使用匿名内部类来实现
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        // 使用匿名内部类来创建 接口的实例对象
        Runnable r = new Runnable() {
            @Override
            public void run() {

            }
        };

        // 3. 使用Lambda表达式
        Thread t4 = new Thread(()->{});
        Runnable r2 = ()->{};
    }
}
