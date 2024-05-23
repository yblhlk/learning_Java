public class Order {
    private String level;
    private double pay;

    public Order() {
    }

    public Order(String level) {
        this.level = level;
    }

    public Order(String level, double pay) {
        this.level = level;
        this.pay = pay;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }
}
