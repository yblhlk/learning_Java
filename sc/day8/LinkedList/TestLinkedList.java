package Code.LinkedList;

import java.util.LinkedList;

public class TestLinkedList {
    public static void main(String[] args) {
        LinkedList<Student> linked = new LinkedList<>();
        //1.尾插
        linked.add(new Student("wangwu", 18));
        linked.add(new Student("lisi", 18));
        linked.add(new Student("zhangsan", 18));
        linked.add(new Student("xionger", 18));
        linked.add(new Student("zhaoyi", 18));

        //2.遍历（linkedlist也支持用get()进行下标访问)
        for (int i = 0; i < linked.size(); i++) {
            System.out.print(linked.get(i) + " ");
        }
        System.out.println();

        //3. 头插头删\尾插尾删
        linked.removeFirst();
        linked.addFirst(new Student("ling", 0));
        linked.removeLast();
        linked.addLast(new Student("zhaoliu", 16));
        //遍历（linkedlist也支持用get()进行下标访问)
        for (int i = 0; i < linked.size(); i++) {
            System.out.print(linked.get(i) + " ");
        }
        System.out.println();

        //3.修改（set())
        linked.set(0, new Student("wangyalin", 22));
        //遍历（linkedlist也支持用get()进行下标访问)
        for (int i = 0; i < linked.size(); i++) {
            System.out.print(linked.get(i) + " ");
        }
        System.out.println();
    }
}
