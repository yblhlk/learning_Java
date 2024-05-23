public class NormalOrderHandler extends AbstractOrderHandler{
    public void orderHandler(Order o) {
        if(o.getLevel().equals("normal")){
            System.out.println("处理普通订单，不打折：" + o.getPay());
        }else{
            System.out.println("无法处理的订单");
        }
    }
}
