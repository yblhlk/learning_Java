package com.abstruct;

public class Jakos extends Legends implements Skill {
    public Jakos() {
        super("com.abstruct.Jakos", 600, 300);
    }

    public void talent()
    {
        System.out.println("无情连打（被动）");
        System.out.println("贾克斯的每次普通攻击都会提升攻击速度，最多可叠加8层。)");
    }
    public void Q()
    {
        System.out.println("跳斩");
        System.out.println("贾克斯跳向目标，若目标是敌人，将造成物理伤害。");
    }
    public void W()
    {
        System.out.println("蓄力一击");
        System.out.println("贾克斯的下次攻击或Q技能会造成额外的魔法伤害，可以重置普攻并增加攻击距离。");
    }
    public void E()
    {
        System.out.println("反击风暴");
        System.out.println("贾克斯进入防御姿态，躲避所有普通攻击，同时每次躲避可以提升伤害，E技能结束时会眩晕附近敌人并造成魔法伤害。");
    }
    public void R()
    {
        System.out.println("宗师之威");
        System.out.println("被动效果是每两次普通攻击造成额外魔法伤害；主动激活时，提升贾克斯的护甲和魔法抗性，增加其在战斗中的生存能力。");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
