//具体装饰者（ConcreteDecorator） - 短剑
public class ShortSword extends Decorator {
    /**
     * “@param”标签来详细说明每个参数的作用、类型以及可能的取值范围等。
     * @param hero 要穿戴该装备的英雄
     */
    public ShortSword(Hero hero) {
        super(hero);
    }

    @Override
    public int getAttack() {
        // 短剑攻击力+英雄攻击力
        return super.getAttack() + 10;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ".穿戴了短剑，攻击力+10,当前攻击力为：" + this.getAttack();
    }
}