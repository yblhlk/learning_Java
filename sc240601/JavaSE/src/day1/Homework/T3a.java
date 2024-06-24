package day1.Homework;

public class T3a implements Runnable {
    static int balance = 1000;

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        while(balance > 0) {
            if(name.equals("A")){
                balance -= 200;
                System.out.println(name +  "从柜台取钱200 。");
            }
            if(name.equals("B")) {
                balance -= 100;
                System.out.println(name += "从ATM取钱100 。");
            }
        }
    }

    public static void main(String[] args) {
        Thread A = new Thread(new T3a(), "A");
        Thread B = new Thread(new T3a(), "B");
        A.start();
        B.start();
    }
}


