import java.util.ArrayList;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Users users = new Users();
        Register r = new Register();
        Login l = new Login();
        r.register(users);
        r.register(users);
        l.login(users);
        users.printUsers();

        //生成随机vip号测试
//        for (int i = 0; i < 30; i++) {
//            String vip_number = "";
//            for (int j = 0; j < 4; j++) {
//                Random random = new Random();
//                vip_number += random.nextInt(10);
//            }
//            System.out.println(vip_number);
//        }
    }
}
