// 具体装饰者（ConcreteDecorator） - 盾牌
public class Shield extends Decorator {
    public Shield(Hero hero) {
        super(hero);
    }

    @Override
    public int getDefense() {
        // 英雄的防御力+20
        return super.getDefense() + 20;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ".穿戴了盾牌，防御力 + 20"  + ",当前防御力为:" + getDefense();
    }
}