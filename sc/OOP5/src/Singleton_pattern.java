//饿汉式单例：
//        1. 私有化构造器
//        2. 在类内创建一个类的静态对象
//        3. 提供一个静态的公有方法，让外部能通过类来获取该静态对象
public class Singleton_pattern {
    private Singleton_pattern(){

    }

    private static Singleton_pattern sp = new Singleton_pattern();
    public static Singleton_pattern getSp()
    {
        return sp;
    }
}
