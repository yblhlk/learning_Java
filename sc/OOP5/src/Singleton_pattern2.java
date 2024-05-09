//懒汉式单例：
////        1. 在类内创建一个该类类型的静态成员
////        2. 私有化构造器
////        3. 提供一个静态的公有方法，让外部能通过该类来获取该静态成员,且在创建第一次调用时创建对象（双重检查锁）
public class Singleton_pattern2 {
    private Singleton_pattern2(){

    }
    //        2. 在类内创建一个类的静态成员
    private static  Singleton_pattern2 sp;

    public static Singleton_pattern2 getSp() {
        if(sp == null){
            synchronized (Singleton_pattern2.class){
                if(sp == null){
                    sp= new Singleton_pattern2();
                }
            }
        }
        return sp;
    }
}
