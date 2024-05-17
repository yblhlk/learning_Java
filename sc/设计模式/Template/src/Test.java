public class Test {
    public static void main(String[] args) {
        OrderTemplate o1 = new OnlineOrder();
        OrderTemplate o2 = new OfflineOrder();
        o1.templateFunction();
        System.out.println();
        o2.templateFunction();
    }
}
