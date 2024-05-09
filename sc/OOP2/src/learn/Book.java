package learn;

public class Book {
    private String press;
    private double price;

    public Book() {
    }

    public Book(String press, double price) {
        this.press = press;
        this.price = price;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void introduce() {
        System.out.println("出版社：" + press + ", 价格：" + price);
    }
}
