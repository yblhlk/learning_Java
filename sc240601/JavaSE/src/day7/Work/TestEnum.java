package day7.Work;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestEnum {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        //枚举中的常量就是已经创建好的对象（也继承于Object)可以使用各种方法
        Enum e = Enum.ONE;
        int a = e.a;
        System.out.println(a);
        e.setB(9);
        System.out.println(e.getB());

        // 可以看到枚举类继承于lang包下的Enum，lang包下的Enum继承于Object
        System.out.println(e.getClass().getName());
        System.out.println(e.getClass().getSuperclass().getName());
        System.out.println(e.getClass().getSuperclass().getSuperclass().getName());

        //1.验证无法通过反射创建枚举类的对象
        System.out.println("验证无法通过反射创建枚举类的对象:");
        Class c = e.getClass();
        try {
            Constructor con = c.getDeclaredConstructor();
            con.setAccessible(true);
            Enum ee = (Enum) con.newInstance();
            System.out.println(con);
        } catch (Exception Exception) {
            System.out.println("找不到构造函数");
        }
        // 结论：直接报找不到构造函数。

        //2.通过反射来使用枚举类对象的方法
        //可以使用只有，构造方法被禁止了
        System.out.println("通过反射来所有枚举类对象的方法:");
        Method m = c.getDeclaredMethod("getB");
        System.out.println(m.invoke(e));

        //3.通过反射来获取枚举类对象的属性
        System.out.println("通过反射来所有枚举类对象的属性:");
        Field f = c.getDeclaredField("a");
        System.out.println(f.get(e));
    }
}

enum Enum {
    // 1. 定义常量(不是final是枚举对标构造方法的），有几个常量就调用几次构造方法。
    ONE(), TWO();
    // 2.定义构造方法，一个构造方法可以对应多个常量。
    private Enum() {}
    // 3.其他属性和方法可以随意创建
    public int a;
    private int b;

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}

enum Enum2 {
    // 1. 如果没有无参构造，创建常量时要传参。
    ONE(1),
    TWO("wang", 19),
    THREE(1, "wang", 99.5);

    // 2.创建和常量数量相同或更少的构造方法。
    private Enum2(int a) {}
    private Enum2(String name, int age) {}
    private Enum2(int id, String name, double score) {}
    private Enum2(int id, String name) {}
}
