package Code.Clone;

public class TestDeepClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        DeepClone.InnerClass ic1 = new DeepClone().new InnerClass("2121803", 1);
        DeepClone.InnerClass ic2 = new DeepClone().new InnerClass("2121801", 3);
        DeepClone a = new DeepClone("wang", 10, ic1);
        DeepClone b = a.clone();//使用clone()方法要记得抛异常
        DeepClone c = a;
        a.ic.setId(111);
        c.setAge(18);
        System.out.println("a : " + a);
        System.out.println("b : " + b);
        System.out.println("c : " + c);
        System.out.println("a == b : " + (a == b));
        System.out.println("a == c : " + (a == c));
    }
}
