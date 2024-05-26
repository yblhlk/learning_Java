package Code;

import java.util.LinkedList;

// 泛型类
public class TestGeneric<E> {
    private E date;
    private LinkedList<E> linkedList = new LinkedList<>();
    public static void main(String[] args) {
        TestGeneric<InnerClass> TG = new TestGeneric<>();
        TG.date = new InnerClass();
        TG.linkedList.add(new InnerClass());
        System.out.println(add(1));
    }

    private static class InnerClass{
        public InnerClass() {
        }
    }

    //泛型方法
    private static <T> T add(T a){
        return a;
    }
}
