package com.abstruct;

public class Katerina extends Legends  implements Skill{
    public Katerina()
    {
        super("com.abstruct.Katerina", 400, 500);
        super.toString();
    }

    public void talent()
    {
        System.out.println("贪婪（被动）");
        System.out.println("卡特琳娜每杀死或助攻击杀一个英雄，额外获得50黄金，并减少所有技能冷却时间20秒。");
    }
    public void Q()
    {
        System.out.println("弹射之刃");
        System.out.println("卡特琳娜投掷匕首，在敌人中间弹射造成魔法伤害。");
    }
    public void W()
    {
        System.out.println("杀手本能");
        System.out.println("被动：卡特琳娜攻击同一个目标时，攻击逐渐提升；主动：赋予卡特琳娜的下个技能攻击特效。");
    }
    public void E()
    {
        System.out.println("瞬步");
        System.out.println("卡特琳娜瞬移到目标位置，如果是敌人还会造成伤害。");
    }
    public void R()
    {
        System.out.println("死亡莲华");
        System.out.println("卡特琳娜进入忘我境界，高速向周围敌人投掷匕首。");
    }
    public String toString() {
        return super.toString();
    }
}
