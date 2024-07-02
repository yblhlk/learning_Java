package day6.Class;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Demo1 {
    public void test1(){
        System.out.println("测试1");
    }
    private int test2(Integer num) {
        System.out.println("测试2");
        return 1;
    }
    public void test3(String a, Integer b) {
        System.out.println("测试3");
    }

}

class A {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = Class.forName("day6.Class.Demo1");
        Method[] ms = c.getDeclaredMethods();
        for(Method m:ms) {
            String name = m.getName();
            Class[] cs = m.getParameterTypes();
            Class result = m.getReturnType();
            System.out.println(name + " " + Arrays.toString(cs) + " " + result);
        }

        //获取一个方法对象进行调用 (参数1：方法名， 参数2：方法参数的Class类型)
        Method m1 = c.getDeclaredMethod("test1");
        Method m2 = c.getDeclaredMethod("test2", Integer.class);
        Method m3 = c.getDeclaredMethod("test3", String.class, Integer.class);

        //调用方法（参数1：调用方法的对象  参数2：实参） 返回值：方法调用后的结果是Object类型
        Demo1 d = new Demo1();
        Object result1 = m1.invoke(d);
        m2.setAccessible(true); // 开启私有访问权限， m2是私有方法
        Object result2 = m2.invoke(d, 100);
        Object result3 = m3.invoke(d, "java", 1000);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
