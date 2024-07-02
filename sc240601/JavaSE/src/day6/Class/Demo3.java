package day6.Class;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// 通过反射实现通用方法的调用
public class Demo3 {
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

    private int test4(int num) {
        System.out.println("测试4");
        return 1;
    }
}

class TestDemo3 {
    // 通用方法 反射可以调用任意对象的任意方法
    // 返回值 ： 目标方法的fanhuiz
    // 参数1：调用方法的对象； 参数2 ：方法名：参数3：方法的实参
    // 存在的bug : 如果传递的是基本类型， 会自动转换额外i封装类型， 导致了方法不存在
    public static Object base1 (Object o, String name, Object... params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = o.getClass();
        Class[] args = new Class[params.length];
        for (int i = 0; i < params.length; i++) {
            args[i] = params[i].getClass();
        }
        //创建一个方法对象
        Method m = c.getDeclaredMethod(name, args);
        //一定要记得开放权限
        m.setAccessible(true);
        //调用创建好的方法对象
        Object result = m.invoke(o, params);
        return  result;
    }

    // 考虑基本类型
    public static Object base2 (Object o, String name, Object... params) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = o.getClass();
        Class[] args = new Class[params.length];
        //创建一个方法对象
        Method[] ms = c.getDeclaredMethods();
        for(Method m : ms) {
            if(m.getName().equals(name)) {
                args = m.getParameterTypes();
                break;
            }
        }
        //创建一个方法对象
        Method m = c.getDeclaredMethod(name, args);
        //一定要记得开放权限
        m.setAccessible(true);
        //调用创建好的方法对象
        Object result = m.invoke(o, params);
        return  result;
    }
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Demo3 d = new Demo3();
        base1(d, "test1");
        base1(d, "test2", 100);
        base1(d, "test3", "a", 100);
        //base1(d, "test4", 100);
        base2(d, "test4", 100);
    }
}
