package Test;

import java.util.Scanner;

public class DepartmentManager extends Approver {
    public boolean approve(Excuse excuse) {
        if(excuse.getLeave_days() > 3 && excuse.getLeave_days() <= 7){
            Scanner sc = new Scanner(System.in);
            System.out.println("部门经理是否批准请假?(yes or no)");
            String choose = sc.next();
            while(!choose.equals("yes") && !choose.equals("no")){
                System.out.println("输入有误，请重新输入。");
                System.out.println("部门经理是否批准请假?(yes or no)");
                choose = sc.next();
            }
            if(choose.equals("yes"))
                return true;
            else
                return false;
        }else{
            System.out.println("部门经理：请假时间太长，不同意！");
            return false;
        }
    }
}
