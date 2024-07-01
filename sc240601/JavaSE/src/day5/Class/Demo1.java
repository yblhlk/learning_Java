package day5.Class;

import java.lang.reflect.Field;
import java.util.Date;

//测试反射 ： 测试Person类
public class Demo1 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
    //1.获取类对象
        //a. 通过 类名.class()
        Class c1 = Person.class;
        //b. 通过 Class.forName(全类名)（要抛异常）
        Class c2 = Class.forName("day5.Class.Person");
        //c. 通过 对象.getClass()
        Person p = new Person("王五", 19, 10);
        Class c3 = p.getClass();

    //2.通过类对象来获取类中的资源
        System.out.println("++++++++++++++获取变量名Name++++++++++++++++");
        String name = c1.getName();
        System.out.println("全类名:" + name);
        String simpleName = c1.getSimpleName(); //只获取类名
        System.out.println("类名:" + simpleName);
        String superName = c1.getSuperclass().getName();
        System.out.println("父类的全类名:" + superName);

        System.out.println("++++++++++++++获取属性Field++++++++++++++++");
        //导入java lang包的 reflect
        // a. 获取本类的属性（不包括父类的属性）
        Field[] fs = c1.getDeclaredFields();
        for (Field f : fs) {
            String fieldName = f.getName();
            Class type = f.getType();
            System.out.println("属性名：" + fieldName + ", 类型：" + type);
        }
        System.out.println();

        // b. 获取本类的公开属性（包括父类的公开属性）
        Field[] fs2 = c1.getFields();
        for (Field f : fs2) {
            String fieldName = f.getName();
            Class type = f.getType();
            System.out.println("属性名：" + fieldName + ", 类型：" + type);
        }

        // c. 获取指定的一个属性
        Field f1 = c1.getDeclaredField("name"); //根据属性名来获取属性类
        Field f2 = c1.getDeclaredField("time"); //根据属性名来获取属性类
        // 设置某个对象的属性值和获取某个对象的属性值
        // f2.set(对象, 属性值) ： 设置某个对象的属性值
        // f2.get(对象) ： 获取某个对象的属性值
        // 没有走访问器和修改器，不然私有属性就不需要开权限了
        // * 公有成员
        f2.set(p, new Date()); //等价于p.time = new Date(), 是它的底层
        Object v = f2.get(p);  //等价于 p.time, 也是这句话的底层
        System.out.println("日期属性：" + v);
        // * 私有成员 (必须先开启权限才能访问）
        f1.setAccessible(true); //开启私有访问
        f1.set(p, "wang"); //等价于p.time = new Date(), 是它的底层
        Object n = f1.get(p);  //等价于 p.time, 也是这句话的底层
        System.out.println("姓名属性：" + n);
//        c1.getDeclaredMethod(); //只获取类自己声明的某个方法
//        c1.getDeclaredMethods(); //获取类自己声明的所有方法（不包括从父类继承的方法）
//        c1.getMethod();
//        c1.getMethods(); //获取类中所有方法（包括从父类继承的方法）

        //总结：
        //1.带后缀s : 获取一个数组，数组中是要获取的资源
        //2.不带后缀s : 获取指定资源，要传入资源名称来指定
        //3.带中缀Declared :  获取本类的所有资源，可以获取私有资源（但不获取父类的资源）
        //4.不带中缀Declared : 只能获取本类和父类中的公有资源（只能获取公有资源）
        //5.私有成员，必须先开启权限才能访问
        //f1.setAccessible(true); //开启私有访问
    }
}
