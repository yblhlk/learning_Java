import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Register {
    public void register(Users users){
        // 1.用户输入用户名，不能重复
        Scanner sc = new Scanner(System.in);
        System.out.print(">>>请输入用户名：");
        String username = "";
        if(users.size() == 0){
            username = sc.next();
        } else {
            int confirm = 1;
            do{
                username = sc.next();
                int i = 0;
                for (; i < users.size(); i++) { //没有用户的时候不会进入，就无法创建
                    if(username.equals(users.getuser(i).getUsername())){
                        System.out.println(">>>用户名已经存在，请重新输入：");
                        confirm = 1;
                        break;
                    }
                    if(i == users.size()-1){
                        confirm = 0; //没有重名，不用循环
                        break;
                    }
                }
            }while (confirm == 1);

        }

        // 2.生成会员号，不重复，展示给用户
        /* 版本一：有缺陷，只能生成1000-9999的数，所以要使用字符串
        int vip_number = 0;
        Random random = new Random();
        vip_number = (int)(Math.random()*1000) + (random.nextInt(9)+1)*1000;
        */
        /*版本二*/
        String vip_number = "";
        if(users.size() == 0){
            vip_number = "";
            for (int j = 0; j < 4; j++) {
                Random random = new Random();
                vip_number += random.nextInt(10);
            }
            //System.out.println(">>>您的vip号为：" + vip_number + " 。");
        } else {
            int confirm = 1;
            do{
                vip_number = "";
                for (int j = 0; j < 4; j++) {
                    Random random = new Random();
                    vip_number += random.nextInt(10);
                }
                int i = 0;
                for (; i < users.size(); i++) { //没有用户的时候不会进入，就无法创建
                    if(vip_number.equals(users.getuser(i).getVip_number())){
                        confirm = 1;
                        break;
                    }
                    if(i == users.size()-1){
                        confirm = 0; //没有重复，不用再循环了
                        break;
                    }
                }
            }while (confirm == 1);
        }

        // 3.选择会员等级
        int vip_lv = 0;
        do {
            System.out.print(">>>请选择您的会员等级：1.普通会员 2. 高级会员 ");
            vip_lv = sc.nextInt();
            if(vip_lv < 1 || vip_lv > 2)
                System.out.println("请输入正确的等级");
        } while(vip_lv < 1 || vip_lv > 2);

        // 4. 设置密码
        String passwd = "";
        String cpasswd = "";
        boolean f = false;
        do{
            System.out.print(">>>请输入你的密码:");
            passwd = sc.next();

            System.out.print(">>>请再次输入一遍确认你的密码:");
            cpasswd = sc.next();
            if(!passwd.equals(cpasswd))
            {
                System.out.println(">>>两次密码不相同，请重新设置。");
                f  = true;
            }
            else
                f = false;
        }while (f);

        // 5. 将新用户加入到集合中
        User user = new User(username, vip_number, vip_lv, cpasswd);
        users.adduser(user);

        // 6. 给用户展示创建好的信息
        System.out.println(">>>您的账号信息为：" + user + "\n");
    }
}
