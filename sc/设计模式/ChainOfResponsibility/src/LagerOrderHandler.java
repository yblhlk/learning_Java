public class LagerOrderHandler extends AbstractOrderHandler{
    public void orderHandler(Order o) {
        if(o.getPay() > 2000.0){
            System.out.println("处理大订单.打九折：" + (o.getPay()*0.9));
        }else{
            next_orderHandler.orderHandler(o);
        }
    }
}
