package day5.Class;

import java.util.Date;

public class Person extends Animal{
    // 反射得到4个Filed类，类中有几个属性（包括继承的属性）就会有几个Field类
    private String name;
    int age;
    protected double money;
    public Date time;

    public void study(){
        System.out.println("开始学习");
    }
    private void play(String str){
        System.out.println("玩" + str);
    }

    private Person() {
    }

    public Person(String name, int age, double money) {
        this.name = name;
        this.age = age;
        this.money = money;
    }

    public Person(String name, int age, double money, Date time) {
        this.name = name;
        this.age = age;
        this.money = money;
        this.time = time;
    }

}
