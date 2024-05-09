package com.Interface;

public interface A {
    void a();

    static void b(){

    }

    default double c(int a) {
        System.out.println("interface A");
        return 0.1;
    }
}
