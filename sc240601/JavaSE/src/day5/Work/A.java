package day5.Work;
import java.io.*;
import java.util.*;

class User {
    String phone;
    String password;
    String name;

    public User(String phone, String password, String name) {
        this.phone = phone;
        this.password = password;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class A {
    private static final String FILE_NAME = "users.dat";
    private static List<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadUsers(); //从文件中加载用户
        while (true) {
            System.out.println("请选择操作：");
            System.out.println("a. 注册");
            System.out.println("b. 登录");
            System.out.println("c. 退出系统");
            char choice = scanner.next().charAt(0);
            switch (choice) {
                case 'a':
                    register();
                    break;
                case 'b':
                    login();
                    break;
                case 'c':
                    saveUsers(); //保存用户到文件中
                    System.exit(0);
                default:
                    System.out.println("无效的选择，请重新选择。");
            }
        }
    }

    private static void saveUsers() {
        try {
            FileOutputStream fileOut = new FileOutputStream("users.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
            System.out.println("用户数据已成功保存到文件。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadUsers() {
        try {
            FileInputStream fileIn = new FileInputStream("users.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (ArrayList<User>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("用户数据已成功从文件加载。");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void register() {
        String name = "";
        String password = "";
        String phone = getPhoneNumber();

        System.out.println("请输入姓名：");
        name = scanner.nextLine();
        scanner.nextLine();
        System.out.println("请输入密码：");
        password = scanner.nextLine();

        User newUser = new User(phone, password, name);
        users.add(newUser);
        System.out.println("注册成功！您的手机号是：" + phone);
    }

    private static String getPhoneNumber() {
        Random rand = new Random();
        String prev = "150";
        String phoneNumber = "";

        do {
            phoneNumber = prev + String.format("%06d", rand.nextInt(900000) + 100000); // Generate a 6-digit number
        } while (isExists(phoneNumber));

        return phoneNumber;
    }
    // 判断手机号是否重复
    private static boolean isExists(String phoneNumber) {
        for (User user : users) {
            if (user.phone.equals(phoneNumber)) {
                return true;
            }
        }
        return false;
    }

    private static void login() {
        System.out.println("请输入手机号：");
        String phone = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.phone.equals(phone) && user.password.equals(password)) {
                System.out.println("登录成功！欢迎，" + user.name);
                // 进入二级菜单或执行其他登录后操作  
                return;
            }
        }
        System.out.println("手机号或密码错误，请重试。");
    }
}
