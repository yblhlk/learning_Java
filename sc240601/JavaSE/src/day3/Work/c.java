package day3.Work;

import java.util.Random;

public class c {
    public static void main(String[] args) {
        new Thread(new Change()).start();
        new Thread(new Buy()).start();
    }
}

class LowPrice {
    private static int money = 1000;

    public int price() {
        return 1000;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int m) {
        money = m;
    }
}

class Change implements Runnable{
    @Override
    public void run() {
        LowPrice l = new LowPrice();
        while (true) {
            Random random = new Random();
            int f1 = random.nextInt(2);
            int f2 = random.nextInt(10);
            synchronized (LowPrice.class) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (f1 == 0) {
                    if(l.getMoney() > 200)
                        l.setMoney(l.getMoney() - f2);
                    System.out.println("零点已过，当前价格调整为：" + l.getMoney());
                } else {
                    l.setMoney(l.getMoney() + f2);
                    System.out.println("零点已过，当前价格调整为：" + l.getMoney());
                }
            }
        }
    }
}

class Buy implements Runnable {
    @Override
    public void run() {
        LowPrice l = new LowPrice();
        while(true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (LowPrice.class) {
                if(l.getMoney() >= l.price()) {
                    System.out.println("价格太高，再等等。当前价格为：" + l.getMoney());
                } else {
                    System.out.println("等等党大胜利！当前价格为：" + l.getMoney());
                }
            }
        }
    }
}
