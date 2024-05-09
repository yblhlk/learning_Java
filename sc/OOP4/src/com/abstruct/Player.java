package com.abstruct;

public class Player {
    public static void main(String[] args) {
        Skill jakos = new Jakos();
        Skill katerina = new Katerina();
        System.out.println(jakos + "释放技能：");
        jakos.Q();
        jakos.W();
        jakos.E();
        jakos.R();

        System.out.println();

        System.out.println(katerina + "释放技能：");
        katerina.Q();
        katerina.W();
        katerina.E();
        katerina.R();

    }
}
