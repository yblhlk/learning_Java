public abstract class AbstractOrderHandler {
    protected AbstractOrderHandler next_orderHandler;
    public void setNext_orderHandler(AbstractOrderHandler next_orderHandler){
        this.next_orderHandler = next_orderHandler;
    }

    public abstract void orderHandler(Order o);
}
