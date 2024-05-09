package learn;
import static learn.Dog.*;


public class TestCat {
    public static void main(String[] args) {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        Cat cat3 = new Cat();

//        //T1
//        cat1.color = "yellow";
//        cat2.color = "black";
//        cat3.color = "write";
//
//        if(cat1.color == cat2.color)
//            System.out.println("数据不安全。");
//        else
//            System.out.println("数据安全");

        // T2
        System.out.println(cat1.color);
        System.out.println(cat2.color);
        System.out.println(Cat.color);

        //T3
        Cat.eat();    // 静态方法可以通过类调用
        cat1.eat();
        cat1.shout(); // 实例方法不能通过类调用

        //T4
        //T5
        System.out.println(dogshout);
        dogeat();

        //T6
        P.A a = new P.A(); //创建静态内部类的对象不需要先创建外部类的实例。
    }
}
