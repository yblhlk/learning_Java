public class Test {
    public static void main(String[] args) {
        //外部能通过类来获取单例模式的唯一对象
        Singleton_pattern2 sp1 = Singleton_pattern2.getSp();
        Singleton_pattern2 sp2 = Singleton_pattern2.getSp();
        System.out.println(sp1 == sp2);
    }
}
