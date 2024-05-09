package com.HomeWork1;

public class Bird extends Animal{
    private String color = "黄毛";

    public Bird() {
    }

    public Bird(String name, int age) {
        super(name, age);
    }

    public Bird(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

    @Override
    public void info() {
        System.out.println("这是一只"+ color +"色，"+ getAge() + "岁的鳥的自白：" + "我是一只小小小小鸟，想要飞却怎么样也飞不高~");
    }
}
