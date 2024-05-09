package com.HomeWork1;

public class Fish extends Animal{

    private double weight;

    public Fish() {
    }

    public Fish(double weight) {
        this.weight = weight;
    }

    public Fish(String name, int age, double weight) {
        super(name, age);
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void info() {
        System.out.println("这是一条重"+ weight +"斤，"+ getAge() + "岁的🐟的自白：" + "我化作人鱼，只有七秒钟的记忆~");
    }
}
