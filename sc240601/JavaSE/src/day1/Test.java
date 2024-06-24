package day1;

public class Test {
    static int i = 0;
    //1.请创建两个线程，对同一个变量i从0到10万进行输出，要求一个输出奇数，一个实现偶数。
    public static void main(String[] args) {
        final boolean flag = true;

        Runnable r = ()->{
            String name = Thread.currentThread().getName();
            for (; i <= 100000; i++) {
                if(i%2 ==0 && flag)
                    System.out.println(name + " : " + i);
                if(i%2 !=0 && !flag)
                    System.out.println(name + " : " + i);
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
}
