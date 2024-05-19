import java.util.Scanner;

public class Login {
    public void login(Users users){
        // 1.用户输入用户名
        System.out.print(">>>请输入用户名：");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();

        // 2.在集合中匹配用户名
        int index = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.getuser(i).getUsername().equals(username)) {
                index = i;
                break;
            }else{
                System.out.println(">>>未找到该用户，请先进行注册。");
                return;
            }
        }

        // 3.输入密码
        int passwd_wrongconst = 0;
        String passwd = "";
        while(passwd_wrongconst< 3){
            System.out.print(">>>请输入密码：");
            passwd += sc.next();
            if(passwd.equals(users.getuser(index).getPasswd()))
                break;
            else{
                passwd_wrongconst++;
                System.out.println(">>>密码输入错误，您还有" + (3-passwd_wrongconst) + "次输入机会。");
            }
        }
        if(passwd_wrongconst >= 3){
            System.out.println(">>>输入密码错误3次，账号已被锁定，请联系客服进行解锁。");
            users.getuser(index).setLock(1);
            return;
        }else{
            System.out.println(">>>登录成功!");
            System.out.print(">>>是否要查看您的账号信息？ 1. yes 2. no :");
            int confirm = sc.nextInt();
            if(confirm == 1)
            {
                System.out.println("您的账号信息为：");
                System.out.println(users.getuser(index).toString());
            }
        }

    }
}
