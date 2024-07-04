package day1.Class;

// 通过线程模拟病人看病过程
// 第一类病人：是普通用户 50人 看病时长：500ms
// 优先级低一点
// 如果普通用户看完了10人优先看完VIP用户再看普通用户
// 第二类病人：是VIP用户 10人 看病时长：1000ms
// 优先级会更高一点。
public class Demo4 {
    public static void main(String[] args) {
        // 小细节：第二个参数是设置线程名字
        Thread vip = new Thread(()->{
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                System.out.println("第" + (i+1) + "个" + name + "在看病");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "VIP");
        Thread user = new Thread(()->{
            String name = Thread.currentThread().getName();
            for (int i = 0; i < 50; i++) {
                System.out.println("第" + (i+1) + "个" + name + "在看病");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(i==10) {
                    try {
                        vip.join(); //等待vip线程执行完后才会执行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "普通用户");

        // 只有当两个线程在同一时间点去抢CPU的时候 优先级高的才会先执行
        vip.setPriority(Thread.MAX_PRIORITY);
        user.setPriority(Thread.MIN_PRIORITY);
        vip.start();
        user.start();
    }
}
