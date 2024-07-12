package day9.System.EntityClass;

import java.io.Serializable;

public class MobileCard implements Serializable {
    private String cardNumber;
    private String userName;
    private String passWord;
    private ServicePackage serPackage; //（套餐类型：和套餐类对应）
    private double consumAmount; // （已消费金额）
    private double money;// （余额）
    private int realTalkTime; // （剩余通话时间）
    private int realSMSCount;//（剩余短信数量）
    private int realFlow; //（剩余流量）

    public MobileCard() {
    }

    public MobileCard(String cardNumber, String userName, String passWord, ServicePackage serPackage, double consumAmount, double money, int realTalkTime, int realSMSCount, int realFlow) {
        this.cardNumber = cardNumber;
        this.userName = userName;
        this.passWord = passWord;
        this.serPackage = serPackage;
        this.consumAmount = consumAmount;
        this.money = money;
        this.realTalkTime = realTalkTime;
        this.realSMSCount = realSMSCount;
        this.realFlow = realFlow;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public ServicePackage getSerPackage() {
        return serPackage;
    }

    public void setSerPackage(ServicePackage serPackage) {
        this.serPackage = serPackage;
    }

    public double getConsumAmount() {
        return consumAmount;
    }

    public void setConsumAmount(double consumAmount) {
        this.consumAmount = consumAmount;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getRealTalkTime() {
        return realTalkTime;
    }

    public void setRealTalkTime(int realTalkTime) {
        this.realTalkTime = realTalkTime;
    }

    public int getRealSMSCount() {
        return realSMSCount;
    }

    public void setRealSMSCount(int realSMSCount) {
        this.realSMSCount = realSMSCount;
    }

    public int getRealFlow() {
        return realFlow;
    }

    public void setRealFlow(int realFlow) {
        this.realFlow = realFlow;
    }

    public String showMeg() {
        return "当前电话卡信息：{" +
                "卡号='" + cardNumber + '\'' +
                ", 用户名='" + userName + '\'' +
                ", 套餐类型=" + serPackage.showInfo() + //如何判断类型？靠单例？使用Instanceof
                ", 已消费金额=" + consumAmount +
                ", 余额=" + money +
                ", 剩余通话时间=" + realTalkTime +
                "分钟, 剩余短信数量=" + realSMSCount +
                "条, 剩余流量=" + realFlow +
                "MB}";
    }
}
