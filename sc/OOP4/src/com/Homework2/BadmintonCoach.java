package com.Homework2;

public class BadmintonCoach extends People implements Coach{
    public BadmintonCoach() {
    }

    public BadmintonCoach(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println(getName() + "：中年人细嚼慢咽");
    }

    @Override
    public void teach() {
        System.out.println(getName() + "：曾经我也是羽毛球好手，直到我的膝盖中了一箭……");
    }
}
