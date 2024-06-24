package day1.Homework;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Locale;

public class b extends Thread{
    // 在Java中，您可以使用java.time包下的LocalDateTime类来获取当前时间。
    // LocalDateTime t = LocalDateTime.now(); 使用t.toString()来打印
    @Override
    public void run() {
        while(true){
            LocalDateTime t = LocalDateTime.now();
            System.out.println(t.toString());
            // 休眠1秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new b();
        t.start();
    }
}
