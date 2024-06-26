package day2.Work;

import java.util.Map;

public class T3a {
    public static class Pool {
        private int[] a = {10, 5, 20, 50, 100, 200, 500, 800, 2, 80, 300};
        private int size = 11;
        public Pool() {
        }

        public synchronized void getPrice() {
            if(size > 0) {
                int index = (int)(Math.random() * size);
                String name = Thread.currentThread().getName();
                System.out.println(name + "产生一个了" + a[index] + "元大奖");
                // 创建一个新数组
                int[] b = new int[size-1];
                // 将需要的内容拷贝到新数组
                for (int i = 0, j = 0; j < size; i++, j++) {
                    if(j != index)
                        b[i] = a[j];
                    else
                        i--;
                }
                // 改变指向
                a = b;
                size--;

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Pool pool = new Pool();
        new Thread(() -> {
            for (int i = 0; i < 11; i++) {
                pool.getPrice();
            }
        }, "抽奖箱1 ").start();
        new Thread(() -> {
            for (int i = 0; i < 11; i++) {
                pool.getPrice();
            }
        }, "抽奖箱2 ").start();
    }
}
