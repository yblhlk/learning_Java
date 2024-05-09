package learn;

public class ChinessBook extends Book {
    public ChinessBook(String press, double price) {
       super(press, price);
    }
    @Override
    public void introduce() {
        System.out.println("这是一本语文书。");
    }

    public void introduce(int p) {
        System.out.println("这是一本语文书, 价格是" + p);
    }
}
