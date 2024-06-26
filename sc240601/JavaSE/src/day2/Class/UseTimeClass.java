package day2.Class;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UseTimeClass {
    public static void main(String[] args) throws ParseException {
    //一、Date类型
        // 1.创建当前系统时间（默认格式）
        // Tue Jun 25 10:35:14 CST 2024
        System.out.println("--------------------Date类型--------------------");
        Date d = new Date();
        System.out.println(d);
        // 2.对日期类型做格式化处理（SimpleDateFormat类的format方法)
        // 格式：y年 M月(大写) d日 h(12进制时) H(24进制时) m分 s秒 S毫秒 a上午/下午
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String result = sdf.format(d);
        System.out.println(result);
        // 3.字符串转为日期类 (impleDateFormat类的sdf方法，要异常处理）
        // 字符串必须和SimpleDateFormat类的构造方法中的格式一致
        Date dd = sdf.parse("2024-06-25 10:41:11 上午");
        System.out.println(dd);
    // sql.Date <==> util.Date
        java.sql.Date sqld = new java.sql.Date(dd.getTime());
        java.util.Date utild = new Date(sqld.getTime());

    // 二、 Calendar类 11:05
        System.out.println("----------------Calendar类-----------------");
        //Calender日历对象
        //优点: 提供很多封装好的常量  获取日期指定内容
        //缺点: 不是日期类型 需要封装Date类型才可以使用
        //1.创建日历对象
        Calendar cal=Calendar.getInstance();
        //2.设置时间
        Date ddd = new Date();
        System.out.println(ddd);
        cal.setTime(ddd);
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH));
        System.out.println(cal.get(Calendar.DATE));
        //3.日历会针对于这个日期 计算好很多常量
        //cal.get(Calender.常量)
        //4.日历做一些简单运算
        //cal.add(Calendar.常量,数值);
        cal.add(Calendar.MONTH,10);
        System.out.println(cal.get(Calendar.YEAR));  //获取年
        System.out.println(cal.get(Calendar.MONTH)); //获取月
        System.out.println(cal.get(Calendar.DATE));  //获取日
        cal.set(2012,10,20);
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH));
        System.out.println(cal.get(Calendar.DATE));

        // 1.8后的时间类
        System.out.println("-------------------1.8后的时间类------------------");
        //jdk1.8开始支持新日期格式
        //instant: 代表时间戳(时间完整格式)
        //LocalDate: 代表年-月-日
        //LocalTime: 代表时:分:秒
        //LocalDateTime: 代表年-月-日 时:分:秒
        //now() 静态方法表示当前时间
        //of() 静态方法 手动传入年月日 时分秒
        Instant in=Instant.now();
        System.out.println("时间戳:"+in);
        LocalDate ld=LocalDate.now();
        System.out.println("日期:"+ld);
        LocalDate ld2=LocalDate.of(2014,5,15);
        System.out.println("日期:"+ld2);

        //2. jdk1.8日期类型提供很多方法 获取日期指定部分
        //日期.getXXX()
        DayOfWeek dw=ld.getDayOfWeek();
        System.out.println("星期:"+dw);

        //3.提供修改的方法
        //日期.withXXX()
        ld=ld.with(DayOfWeek.WEDNESDAY);
        System.out.println("修改成星期三:"+ld);

        //4.对日期做加减法
        //日期.plusXXX() 做加法  只需要会一个(+ 正数和负数 )
        //日期.minusXXX() 做减法
        ld=ld.plusWeeks(1);
        System.out.println("加完一周:"+ld);
        ld=ld.plusMonths(-1);
        System.out.println("减一个月:"+ld);
        //ld.isLeapYear() 计算闰年
        //日期.until(日期).getDays  .getYears: 获取两个日期差
        LocalDate ld1=LocalDate.of(2000,10,20);
        LocalDate now=LocalDate.now();
        int year=ld1.until(now).getYears();
        System.out.println("你多大了:"+year);
        int a = 1/0;
    }
}

// 拓.日期类型
// 1. Date (java.util.Date(绝大部分使用)  java.sql.Date(访问数据库特殊形式))
// 2. Calender 日历对象
// 3. LocalTime LocalDate LocalDateTime (1.8 后引入的日期类型)