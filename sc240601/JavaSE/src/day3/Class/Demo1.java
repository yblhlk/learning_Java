package day3.Class;

// 测试hashcode
public class Demo1 {
    public static void main(String[] args) {
        // 1. Object类的hashcode
        Demo1 d1 = new Demo1();
        System.out.println("d1：" + d1.hashCode());
        Demo1 d2 = new Demo1();
        System.out.println("d2：" + d2.hashCode());
        Demo1 d3 = d1;
        System.out.println("d3：" + d3.hashCode());

        // 2. Integer类的hashcode
        Integer i = new Integer(10);
        Short ii = new Short((short) 10);
        Double d = new Double(100.0);
        Double dd = new Double(1.2);
        Float f = new Float(15.5f);
        System.out.println("i：" + i.hashCode());
        System.out.println("ii：" + ii.hashCode());
        System.out.println("d：" + d.hashCode());
        System.out.println("dd：" + dd.hashCode());
        System.out.println("f：" + f.hashCode());

        // 3. String类的hashcode
        String s1 = "java";
        String s2 = new String("java");
        System.out.println("s1：" + s1.hashCode());
        System.out.println("s2：" + s2.hashCode());
    }
}
