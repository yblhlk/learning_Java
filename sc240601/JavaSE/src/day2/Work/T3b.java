package day2.Work;

import java.util.concurrent.locks.ReentrantLock;

public class T3b {
    public static class Print {
        private ReentrantLock lock = new ReentrantLock();
        public int f = 0;

        public void printName() {
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 5; i++) {
                while (true) {
                    if (f == (int)(name.charAt(0)-65)) break;
                }
                lock.lock();
                System.out.print(name);
                f = (f + 1) % 3;
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Print p = new Print();

        new Thread(() -> {
            p.printName();
        }, "A").start();
        new Thread(() -> {
            p.printName();
        }, "B").start();
        new Thread(() -> {
            p.printName();
        }, "C").start();
    }
}
