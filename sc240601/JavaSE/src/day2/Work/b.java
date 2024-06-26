package day2.Work;


import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class b {
    public static class Cache{
        private Map<String, Integer> score;
        private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        public Cache() {
            score  = new Hashtable<>();
        }

        public void read() throws InterruptedException {
            String tname = Thread.currentThread().getName();
            // 加读锁
            if(rwLock.readLock().tryLock(30, TimeUnit.SECONDS)){
                try {
                    System.out.println(tname + "正在读取成绩单的信息……");
                    Thread.sleep(300);
                    if(score.isEmpty()) {
                        System.out.println("成绩单中还没有信息哦。");
                    } else {
                        for (Map.Entry<String, Integer> s : score.entrySet()) {
                            System.out.println("    姓名：" + s.getKey() + ", 成绩：" + s.getValue());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(tname + "已经完成对成绩单的读取。");
                    //释放读锁，最好中finally中释放
                    rwLock.readLock().unlock();
                }
            } else {
                System.out.println("读取成绩单失败！");
            }
        }

        public void write(String name, int score) throws InterruptedException {
            String tname = Thread.currentThread().getName();
            // 加写锁
            if(rwLock.writeLock().tryLock(30, TimeUnit.SECONDS)){
                try {
                    System.out.println(tname + "正在向成绩单中写入信息……");
                    this.score.put(name, score);
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(tname + "已经完成对成绩单的写入。");
                    //释放写锁，最好中finally中释放
                    rwLock.writeLock().unlock();
                }
            } else {
                System.out.println("写入成绩单失败！");
            }
        }
    }

    public static void main(String[] args) {
        Cache cache = new Cache();
        for (int i = 0; i < 10; i++) {
            double f = Math.random()*1000 / 10;
            String name = "王" + i;
            new Thread(() -> {
                if(f > 70) {
                    try {
                        cache.read();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        cache.write(name, (int)f);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }, "王" + i).start();
        }
    }
}

// 总结：
// 遍历集合的方式：在Java中，Map 集合的遍历稍有不同于其他集合，因为 Map 集合中保存的是键值对（Key-Value Pair）。
// 增强for循环（也称为“foreach”循环）不能直接用于遍历 Map，
// 但你可以遍历 Map 的键集（keySet）、值集（values）或键值对集（entrySet）。
//        // 创建一个HashMap对象
//        Map<String, Integer> map = new HashMap<>();
//        map.put("One", 1);
//        map.put("Two", 2);
//        map.put("Three", 3);
//        map.put("Four", 4);
//
//        // 遍历键
//        System.out.println("遍历键:");
//        for (String key : map.keySet()) {
//            System.out.println(key);
//        }
//
//        // 遍历值
//        System.out.println("遍历值:");
//        for (Integer value : map.values()) {
//            System.out.println(value);
//        }
//
//        // 遍历键值对
//        System.out.println("遍历键值对:");
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//        }

