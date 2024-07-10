//a.有100份礼品,两人同时发送，当剩下的礼品小于10份的时候则不再送出，利用多线程模拟该过程并将线程的名称打印出来.

package day7.Work;

public class A extends Thread{
    // 给属性和方法加上static的作用：1.能通过类直接访问，而不需要创建对象。 2.被所有对象共享。
    static private volatile int gift = 100;

    private void distribution () {
        String name = Thread.currentThread().getName();
        while(true) {
            synchronized(A.class) {
                if (gift > 10) {
                    System.out.println(name + "分发了一个礼物, 还剩" + gift + "个礼物。");
                    gift--;
                } else {
                    System.out.println("孩子们礼物没咯。还有10个是我自己的。");
                    break;
                }
            }
        }
    }
    @Override
    public void run() {
        distribution();
    }

    public static void main(String[] args) {
        A a = new A();
        new Thread(a, "红色圣诞老人").start();
        new Thread(a, "蓝色圣诞老人").start();
    }
}
