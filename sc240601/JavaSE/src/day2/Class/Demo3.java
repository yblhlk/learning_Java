package day2.Class;

//基于synchronized实现购票功能，票的总数：100
//模拟两个窗口同时购票 有200个购票需求

public class Demo3 {
    static int sum = 100;
    public synchronized void buy ()
    {
        String name = Thread.currentThread().getName();
        if(sum > 0){
            sum--;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "：出售车票一张，还剩余车票：" + sum);
        }
        else{
            System.out.println(name + "：抱歉车票已售罄。");
        }
    }
    public static void main(String[] args) {
        Demo3 d = new Demo3();
        Thread t1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                d.buy();
            }
        }, "窗口一");
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                d.buy();
            }
        }, "窗口二");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("剩余车票：" + sum);
    }
}
