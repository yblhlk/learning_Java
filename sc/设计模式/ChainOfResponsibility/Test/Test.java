package Test;

public class Test {
    public static void main(String[] args) {
        // 1.创建责任链（创建责任链上的节点后串起来）
        Approver teamleader = new TeamLeader();
        Approver departmentmaneger = new DepartmentManager();
        teamleader.setNext(departmentmaneger); //串起来

        // 2.使用责任链的头结点来处理业务
        System.out.println(teamleader.approve(new Excuse("zhangsan", "我是法外狂徒", 9999)));
        System.out.println("-----------------");
        System.out.println(teamleader.approve(new Excuse("lisi", "老婆生孩子", 7)));
        System.out.println("-----------------");
        System.out.println(teamleader.approve(new Excuse("wangwu", "世界这么大我想去看看", 3)));


    }
}
