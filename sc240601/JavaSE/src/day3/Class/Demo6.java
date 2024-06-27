package day3.Class;

// 测试volatile的原子性
public class Demo6 implements Runnable{
    private volatile int count; //模拟购物车中物品的数量
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            //count++ 其实等价于count = count+ 1；
            //包3个操作：1.读取count变量， 2.计算count+1的数值 3.给count重写赋值
            // 所以它属于复合操作
            // 数据丢失的原因：假设线程1和2都先读取数据 都需要经过count+1计算
            // 突然线程2先count赋值 后面线程1开始赋值的时候 还认为count是未被2修改的值。 就出现了数据丢失。
            // 解决方案：给这种复合操作添加同步锁。
            synchronized(this) {
                count++;
                System.out.println("购物车中物品的数量为：" + count);
            }
        }
    }

    public static void main(String[] args) {
        Demo6 d = new Demo6();
        for (int i = 0; i < 100; i++) {
            new Thread(d).start();
        }
    }
}
