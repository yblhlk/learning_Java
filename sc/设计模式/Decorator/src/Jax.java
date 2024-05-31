// 具体构件（ConcreteComponent）
public class Jax implements Hero {
    private int attack = 10;
    private int defence = 5;

    public Jax() {
    }

    public Jax(int attack, int defence) {
        this.attack = attack;
        this.defence = defence;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }


    @Override
    public String getDescription() {
        return "贾科斯的基础攻击力为" + getAttack() + "，基础防御力为" + getDefense() + " ";
    }
}
