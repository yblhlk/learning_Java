package day3.Class;

public class Demo7 {
    // volatile可以禁止重排序
    public volatile static int a, b, c, d;

    public static void main(String[] args) {
        int i = 0;
        while (true) {
            i++; // 为了计算次数
            a = b = c = d = 0; //每循环一次 清零
            Thread t1 = new Thread(() -> {
                a = 1;
                c = b;
                // 正常来说先执行a = 1; 再执行 c = b;
                // 如果重排序后，可能先执行c=b ,这时只需要保存c和d都是0，就可以确保是否重排序了。
            });
            Thread t2 = new Thread(() -> {
                b = 1;
                d = a;
            });
            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(c == 0 && d == 0) {
                System.out.println("执行了" + i + "出现了程序重排。");
                break;
            } else {
                System.out.println(i);
            }
        }
    }
}
