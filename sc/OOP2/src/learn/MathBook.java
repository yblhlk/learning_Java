package learn;

public class MathBook extends Book {
    public MathBook(String press, double price) {
        super(press, price); //super就是父类的构造方法
    }

    public void f(double a, int b) {
        System.out.println("a");
    }
    public void f(int a, double b) {
        System.out.println("b");
    }
}
