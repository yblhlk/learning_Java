package learn;

public class P {
    public int c;
    public static int sc;

    static class A {
        public  int a;
        public static int b = 1;
        public void f() {
            System.out.println(sc);
            P p = new P();
            System.out.println(p.c);
        }
    }

    // 静态内部类只能直接访问其外部类的静态成员。
    // 它们无法访问外部类的实例成员，除非通过外部类的一个实例来访问。

}
