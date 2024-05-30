package Test;

import java.util.Scanner;

public class TeamLeader extends Approver{
    @Override
    public boolean approve(Excuse excuse) {
        if (excuse.getLeave_days() <= 3) {
            Scanner sc = new Scanner(System.in);
            System.out.println("小组长是否批准请假?(yes or no)");
            String choose = sc.next();
            while (!choose.equals("yes") && !choose.equals("no")) {
                System.out.println("输入有误，请重新输入。");
                System.out.println("小组长是否批准请假?(yes or no)");
                choose = sc.next();
            }
            if (choose.equals("yes"))
                return true;
            else
                return false;
        } else {
            return next.approve(excuse);
        }

    }
}
