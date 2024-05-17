public class Proxy {
    private ManagementLayer ml;
    public Proxy(ManagementLayer m)
    {
        ml = m;
    }
    public void proxyMeeting()
    {
        System.out.println("通知开会"); //增强（增强逻辑）
        ml.meeting();
        System.out.println("记录会议");
    }
    public void proxyPayBonuses()
    {
        System.out.println("通知发奖金"); //增强（增强逻辑）
        ml.payBonuses(); //代理（代理逻辑）
        System.out.println("记录会议");
    }
}

//增强（增强逻辑）：在代理类中增加被代理类中没有的语句

//代理模式： 增强逻辑，业务解耦，增强可维护性。