package com.Interface;

public interface B {
    void a();

    static void b(){

    }

    default void c() {
        System.out.println("interface B");
    }
}
