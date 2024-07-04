package day1.Class;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable implements Callable {

    @Override
    public Integer call() throws Exception {
        System.out.println("Callable线程被执行，我还能返回返回值哦");
        Thread.sleep(2000);
        return 1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable tc = new TestCallable();
        FutureTask<Integer> f = new FutureTask<>(tc);
        new Thread(f).start();

        // FutureTask的get()方法会被Call线程阻塞，直到它执行玩，才会获得它的返回值。
        System.out.println(f.get());
    }
}
