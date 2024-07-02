package day6.Class;

public class Demo4 {
    public static void main(String[] args) {
        //获取系统类加载器（应用程序类加载器）
        ClassLoader cl1 = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器：" + cl1);
        // 系统类加载器父加载器就是平台类加载器（扩展类加载器）
        ClassLoader cl2 = cl1.getParent();
        System.out.println("平台类加载器：" + cl2);
        // 平台类加载器父加载器就是启动类加载器
        ClassLoader cl3 = cl2.getParent();
        System.out.println("启动类加载器：" + cl3);
    }
}
