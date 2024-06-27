package day3.Work;

public class T3b {
    public static void main(String[] args) {
        Product product = new Product("T-shirt", 100000, 60000);
        Producers producers = new Producers(product);
        Consumers consumers = new Consumers(product);
        new Thread(producers).start();
        new Thread(consumers).start();
    }
}

class Product {
    private String name;
    private int maxCount;
    private volatile int count;

    public Product(String name, int maxCount, int count) {
        this.name = name;
        this.maxCount = maxCount;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCount() {
        return maxCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

class Producers implements Runnable {
    Product p;
    public volatile static int pflag; //保证数据的可见性,不然在其他线程中修改了,没有更新到主线程.

    public Producers(Product p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true) {
            while (true) {
                if (Producers.pflag == 1) {
                    System.out.println("收到订单不足信号，生产者开始生产" + p.getName());
                    break;
                }
            }
            synchronized (T3b.class) {
                while (p.getCount() < p.getMaxCount()) {
                    int add = (int) (Math.random() * p.getMaxCount());
                    int newCount = add + p.getCount();
                    if (newCount > p.getMaxCount()) {
                        add = p.getMaxCount() - p.getCount();
                    }
                    p.setCount(p.getCount() + add);
                    try {
                        Thread.sleep(add % 2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("生产者生产了" + add + "件产品，当前产品数量为：" + p.getCount());
                }
                System.out.println("生产者结束生产, 向消费者发送购买信息。");
                Consumers.cflag = 1;
                Producers.pflag = 0;
            }
        }
    }
}

class Consumers implements Runnable {
    Product p;
    public volatile static int cflag = 1;

    public Consumers(Product p) {
        this.p = p;
    }

    @Override
    public void run() {
        while (true) {
            while (true) {
                if (Consumers.cflag == 1) {
                    System.out.println("生产者生产完成，消费者开始购物。");
                    break;
                }
            }
            synchronized (T3b.class) {
                while (true) {
                    int orderCount = (int) (Math.random() * p.getMaxCount());
                    try {
                        Thread.sleep(orderCount % 2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (orderCount > p.getCount()) {
                        System.out.println("消费者准备购买" + orderCount + "件" + p.getName() + "，当前库存为：" + p.getCount() + "件，库存不足，向生产者发送生产信息。");
                        break;
                    } else {
                        p.setCount(p.getCount() - orderCount);
                        System.out.println("消费者购买了" + orderCount + "件商品，当前库存剩余：" + p.getCount() + "件.");
                    }
                }
                Consumers.cflag = 0;
                Producers.pflag = 1;
            }
        }
    }
}