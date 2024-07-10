package day7.Work;

//1. 饿汉式单例
public class SingleInstance {
    // 1. 创建一个私有的本类对象
    // 要静态化，才能被静态方法直接使用，因为获取方法必须是静态的所以这个对象也必须是静态的。
    private static final SingleInstance object = new SingleInstance();

    // 2. 私有化构造器
    private SingleInstance() {}

    // 3. 提供一个静态方法供外界访问对象（静态方法，才能不通过对象来使用）
    public static SingleInstance get() {
        return object;
    }
}

//2. 懒汉式单例
class SingleInstance2{
    // 1. 创建一个本类对象的引用
    // 保证数据在多线程下的可见性
    private static volatile SingleInstance2 s;

    // 2. 私有化构造器
    private SingleInstance2() {}

    // 3. 提供接口供外界获取单例对象
    // 加锁保证多线程情况下数据不一致问题
    public static synchronized SingleInstance2 get() {
        if(s == null)
            s = new SingleInstance2();
        return s;
    }
}

//3. 双重检索的单例
// 单纯的饿汉在多线程情况下效率比较低，所以使用双重检索来对饿汉进行优化，好处是不需要每次都申请锁。
// 还要加上volatile保证对象的可见性，不然可能出现线程空间没有同步到主内存空间的情况。
class SingleInstance3 {
    // 1. 创建一个本类对象的引用
    private static volatile SingleInstance3 s;
    // 2. 私有化构造器
    private SingleInstance3 () {}
    // 3. 提供一个接口供外界获取对象
    public static SingleInstance3 get() {
        if( s == null) {
            synchronized (SingleInstance3.class) {
                if( s == null) {
                    s = new SingleInstance3();
                }
            }
        }
        return s;
    }
}

//4. 静态内部类实现单例
// 类只加载一次，所以其天然是线程安全的。
// 是使用静态内部类对饿汉式单例的一种优化。（在安全性方面）
class SingleInstance4 {
    //1. 私有化构造函数
    private SingleInstance4() {}
    // 2. 在静态内部类中创建对象
    private static class member{
        private static SingleInstance4 s = new SingleInstance4();
    }
    // 3. 提供一个方法供外界获取单例对象
    public static SingleInstance4 get() {
        return member.s;
    }
}

//5. 枚举式的单例
// 为了防止通过反射来突破限制
enum M {
    // 枚举类的构造方法只能在类中调用，用于创建枚举常量。
    // 所以我们只在枚举类中创建一个枚举常量就完成了单例的设计。
    s;
    private M() {}
    public static M get() {
        return s;
    }
}