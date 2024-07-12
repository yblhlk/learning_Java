package day9.System.BusinessClass;

import day9.System.EntityClass.*;

import java.util.ArrayList;
import java.util.List;

public class UserRegisterSystem {
// 1. 用户注册系统
//    选择卡号（提供以“139”开头的9个随机卡号,每行3个）
//    选择套餐（话唠套餐、网虫套餐、超人套餐）
//    输入用户名、密码信息 输入预存话费金额
    public static void userRegister() {
        System.out.println("\033[34m|>开始进行用户注册。\033[0m");
        String cardNumber = null;
        String userName = null;
        String passWord = null;
        ServicePackage serPackage = null; //（套餐类型：和套餐类对应）
        double consumAmount = 0; // （已消费金额）
        double money = -1;// （余额）
        int realTalkTime = 0; // （剩余通话时间）
        int realSMSCount = 0;//（剩余短信数量）
        int realFlow = 0; //（剩余流量）

        //1. 生成随机号码让用户选择
        cardNumber = createNumber();
        //2. 输入姓名和密码
        System.out.print("\033[34m|>请输入您的姓名:\033[0m");
        userName = Util.sc.next();
        System.out.print("\033[34m|>请输入您的密码:\033[0m");
        passWord = Util.sc.next();
        // String passWord = createPassword(); //密码判断系统。

        while(money == -1) {
            //3. 选择套餐类型
            serPackage = selectPackage(); //（套餐类型：和套餐类对应）
            //4. 充值（要求第一次充值的金额必须大于余额）
            money = recharge(serPackage);// （余额）
        }

        //5. 通过instanceOf关键字来判断对象的具体类型，然后向下转型，获取套餐信息赋值给下面的字段
        if(serPackage instanceof TalkPackage) {
            TalkPackage talkPackage = (TalkPackage) serPackage;
            realTalkTime = talkPackage.getTalkTime();
            realSMSCount = talkPackage.getSmsCount();
            System.out.println("\033[34m|>恭喜您成功办理话痨套餐！您的\033[0m" + talkPackage.showInfo() + "\033[34m已生效。\033[0m");
        }
        if(serPackage instanceof NetPackage) {
            NetPackage netPackage = (NetPackage) serPackage;
            realFlow = netPackage.getFlow()*1024;
            System.out.println("\033[34m|>恭喜您成功办理网虫套餐！您的\033[0m" + netPackage.showInfo() + "\033[34m已生效。\033[0m");
        }
        if(serPackage instanceof SuperPackage) {
            SuperPackage superPackage = (SuperPackage) serPackage;
            realTalkTime = superPackage.getTalkTime();
            realSMSCount = superPackage.getSmsCount();
            realFlow = superPackage.getFlow()*1024;
            System.out.println("\033[34m|>恭喜您成功办理超人套餐！您的\033[0m。" + superPackage.showInfo() + "\033[34m已生效。\033[0m");
        }

        //6. 将用户数据保存到集合中去
        MobileCard mc = new MobileCard(cardNumber,userName,passWord,serPackage,
                consumAmount,money,realTalkTime,realSMSCount,realFlow);
        InfoPool.getUsers().put(cardNumber,mc);
        System.out.print(mc.showMeg());
        System.out.println("，已保存到用户池中。");
        System.out.println(InfoPool.getUsers().get(cardNumber).showMeg());

        //7. 为每个用户创建一个空的消费记录List集合
        List<ConsumInfo> list = new ArrayList<>();
        InfoPool.getConsums().put(cardNumber,list);
        System.out.println("用户" + InfoPool.getConsums().keySet() + "的用户消费记录集合已经创建，并成功保存到池中。");

        //将最新用户信息序列化保存
        InfoPool.serialize();
        System.out.println("用户信息已保存到本地");
    }

