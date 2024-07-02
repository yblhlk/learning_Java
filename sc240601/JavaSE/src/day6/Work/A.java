package day6.Work;

import java.lang.reflect.Field;

import static day3.Class.Demo7.a;

//a.通过反射定义一个方法可以实现修改任意对象的属性值
//    通过反射定义一个方法可以实现获取任意对象的属性值
//    如：setProperty(对象，属性名,新的属性值)    设置
//    getProperty(对象，属性名)   获取
public class A {
    public String name;
    private int age;

    public static void setProperty(Object o, String memberName, Object newValue) throws IllegalAccessException, NoSuchFieldException {
        // 1.获取类对象 （获取类对象的三种方式）
        Class c = o.getClass();
        // 2. 获取类的属性对象数组
        Field[] fields = c.getDeclaredFields(); //获取当前类的所有属性（包括私有的）
        // 3. 比较属性名，确定要修改的对象。
        for (Field f : fields) {
            // 4.开启权限以访问私有成员
            f.setAccessible(true);
            if (f.getName().equals(memberName)) {
                // 5.通过set()方法来修改属性对象对应的属性
                // 属性对象.set(要修改属性的对象，新值）
                f.set(o, newValue);
            }
        }
        // 2.获取要修改的属性对象
//        Field f = c.getDeclaredField(memberName);
//        // 3.开启访问权限以访问私有对象
//        f.setAccessible(true);
//        // 4,通过set()来修改属性对象对应的 具体对象的属性
//        f.set(o, newValue);
    }
    public static Object getProperty(Object o, String memberName) throws NoSuchFieldException, IllegalAccessException {
        // 1.获取类对象
        Class c = o.getClass();
        // 2.通过类对象获取类中的属性对象(要指定名称）
        Field f = c.getDeclaredField(memberName);
        // 3.获取属性对象的值（要指定是哪个对象的属性）并返回
        return f.get(o);
    }
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        A a = new A();
        int age1 = (int)getProperty(a, "age");
        System.out.println(age1);
        setProperty(a, "age", 18);
        int age2 = (int)getProperty(a, "age");
        System.out.println(age2);
    }
}

// 通过反射来修改属性，没有走类中的修改器，不然通过反射来修改私有成员的值，就不需要开权限了

