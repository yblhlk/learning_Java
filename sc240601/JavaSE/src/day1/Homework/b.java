package day1.Homework;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class b extends Thread{
    // 在Java中，您可以使用java.time包下的LocalDateTime类来获取当前时间。
    // LocalDateTime t = LocalDateTime.now(); 使用t.toString()来打印
    @Override
    public void run() {
        while(true){
            // 1.创建当前系统时间（默认格式）
            // Tue Jun 25 10:35:14 CST 2024
            Date d = new Date();
            System.out.println(d);
            // 2.对日期类型做格式化处理（SimpleDateFormat类的format方法)
            // 格式：y年 M月(大写) d日 h(12进制时) H(24进制时) m分 s秒 S毫秒 a上午/下午
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
            String result = sdf.format(d);
            System.out.println(result);
            // 3.字符串转为日期类 (impleDateFormat类的sdf方法，要异常处理）
            // 字符串必须和SimpleDateFormat类的构造方法中的格式一致
            try {
                Date dd = sdf.parse("2024-06-25 10:41:11 上午");
                System.out.println(dd);
            } catch (ParseException e) {
                e.printStackTrace();
            }

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


// 拓.日期类型
// 1. Date (java.util.Date(绝大部分使用)  java.sql.Date(访问数据库特殊形式))
// 2. Calender 日历对象
// 3. LocalTime LocalDate LocalDateTime (1.8 后引入的日期类型)