    // 1. 生成随机号码让用户选择
    // 提供以“159”开头的9个随机卡号,每行3个，要求不能和用户池中的数据重复。
    public static String createNumber() {
        // a. 生成九个随机号码(保证不和用户池中的数据重复
        List<String> phoneNumbers = createRandomNumber();
        // b. 打印随机生成的9个号码供用户选择：
        int select = 0;
        do {
            System.out.println("\033[34m|>请从下方九个号码中选择一个您的喜欢的号码。\033[0m");
            System.out.println("\033[34m ______________________________________________\033[0m");
            for (int i = 1; i <= 9; i++) {
                if(i%3 == 1) {
                    System.out.print("\033[34m| ");
                }
                System.out.print("<" + i + ">" + phoneNumbers.get(i - 1) + " ");
                if(i%3 == 0) {
                    System.out.println("|\033[0m");
                }
            }
            System.out.println("\033[34m| <0>都不喜欢,重新生成。                         |\033[0m");
            System.out.println("\033[34m|______________________________________________|\033[0m");
            System.out.print("\033[34m|>您选择：\033[0m");
            select = Util.sc.nextInt();
            if(select == 0) {
                System.out.println("\033[34m|正在生成新的号码……");
                phoneNumbers = createRandomNumber();
            } else if(select < 0 || select > 9) {
                System.out.println("\033[31m|>对不起，做不到。>_<\033[0m");
            }
        } while(select <= 0 || select > 9);
        return phoneNumbers.get(select-1);
    }

    // 1.1 生成九个随机号码(保证不和用户池中的数据重复)
    public static List<String> createRandomNumber() {
        List<String> phoneNumbers = new ArrayList<>();
        String phoneNumber = null;
        for (int i = 0; i < 9; i++) {
            phoneNumber = "159";
            do{
                for (int i1 = 0; i1 < 8; i1++) {
                    phoneNumber += Util.random.nextInt(10);
                }
            } while(phoneNumbers.contains(phoneNumber) || InfoPool.getUsers().containsKey(phoneNumber));
            phoneNumbers.add(phoneNumber);
        }
        return phoneNumbers;
    }

    //3. 选择套餐类型
    public static ServicePackage selectPackage() {
        // b. 打印随机生成的9个号码供用户选择：
        int select = 0;
        do {
            System.out.println("\033[34m|>请选择您的套餐：\033[0m");
            System.out.println("\033[34m ______________________________________________\033[0m");
            System.out.println("\033[34m|              <1> 话痨套餐                     |\033[0m");
            System.out.println("\033[34m|              <2> 网虫套餐                     |\033[0m");
            System.out.println("\033[34m|              <3> 超人套餐                     |\033[0m");
            System.out.println("\033[34m|              <0> 查询套餐详情。                |\033[0m");
            System.out.println("\033[34m|______________________________________________|\033[0m");
            System.out.print("\033[34m|>您选择：\033[0m");
            select = Util.sc.nextInt();
            switch (select) {
                case 0:
                    System.out.println("\033[34m|>正在为您打印套餐详情……\033[0m");
                    Util.costDescription();
                    break;
                case 1:
                    System.out.println("\033[34m|>正在为您办理话痨套餐……\033[0m");
                    return TalkPackage.get();
                case 2:
                    System.out.println("\033[34m|>正在为您办理网虫套餐……\033[0m");
                    return NetPackage.get();
                case 3:
                    System.out.println("\033[34m|>正在为您办理超人套餐……\033[0m");
                    return SuperPackage.get();
                default:
                    System.out.println("\033[31m|>对不起，做不到。>_<\033[0m"); break;
            }
        } while(select <= 0 || select > 3);
        return null;
    }
    //4. 充值（要求第一次充值的金额必须大于套餐价钱）
    public static double recharge(ServicePackage serPackage) {
        double money = 0;
        int select = 1;
        while (money < serPackage.getPrice()) {
            switch (select) {
                case 1:
                    System.out.print("\033[34m|>请进行预充值(必须为50的整数倍，且大于套餐价格)：\033[0m");
                    money = Util.sc.nextInt();
                    if(money % 50 == 0 && money > serPackage.getPrice()) {
                    } else {
                        System.out.println("\033[31m|>金额不符合要求，请重新输入或更换套餐：\033[0m");
                        money = 0;
                    }
                    break;
                case 2:
                    return -1;
                default:
                    System.out.println("\033[31m|>对不起，做不到。>_<\033[0m"); break;
            }
            if(money == 0) {
                System.out.println("\033[31m|>1. 重新输入 2. 更换套餐\033[0m");
                System.out.print("\033[31m|>您选择：\033[0m");
                select = Util.sc.nextInt();
            }
        }

        System.out.println("\033[34m|>充值成功，扣除套餐费" + serPackage.getPrice() + "元后，当前余额：" + (money-serPackage.getPrice()) + "元，继续为您办理套餐。\033[0m");
        return (money-serPackage.getPrice());
    }
}
