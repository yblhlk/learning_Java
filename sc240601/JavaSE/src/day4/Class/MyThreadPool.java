package day4.Class;

import java.util.concurrent.*;

// 实现自定义线程池
public class MyThreadPool {
    // 3. 定义七个核心参数
    // a. 定义核心线程数
    private static final int core_num = Runtime.getRuntime().availableProcessors(); //设置为电脑CPU的核数
    // b. 定义最大核心线程数
    private static final int max_num = 20;
    // c. 定义(多余线程的）存活时间
    private static final long alive_time = 30;
    // d. 定义时间单位
    private static final TimeUnit unit = TimeUnit.SECONDS;
    // e. 工作队列（任务队列）
    private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
    // f. 线程工厂
    private static  ThreadFactory factory = Executors.defaultThreadFactory();
    // g. 定义拒绝策略
    private static RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

    // 1.定义一个线程池对象，来表示线程池
    private static ExecutorService pool;

    static {
        // 2.传入七个核心参数
        // 3. 定义七个核心参数（在上面）
        pool = new ThreadPoolExecutor(core_num, max_num, alive_time
        , unit, queue, factory, handler);
    }

    // 4. 执行自定义线程池的任务 执行成功返回true 否则false
    public static boolean myExecute1(Runnable r){
        try {
            pool.execute(r);
            return true;
        } catch (Exception e) {
            System.out.println("任务执行失败");
            return false;
        }
    }

    // 5.执行自定义线程池的任务（Callable c)
    // static后面的<A> 表示定义一个A泛型，会读取调用者传入的类型
    public static <A> Future<A> myExecute2(Callable<A> c) {
        Future f = null;
        try {
            f = pool.submit(c);
        } catch (Exception e) {
            System.out.println("任务执行失败");
        }
        return f;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            MyThreadPool.myExecute1(()->{
                System.out.println("123");
            });
            Future<Integer> f = MyThreadPool.myExecute2(()->{
                System.out.println("456");
                return 1;
            });
            System.out.println("callable的结果：" + f.get());
        }
        pool.shutdown();
    }
}
