package day9.System.EntityClass;

import java.io.Serializable;
import java.text.DecimalFormat;

// 消费记录 (每在一个场景使用一次就要记录一次消费记录）
public class ConsumInfo implements Serializable {
    private int talkTime;
    private int smsCount;
    private int flow;
    private double price;

    public ConsumInfo(String type, int data, double price) {
        if (type.equals("流量")) {
            this.talkTime = 0;
            this.smsCount = 0;
            this.flow = data;
            this.price = price;
        }
        if (type.equals("通话")) {
            this.talkTime = data;
            this.smsCount = 0;
            this.flow = 0;
            this.price = price;
        }
        if (type.equals("短信")) {
            this.talkTime = 0;
            this.smsCount = data;
            this.flow = 0;
            this.price = price;
        }
    }

    public int getTalkTime() {
        return talkTime;
    }

    public void setTalkTime(int talkTime) {
        this.talkTime = talkTime;
    }

    public int getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(int smsCount) {
        this.smsCount = smsCount;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        DecimalFormat format= new DecimalFormat("#.0");//定义格式化器
        return "bill{" +
                "通话时长=" + talkTime +
                "分钟, 短信数量=" + smsCount +
                "条, 流量消耗=" + flow +
                "MB, 消费金额=" + format.format(price) +
                "元}";
    }
}
