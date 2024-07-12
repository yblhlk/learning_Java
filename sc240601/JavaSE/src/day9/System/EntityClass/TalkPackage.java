package day9.System.EntityClass;

import java.io.Serializable;
import java.text.DecimalFormat;

// 枚举类无法继承，但它们可以实现接口。
public class TalkPackage extends ServicePackage implements Serializable {
    private int talkTime;
    private int smsCount;

    private static TalkPackage t = new TalkPackage(500, 30, 58.0);

    private TalkPackage(int talkTime, int smsCount, double p) {
        this.talkTime = talkTime;
        this.smsCount = smsCount;
        price = p;
    }

    public static TalkPackage get() {
        return t;
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


    @Override
    public String showInfo() {
        DecimalFormat format= new DecimalFormat("#.0");//定义格式化器
        return "话痨套餐:{" +
                "通话时长=" + talkTime + "分钟" +
                ", 短信数量=" + smsCount + "条" +
                ", 资费=" + format.format(price) + "元" +
                '}';
    }
}
