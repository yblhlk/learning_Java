package day6.Class;

// 注意反射的包一定包含了reflect
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// 通过反射创建对象
public class Demo2 {
    public Demo2() {
        System.out.println("无参构造");
    }
    public Demo2(Integer n) {
        System.out.println("一个参数的构造");
    }
    public Demo2(String a, Integer b) {
        System.out.println("两个参数的构造");
    }
}

// 测试通过反射来创建对象
class TestDemo2 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //1. 通过类对象的newInstance()方法直接创建
        Class c1 = Demo2.class;
        //底层原理：调用无参构造方法（只能调用无参构造），而且无法设置权限。
        Demo2 d = (Demo2)c1.newInstance();
        System.out.println(d);

        // 2. 通过构造方法类的newInstance方法来创建对象
        Constructor con1 = c1.getDeclaredConstructor();
        Constructor con2 = c1.getDeclaredConstructor(Integer.class);
        Constructor con3 = c1.getDeclaredConstructor(String.class, Integer.class);
        //创建对象 底层原理：可以使用任意的构造方法, 并且可以设置权限
        Demo2 d1 = (Demo2) con1.newInstance();
        con1.setAccessible(true); //开启权限
        Demo2 d2 = (Demo2) con2.newInstance(100);
        Demo2 d3 = (Demo2) con3.newInstance("java", 1000);
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
    }
}
