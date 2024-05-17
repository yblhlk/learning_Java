public abstract class OrderTemplate {
    public abstract void accept();  // 接单
    public abstract void check();   // 核验订单
    public void pay() { // 支付
        System.out.println("客户付款");
    }
    // 钩子方法：有些类中需要有些类中不需要，是可有可无的方法，需要的子类就重写该方法，子类重写后该方法才有内容，才会有效果
    public void send(){}     // 发货
    public void complete(){} // 完成订单
    // 模板方法：所有方法都会在模板方法中被调用
    public final void  templateFunction()
    {
        accept();  // 接单
        check();
        pay();
        send();
        complete();
    }

}

//模板方法模式:同一个业务流程是一样的，但是有多种实现，
//        而且每种子类的实现不一样，步骤的调用就会变得繁琐
//        这个时候呕以考虑模板方法模式，将步骤的调用放在父类中，
//        子类中实现不同的步骤，这样就可以减少代码的重复性
