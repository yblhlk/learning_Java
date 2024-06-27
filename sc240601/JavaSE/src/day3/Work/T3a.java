package day3.Work;

public class T3a implements Runnable{
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (T3a.class) {
            System.out.println(name + "开始进入山洞");
            System.out.println("剩余3S");
            System.out.println("剩余2S");
            System.out.println("剩余1S");
            System.out.println(name + "开始出洞");
        }
    }

    public static void main(String[] args) {
        T3a a = new T3a();
        for (int i = 0; i < 10; i++) {
            new Thread(a, "王" + i).start();
        }
    }
}
