public class VipOrderHandler extends AbstractOrderHandler{
    @Override
    public void orderHandler(Order o) {
        if(o.getLevel().equals("vip")){
            System.out.println("处理vip订单.打八折，需要付款：" + (o.getPay()*0.8));
        }else{
            next_orderHandler.orderHandler(o);
        }
    }
}
