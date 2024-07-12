package day9.System.EntityClass;

import java.io.Serializable;

public abstract class ServicePackage implements Serializable {
    protected double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract String showInfo();
}
