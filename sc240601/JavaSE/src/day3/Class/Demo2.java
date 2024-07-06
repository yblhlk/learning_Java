package day3.Class;

// 深克隆和浅克隆
public class Demo2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        A a = new A();
        a.b = new B();
        A a2= (A)a.clone();

        System.out.println(a); //@1b6d3586
        System.out.println(a2); //@4554617c

        a.id = 100;
        a.name = "lisi";

        System.out.println(a.id + " " + a.name + " " + a.b + " " + a.b.id + " " + a.b.name);
        System.out.println(a2.id + " " + a2.name + " " + a2.b + " " + a2.b.id + " " + a2.b.name);


        a.b.id = 100;
        a.b.name = "wanngwu";
        System.out.println(a.id + " " + a.name + " " + a.b + " " + a.b.id + " " + a.b.name);
        System.out.println(a2.id + " " + a2.name + " " + a2.b + " " + a2.b.id + " " + a2.b.name);
    }
}

// 浅拷贝
class A implements Cloneable {
    int id;
    String name;
    B b;

//深拷贝
    protected  Object clone() throws  CloneNotSupportedException {
        A a = (A) super.clone();
        a.b = (B) b.clone();
        return a;
    }
}

class B implements Cloneable {
    int id;
    String name;

    protected Object clone() throws CloneNotSupportedException{
        return  super.clone();
    }
}
