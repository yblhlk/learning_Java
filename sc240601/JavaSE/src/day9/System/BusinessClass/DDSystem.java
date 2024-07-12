package day9.System.BusinessClass;

// 系统主体，一级菜单
public class DDSystem {
    public static void menu1() {
        System.out.println("\033[32m _________________________________________________\033[0m");
        System.out.println("\033[32m|               立即登录，探索更多精彩!             |\033[0m");
        System.out.println("\033[32m|                   1. 用户注册                    |\033[0m");
        System.out.println("\033[32m|                   2. 用户登录                    |\033[0m");
        System.out.println("\033[32m|                   3. 资费说明                    |\033[0m");
        System.out.println("\033[32m|                   4. 退出系统                    |\033[0m");
        System.out.println("\033[32m|_________________________________________________|\033[0m");
    }

    public static void main(String[] args){
        System.out.println("\033[33m欢迎使用兜兜5G移动业务厅！\033[0m");
        int choose = 4;
        InfoPool.deserialize(); // 加载用户数据（反序列化）
        System.out.println("用户信息加载成功。");
        Util.deleteLoginCache(); // 清除登录缓存。(防止上次异常退出）
        System.out.println("登录缓存清除成功！");

        do {
            menu1();
            System.out.print("\033[32m请选择> \033[0m");
            choose = Util.sc.nextInt();
            switch(choose) {
                case 1 :
                    System.out.println("\033[34m _______________正在进入用户注册界面_______________\033[0m");
                    UserRegisterSystem.userRegister();
                    break;
                case 2:
                    System.out.println("\033[34m _______________正在进入登录界面_______________\033[0m");
                    UserLoginSystem.userLogin();
                    UserSpaceSystem.userSpace(); //登录成功有用户数据缓存就进入二级菜单。
                    break;
                case 3:
                    System.out.println("\033[34m正在进入资费说明界面……\033[0m");
                    Util.costDescription();
                    break;
                case 4:
                    System.out.println("\033[33m感谢您的使用!\033[0m");
                    InfoPool.serialize(); // 保存用户数据（序列化）
                    System.out.println("用户信息保存成功");
                    Util.deleteLoginCache(); // 清除登录缓存。
                    System.out.println("登录缓存清除成功！");
                    return;
                default:
                    System.out.println("\033[31m输入有误，请重新选择！\033[0m");
            }
        } while (choose != 4);
    }
}

// do-while + switch框架 = ƪ(˘⌣˘)ʃ优雅

