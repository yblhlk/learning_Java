package day9.System.EntityClass;

import java.io.Serializable;
import java.text.DecimalFormat;

public class SuperPackage extends ServicePackage implements Serializable {
    private int talkTime;
    private int flow;
    private int smsCount;

    private static ServicePackage s = new SuperPackage(20, 1, 50, 78.0);

    private SuperPackage(int talkTime, int smsCount, int flow, double p) {
        this.talkTime = talkTime;
        this.smsCount = smsCount;
        this.flow = flow;
        price = p;
    }
    public static ServicePackage get() {
        return s;
    }

    public int getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(int talkTime) {
        this.talkTime = talkTime;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public int getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(int smsCount) {
        this.smsCount = smsCount;
    }

    @Override
    public String showInfo() {
        DecimalFormat format= new DecimalFormat("#.0");//定义格式化器
        return "超人套餐:{" +
                "通话时长=" + talkTime + "分钟" +
                "，上网流量=" + flow + "GB" +
                ", 短信数量=" + smsCount + "条" +
                ", 资费=" + format.format(price) + "元" +
                '}';
    }
}
