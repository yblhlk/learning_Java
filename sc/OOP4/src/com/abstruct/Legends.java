package com.abstruct;

public abstract class Legends {
    private String name;
    private int hp;
    private int mp;

    public Legends() {}
    public Legends(String name, int hp, int mp)
    {
        this.name = name;
        this.hp = hp;
        this.mp = mp;

        System.out.println("玩家召唤了传奇人物：" + name + ", 初始血量：" + hp + "，初始蓝量：" + mp + "。");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "com.abstruct.Legends{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                ", mp=" + mp +
                '}';
    }
}
