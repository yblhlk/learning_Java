/**
 * 什么是装饰者模式？
 *  装饰着模式：动态的将新功能添加到对象上。
 *  做一个游戏，有英雄，英雄有攻击力，防御力，血量，等级。
 *  可以给英雄穿戴装备，装备有攻击力，防御力，血量。
 */
public class Test {
    public static void main(String[] args) {
        Hero hero = new Jax(80, 45);
        hero = new ShortSword(hero);
        hero = new ShortSword(hero);
        hero = new Shield(hero);
        System.out.println(hero.getDescription());
    }
}