package day9.System.EntityClass;

import java.io.Serializable;
import java.text.DecimalFormat;

public class NetPackage extends ServicePackage implements Serializable {
    private int flow;

    private static NetPackage n = new NetPackage(3, 68.0);

    private NetPackage(int flow, double p) {
        this.flow = flow;
        price = p;
    }

    public static NetPackage get() {
        return n;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    @Override
    public String showInfo() {
        DecimalFormat format= new DecimalFormat("#.0");//定义格式化器
        return "网虫套餐:{" +
                "上网流量=" + flow + "GB" +
                ", 资费=" + format.format(price) + "元" +
                '}';
    }
}
