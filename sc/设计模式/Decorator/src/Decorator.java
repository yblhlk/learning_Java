// 装饰者类，要实现Hero接口，因为不需要实例化所以我们定义为抽象类
// 抽象装饰者（Decorator）
public abstract class Decorator implements Hero{
    protected Hero hero;
    //在Java中，抽象类是可以声明并定义构造方法的。由于抽象类不能被直接实例化
    public Decorator(Hero hero) {
        this.hero = hero;
    }

    public int getAttack() {
        return hero.getAttack();
    }

    @Override
    public int getDefense() {
        return hero.getDefense();
    }

    @Override
    public String getDescription() {
        return hero.getDescription();
    }
}
