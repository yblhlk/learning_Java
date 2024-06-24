package day1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

// 3. 实现Callable接口 实现call()
public class Demo3 implements Callable<Integer> {

    // 返回值：执行线程时 最终可以返回一个
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0)
                sum += i;
            // call方法可以抛出异常，所以不用在方法中处理sleep()方法抛出的异常。
            Thread.sleep(300);
            System.out.println("目前累加到：" + sum);
        }
        return sum;
    }

    public static void main(String[] args) {
        Demo3 d = new Demo3(); // Callable;
        //借助第三方类FutureTask来，使用线程
        //FutureTask实现了Runnable接口，
        //他可以封装Callable来实现线程
        FutureTask<Integer> ft = new FutureTask<>(d);
        Thread t = new Thread(ft);
        t.start();

        //获取线程的执行结果：属于线程阻塞（调用了call方法来获取结果）
        //调用了call方法，所以要抛异常
        //线程阻塞体现在：需要等待call执行结束后才会执行。
        Integer sum = null;
        try {
            sum = ft.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("0—100的偶数之和" + sum);
    }
}
