package day1.Class;

// 守护线程：服务用户线程的 用户线程结束了 守护线程自动结束
// 用户线程：Thread，实现Runnable接口，……
public class GuardDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            while (true) {
                //细节1：run()方法中不能通过抛出异常来解决异常，因为实现的接口中没有抛异常，重写时，子类的异常不能多于父类
                try {
                    Thread.sleep(500); //控制线程休眠时间，单位是__毫秒__
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("守护线程开始执行了");
            }
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                //细节1：run()方法中不能通过抛出异常来解决异常，因为实现的接口中没有抛异常，重写时，子类的异常不能多于父类
                try {
                    Thread.sleep(500); //控制线程休眠时间，单位是__毫秒__
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(i);
            }
        });
        //让t1对象成为守护线程
        t1.setDaemon(true); //设置守护线程 （细节2：守护线程不能在线程启动start()后设置会报错
        t1.start();
        t2.start();
    }
}
