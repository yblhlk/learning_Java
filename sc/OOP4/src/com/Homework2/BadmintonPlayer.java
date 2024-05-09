package com.Homework2;

public class BadmintonPlayer extends People implements Player{
    public BadmintonPlayer() {
    }

    public BadmintonPlayer(String name, int age) {
        super(name, age);
    }

    @Override
    public void eat() {
        System.out.println(getName() + "正在大口炫饭。");
    }

    @Override
    public void train() {
        System.out.println(getName() +"：教练我想学羽毛球。");
    }
}
