package ManagmentSystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MS {
    private ArrayList<Information> arr = new ArrayList<Information>();

    public void addSI()
    {
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println(">>确定增加？(y/n)");
            char choose = sc.next().charAt(0);
            if (choose == 'y')
            {
                String id;
                a:while(true)
                {
                    System.out.println(">>请输入学生id：");
                    id = sc.next();

                    for (Information a:arr) {
                        if(id.equals(a.getId())) {
                            System.out.println(">>id重复！请重新输入\n");
                            continue a;
                        }
                    }
                    break;
                }

                System.out.println(">>请输入学生姓名：");
                String name = sc.next();
                System.out.println(">>请输入学生年龄：");
                int age = sc.nextInt();
                System.out.println(">>请输入学生生日(如：2024-04-14)：");
                String birthday  = sc.next();
                Information s = new Information(id, name, age, birthday);
                arr.add(s);
                System.out.println(">>学生信息添加成功：" + s.toString());
                return;
            }
            else if (choose == 'n')
            {
                System.out.println(">>成功退出添加界面。");
                return ;
            }
            else
            {
                System.out.println(">>输入有误，请重新输入！");
            }
        }

    }

    public void subSI()
    {
        if(empty()) return;
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println(">>确定删除？(y/n)");
            char choose = sc.next().charAt(0);
            if (choose == 'y')
            {
                while(true)
                {
                    System.out.println(">>请输入要删除的学生id:");
                    String id = sc.next();
                    for (Information a : arr) {
                        if(id.equals(a.getId())) {
                            System.out.println(">>学生{Id：" + a.getId() + "，姓名：" + a.getName() + "}，已成功删除！");
                            arr.remove(a);
                            return ;
                        }
                    }
                    System.out.println(">>id不存在！请重新输入。\n");
                }
            }
            else if (choose == 'n')
            {
                System.out.println("成功退出删除界面。");
                return ;
            }
            else
            {
                System.out.println(">>输入有误，请重新输入！");
            }
        }
    }

    public void modSI()
    {
        if (empty()) return;
        Scanner sc = new Scanner(System.in);

        while(true)
        {
            System.out.println(">>确定修改？(y/n)");
            char choose = sc.next().charAt(0);
            if (choose == 'y')
            {
                String id;
                while(true)
                {
                    System.out.println(">>请输入要修改的学生id：");
                    id = sc.next();

                    for (Information a : arr) {
                        if(id.equals(a.getId())) {
                            System.out.println(">>修改前的学生信息：" + a.toString());
                            System.out.println(">>>你想要修改?(1.id 2.姓名 3.年龄 4.生日 5.所有信息)");
                            int select = sc.nextInt();
                            switch (select) {
                                case 1:
                                    modifyId(a); break;
                                case 2:
                                    modifyName(a); break;
                                case 3:
                                    modifyAge(a); break;
                                case 4:
                                    modifyBirthday(a); break;
                                case 5:
                                    modifyAll(a); break;
                                default:
                                    System.out.println("选择有误，请重新输入。\n");
                            }
                            return ;
                        }
                    }
                    System.out.println("id不存在！请重新输入。\n");
                }
            }
            else if (choose == 'n')
            {
                System.out.println("成功退出修改界面。");
                return ;
            }
            else
            {
                System.out.println("输入有误，请重新输入！");
            }
        }

    }
    public void modifyId(Information a)
    {
        String id;
        Scanner sc = new Scanner(System.in);

        gt:while(true)
        {
            System.out.println(">>>请输入修改后的学生id：");
            id = sc.next();
            if(id.equals(a.getId())) {
                System.out.println(">>>id和修改前相同，请重新输入。\n");
                continue;
            }
            for (Information i:arr) {
                if(id.equals(i.getId())) {
                    System.out.println(">>>id已存在！请重新输入\n");
                    continue gt;
                }
            }
            break;
        }
        a.setId(id);
        System.out.println(">>修改后的学生信息：" + a.toString());
    }
    public void modifyName(Information a)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(">>>请输入修改后的学生姓名：");
        String name = sc.next();
        a.setName(name);
        System.out.println(">>修改后的学生信息：" + a.toString());
    }
    public void modifyAge(Information a)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(">>>请输入修改后的学生年龄：");
        int age = sc.nextInt();
        a.setAge(age);
        System.out.println(">>修改后的学生信息：" + a.toString());
    }
    public void modifyBirthday(Information a)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(">>>请输入修改后的学生生日：");
        String birthday = sc.next();
        a.setBirthday(birthday);
        System.out.println(">>修改后的学生信息：" + a.toString());
    }
    public void modifyAll(Information a)
    {
        String id;
        Scanner sc = new Scanner(System.in);
        arr.remove(a);
        gt:while(true)
        {
            System.out.println(">>请输入修改后的学生id：");
            id = sc.next();

            for (Information i:arr) {
                if(id.equals(i.getId())) {
                    System.out.println(">>id重复！请重新输入\n");
                    continue gt;
                }
            }
            break;
        }
        System.out.println(">>请输入修改后的学生姓名：");
        String name = sc.next();
        System.out.println(">>请输入修改后的学生年龄：");
        int age = sc.nextInt();
        System.out.println(">>请输入修改后的学生生日(yyyy-mm-dddd)：");
        String birthday  = sc.next();
        Information s = new Information(id, name, age, birthday);
        System.out.println(">>修改后的学生信息：" + s.toString());
        arr.add(s);
    }

    public void querySI()
    {
        if(empty()) return;
        Scanner sc = new Scanner(System.in);

        System.out.println(">>请输入要查询的学生id：");
        String id = sc.next();

        for (Information a : arr) {
            if(id.equals(a.getId())) {
                System.out.println(">>查询到的学生信息：" + a.toString());
                return;
            }
        }

        System.out.println(">>id不存在！");
    }

    public void menu() {
        System.out.println(" ——————————————————————————————————————————————");
        System.out.println("|             欢迎使用校园信息管理系统             |");
        System.out.println(" ——————————————————————————————————————————————");
        System.out.println("|             1.  进入学生信息管理系统            |");
        System.out.println(" ——————————————————————————————————————————————");
        System.out.println("|             2.  进入教师信息管理系统            |");
        System.out.println(" ——————————————————————————————————————————————");
        System.out.println("|                 0. 退出管理系统                |");
        System.out.println(" ——————————————————————————————————————————————");
    }

    public boolean empty() {
        if(arr.size() == 0)
        {
            System.out.println(">>系统中还没有存储学生信息，请先插入。");
            return true;
        }
        return false;
    }

}


//1. 删除、查找、修改前要判空
//2. 日期信息提示有误
//3. 修改时没有判断id是否重复。（修改单项信息，修改所有信息）