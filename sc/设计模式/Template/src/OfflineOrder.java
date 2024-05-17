public class OfflineOrder extends OrderTemplate{
    @Override
    public void accept() {
        System.out.println("线下接单");
    }

    @Override
    public void check() {
        System.out.println("线下验单");
    }
}
