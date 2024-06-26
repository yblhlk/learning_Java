package day2.Class;

import jdk.nashorn.api.scripting.AbstractJSObject;

import java.util.Arrays;

public class Demo1 {
    public static void main(String[] args) {
        // 语法规则2 ： 参数类型可以省略，且参数类型必须和函数式接口中的方法的参数列表相同。
        inter1 i1 = (ib, c) -> {
        };
        //inter1 i11 = (double b, int c)->{};
        inter1 i111 = (int b, double c) -> {
        };

        // 语法规则5：还能借助静态方法来实现函数式接口
        inter5 i5 = Arrays::sort;
        int[] num = {1, 10, 4, 12, 4123, 412};
        i5.test(num);
        System.out.println(Arrays.toString(num));
    }
}

@FunctionalInterface
interface inter1 {
    public void test(int a, double b);
}

interface inter2 {
    public void test(int[] a);
}

interface inter3 {
    public int test(int[] a);
}

interface inter5 {
    public void test(int[] a);
}
