package ManagmentSystem;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        TeacherMS tms = new TeacherMS();

        int choose = 0;
        Scanner sc = new Scanner(System.in);

        do {
            tms.menu();
            System.out.println(">请输入你的选择：");
            System.out.print(">");
            choose = sc.nextInt();
            switch(choose)
            {
                case 0:
                    break;
                case 1:
                    int select = 0;
                    do {
                        tms.tmenu();
                        System.out.println(">请输入你的选择：");
                        System.out.print(">");
                        select = sc.nextInt();
                        switch(select)
                        {
                            case 0:
                                break;
                            case 1:
                                tms.addSI(); break;
                            case 2:
                                tms.subSI(); break;
                            case 3:
                                tms.modSI(); break;
                            case 4:
                                tms.querySI(); break;
                            default:
                                System.out.println(">输入有误请重新输入！"); break;
                        }
                    } while(select > 0);
                    break;
                case 2:
                    System.out.println(">进入学生管理系统。");break;
                default:
                    System.out.println(">输入有误请重新输入！"); break;
            }

        }while(choose > 0);
    }
}
