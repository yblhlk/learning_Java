package day2.Work;

public class a {
    public static class BankAccount {
        String name = Thread.currentThread().getName();
        private double balance;

        public BankAccount(double balance) {
            this.balance = balance;
        }

        public double getBalance() {
            return balance;
        }

        public synchronized void deposit(double amount) {
            String name = Thread.currentThread().getName();
            System.out.println(name + "开始存款, 准备存款" + String.format("%.3f", amount) + "元");
            this.balance += amount;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + "存款" + String.format("%.3f", amount) + "元，当前账户余额为：" + String.format("%.3f", balance));
        }

        public synchronized void withdraw(double amount) {
            String name = Thread.currentThread().getName();
            System.out.println(name + "开始取款, 准备取款" + String.format("%.3f", amount) + "元");
            if(balance >= amount) {
                this.balance -= amount;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + "取款" + String.format("%.3f", amount)  + "元，当前账户余额为：" + String.format("%.3f", balance));
            } else {
                System.out.println(name + "，抱歉当前账户余额不足，取款失败");
            }

        }
    }

    public static void main(String[] args) {
        BankAccount b = new BankAccount(1000);
        System.out.println("当前账户余额为" + String.format("%.3f", b.getBalance()) + "元。");

        for (int i = 0; i < 10; i++) {
            final double flag = Math.random()*100;
            final double count = Math.random()*100;
            new Thread(()->{
                if(flag > 50) {
                    b.deposit(flag * count);
                } else {
                    b.withdraw(flag * count);
                }
            }, "wang" + i).start();
        }

    }
}

// 总结：
// 1.在Java中，你可以使用java.text.DecimalFormat类或者String.format()方法来控制浮点数的打印格式。
    // a. 使用 DecimalFormat
    //    double number = 123456.789;
    //    DecimalFormat df = new DecimalFormat("#.00");
    //    System.out.println(df.format(number));  // 输出 "123456.79"
    // b. 使用String.format()
    //    double number = 123456.789;
    //    System.out.println(String.format("%.2f", number));  // 输出 "123456.79"