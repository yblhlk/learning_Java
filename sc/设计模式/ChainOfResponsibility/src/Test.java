public class Test {
    public static void main(String[] args) {
        // 1.创建责任链的节点
        AbstractOrderHandler voh = new VipOrderHandler();
        AbstractOrderHandler loh = new LagerOrderHandler();
        AbstractOrderHandler noh = new NormalOrderHandler();
        // 2. 链接责任链节点
        voh.setNext_orderHandler(loh);
        loh.setNext_orderHandler(noh);
        // 3. 使用头节点处理业务
        Order o1 = new Order("vip", 100);
        Order o2 = new Order("normal", 100);
        Order o3 = new Order("normal", 2100);
        Order o4 = new Order("???", 1100);
        voh.orderHandler(o1);
        voh.orderHandler(o2);
        voh.orderHandler(o3);
        voh.orderHandler(o4);
    }
}
