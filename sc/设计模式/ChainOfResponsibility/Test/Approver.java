package Test;

public abstract class Approver {
    //1. 下一个节点的引用
    protected Approver next;

    public void setNext(Approver next) {
        this.next = next;
    }

    //2. 抽象方法
    public abstract boolean approve(Excuse excuse);
}
