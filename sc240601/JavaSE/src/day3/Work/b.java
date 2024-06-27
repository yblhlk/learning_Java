package day3.Work;

// 方式一：通过一个变量+死循环来控制打印顺序。
public class b {
    int f;

    public void test1() {
        for (int i = 1; i <= 26; i++) {
            int num1 = i * 2 - 1;
            int num2 = i * 2;
            while (f == 1) {
            }
            synchronized (b.class) {
                System.out.print(num1);
                System.out.print(num2);
                f = 1;
            }
        }
    }

    public void test2() {
        for (int i = 1; i <= 26; i++) {
            char c = (char) (64 + i);
            while (f == 0) {
            }
            synchronized (b.class) {
                System.out.print(c);
                f = 0;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        b bb = new b();
        new Thread(() -> {
            bb.test1();
        }).start();
        new Thread(() -> {
            bb.test2();
        }).start();

        Thread.sleep(200);
        System.out.println();
        new Thread(() -> {
            int num=0;
            for (int i = 0; i < 26; i++) {
                synchronized (a.class) {
                    System.out.print(++num);
                    System.out.print(++num);
                    a.class.notifyAll();
                    // wait让线程进入等待,还可以释放锁.
                    // wait() 可以释放锁,所以要通过锁对象来调用.
                    try {
                        a.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        Thread.sleep(200);
        new Thread(() -> {
            for (int i = 0; i < 26; i++) {
                synchronized (a.class) {
                    System.out.print((char)( 65 + i));
                    a.class.notifyAll();
                    // wait让线程进入等待,还可以释放锁.
                    // wait() 可以释放锁,所以要通过锁对象来调用.
                    try {
                        a.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}

// 方式二：使用公平锁（公平锁天然就能实现两个线程的交替打印。
