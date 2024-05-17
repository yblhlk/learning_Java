public class OnlineOrder extends OrderTemplate{
    @Override
    public void accept() {
        System.out.println("线上接单");
    }

    @Override
    public void check() {
        System.out.println("线上验单");
    }

    @Override
    public void send() {
        System.out.println("卖家发货");
    }

    @Override
    public void complete() {
        System.out.println("完成订单");
    }
}
