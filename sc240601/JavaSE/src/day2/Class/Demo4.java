package day2.Class;

//基于synchronized同步代码块实现购票功能，票的总数：100
//模拟两个窗口同时购票 有200个购票需求
public class Demo4 implements Runnable{
    static int sum = 100;
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        for (int i = 0; i < 100; i++) {
            synchronized(this){
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
        }
    }

    public static void main(String[] args) {
        Demo4 d = new Demo4();
        new Thread(d, "窗口一").start();
        new Thread(d, "窗口二").start();
    }
}
