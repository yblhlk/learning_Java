package com.Homework2;

public class PingPongCoach  extends People implements Coach, LearnEnglish{
    public PingPongCoach() {
    }

    public PingPongCoach(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println(getName() + "：中年人细嚼慢咽");
    }

    @Override
    public void teach() {
        System.out.println(getName() + "：曾经我也是乒乓球好手，直到我的膝盖中了一箭……");
    }
}
