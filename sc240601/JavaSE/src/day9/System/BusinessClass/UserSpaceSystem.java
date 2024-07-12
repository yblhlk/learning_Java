package day9.System.BusinessClass;

// 判断是否有"用户信息缓存”文件，有证明有用户登录，读取用户信息，从用户池中找到用户对象，进入二级菜单
// 没有就直接退出。

import day9.System.EntityClass.*;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class UserSpaceSystem {
    public static void userSpace() {
        // 0.读取用户信息(反序列化)，判断是否登录成功：
        File userInf = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\LoginCache\\userInfoCache.inf");
        // 没有用户信息缓存文件直接退出
        if (!userInf.exists()) {
            System.out.println("未找到用户信息，登录用户空间失败！");
            return;
        }
        // 有缓存文件，判断其中是否有用户信息：
        String userNumber = "";
        try (ObjectInput oi = new ObjectInputStream(new FileInputStream(userInf))) {
            userNumber = (String) oi.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (userNumber.equals("")) {
            System.out.println("未找到用户信息，登录用户空间失败！");
            return;
        } else {
            System.out.println("成功进入用户空间");
        }

        // 1.登陆成功后，读取用户信息 和 用户账单列表，从用户池(模拟数据库)中找到用户对象
        MobileCard mc = InfoPool.getUsers().get(userNumber);
        List<ConsumInfo> consumInfoList = InfoPool.getConsums().get(userNumber);
        List<Scene> sceneList = InfoPool.getScenes();
        System.out.println("\033[36m|>> 尊敬的用户" + mc.getUserName() + ", 欢迎登录用户空间!\033[0m");

        int select = 0;
        do {
            menu2();
            select = Util.sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println(billInquiry(userNumber));
                    break;
                case 2:
                    packageBalanceInquiry(userNumber);
                    break;
                case 3:
                    printBill(userNumber);
                    break;
                case 4:
                    changePackage(userNumber);
                    break;
                case 5:
                    useDouDou5G(userNumber);
                    break;
                case 6:
                    payThePhoneBill(userNumber);
                    break;
                case 7:
                    contactCustomerService(userNumber);
                    break;
                case 8:
                    signOutNet(userNumber);
                    return;
                case 0:
                    System.out.println("退出用户空间。");
                    return;
                default:
                    System.out.println("\033[31m输入有误，请重新选择！\033[0m");
            }
        } while (select != 0);
    }

    public static void menu2() {
        System.out.println("\033[36m _________________________________________________\033[0m");
        System.out.println("\033[36m|                     为您提供服务：                |\033[0m");
        System.out.println("\033[36m|                   1. 本月账单查询                 |\033[0m");
        System.out.println("\033[36m|                   2. 套餐余量查询                 |\033[0m");
        System.out.println("\033[36m|                   3. 打印消费详单                 |\033[0m");
        System.out.println("\033[36m|                   4. 套餐变更                     |\033[0m");
        System.out.println("\033[36m|                   5. 使用兜兜5G                   |\033[0m");
        System.out.println("\033[36m|                   6. 话费充值                     |\033[0m");
        System.out.println("\033[36m|                   7. 联系客服                     |\033[0m");
        System.out.println("\033[36m|                   8. 办理退网                     |\033[0m");
        System.out.println("\033[36m|                   0. 退出用户空间                 |\033[0m");
        System.out.println("\033[36m|__________________________________________________|\033[0m");
        System.out.print("\033[36m|>>您选择：\033[0m");
    }

    // 二级菜单：
    // 1. 本月账单查询
    public static String billInquiry(String userNumber) {
        MobileCard mc = InfoPool.getUsers().get(userNumber);
        List<ConsumInfo> consumInfoList = InfoPool.getConsums().get(userNumber);
        String re = "本月账单详情：\n";

        double sumPrice = 0;
        double sumFlow = 0;
        double sumSmsCount = 0;
        double sumTalkTime = 0;
        for (ConsumInfo consumInfo : consumInfoList) {
            re = re + consumInfo.toString() + "\n";
            sumPrice += consumInfo.getPrice();
            sumSmsCount += consumInfo.getSmsCount();
            sumTalkTime += consumInfo.getTalkTime();
            sumFlow += consumInfo.getFlow();
        }
        re = re + "本月通话时长：" + sumTalkTime + "分钟\n";
        re = re + "本月短信数量：" + sumSmsCount + "条\n";
        re = re + "本月流量消耗：" + sumFlow + "MB\n";
        re = re + "本月套餐花费：" + mc.getSerPackage().getPrice() + "\n";
        re = re + "本月消费：" + (sumPrice + mc.getSerPackage().getPrice()) + "\n";

        return re;
    }

    // 2. 套餐余量查询
    public static void packageBalanceInquiry(String userNumber) {
        MobileCard mc = InfoPool.getUsers().get(userNumber);
        String re = "本月套餐余量：\n";
        re = re + "套餐剩余通话时长：" + mc.getRealTalkTime() + "分钟\n";
        re = re + "套餐剩余短信数量：" + mc.getRealSMSCount() + "条\n";
        re = re + "套餐剩余流量：" + mc.getRealFlow() + "MB\n";
        System.out.println(re);
    }

    // 3. 打印消费详单
    public static void printBill(String userNumber) {
        String bills = billInquiry(userNumber);
        // 将消费清单保存到本地
        String path = "D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\List\\" + userNumber + "的消费记录.txt";
        File file = new File(path);
        try (FileOutputStream os = new FileOutputStream(file)) {
            os.write(bills.getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bills);
        System.out.println("\033[36m|>>消费详单，已保存到本地文件中。\033[0m");
    }

    // 4. 套餐变更 （变更后更新用户信息）
    public static void changePackage(String userNumber) {
        //a. 获取用户信息对象
        MobileCard mc = InfoPool.getUsers().get(userNumber);
        //b. 让用户选择套餐但不能是原来的套餐
        while (true) {
            ServicePackage sc = selectPackage();
            int flag = 0; // 判断是否退出套餐更换系统。
            if (mc.getSerPackage() instanceof SuperPackage && sc instanceof SuperPackage) {
                System.out.println("\033[31m相同套餐无需更换！\033[0m");
                System.out.println("\033[31m是否退出套餐更换？按1退出，按其他键继续更换。\033[0m");
                flag = Util.sc.nextInt();
                if (flag == 1)
                    return;
            } else if (mc.getSerPackage() instanceof TalkPackage && sc instanceof TalkPackage) {
                System.out.println("\033[31m相同套餐无需更换！\033[0m");
                System.out.println("\033[31m是否退出套餐更换？按1退出，按其他键继续更换。\033[0m");
                flag = Util.sc.nextInt();
                if (flag == 1)
                    return;
            } else if (mc.getSerPackage() instanceof NetPackage && sc instanceof NetPackage) {
                System.out.println("\033[31m相同套餐无需更换！\033[0m");
                System.out.println("\033[31m是否退出套餐更换？按1退出，按其他键继续更换。\033[0m");
                flag = Util.sc.nextInt();
                if (flag == 1)
                    return;
            } else {
                // 判断余额是否支持更换套餐
                while (mc.getMoney() < sc.getPrice()) {
                    System.out.println("\033[31m余额不足以更换套餐！\033[0m");
                    System.out.println("\033[31m是否退出套餐更换？按1退出更换，按其他键进行充值。\033[0m");
                    flag = Util.sc.nextInt();
                    if (flag == 1) {
                        System.out.println("\033[31m余额不足，用户停止更换套餐。\033[0m");
                        return;
                    } else {
                        System.out.println("正在进入充值页面……");
                        payThePhoneBill(userNumber);
                    }
                }
                // 没有return的退出循环就是余额足够更换套餐
                mc.setSerPackage(sc);
                System.out.println("\033[36m|>>套餐更换成功！当前套餐信息：" + mc.getSerPackage().showInfo() + "\033[0m");
                // 更新用户池中的信息
                InfoPool.serialize();
                return;
            }
        }
    }

    public static ServicePackage selectPackage() {
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
                    System.out.println("\033[31m|>对不起，做不到。>_<\033[0m");
                    break;
            }
        } while (select <= 0 || select > 3);
        return null;
    }

    // 5. 使用兜兜5G （变更后更新用户信息）
    public static void useDouDou5G(String userNumber) {
        MobileCard mc = InfoPool.getUsers().get(userNumber);
        List<ConsumInfo> consumInfoList = InfoPool.getConsums().get(userNumber);
        List<Scene> sceneList = InfoPool.getScenes();

        //如果欠费，无法使用场景并提示用户缴费
        if (mc.getMoney() <= 0) {
            System.out.println("\033[31m|>> 抱歉，您已欠费，先已经停机，请充值后再使用！\033[0m");
            return;
        }

        //询问客户是否自定义场景
        int select = 0;
        System.out.print("\033[36m|>> 是否自定义场景，按1自定义，按其他键取消：");
        select = Util.sc.nextInt();

        // 没有场景就先自定义场景
        if (select == 1 || sceneList.size() == 0) {
            customScene();
        }
        //有场景了就从场景中随机抽取
        int r = Util.random.nextInt(sceneList.size());
        Scene s = sceneList.get(r);
        String type = s.getType();   // 消耗的内容
        int data = s.getData();      // 使用的数量
        double price = s.getPrice(); // 如果对应元素消耗完时，本次使用需要的花费
        double money = 0;

        System.out.println(s.toString()); //场景描述。

        if (type.equals("流量")) {
            if (mc.getRealFlow() < data) {
                System.out.println("正在消耗流量……");
                data -= mc.getRealFlow();
                mc.setRealFlow(0);
                money = data * price;
                mc.setMoney(mc.getMoney() - (data * price));
                if (mc.getMoney() <= 0) {
                    DecimalFormat format= new DecimalFormat("#.0");//定义格式化器
                    System.out.println("\033[31m|>> 余额不足，当前余额为：" + format.format(mc.getMoney()) + "元，请尽快充值。\033[0m");
                }
            } else {
                mc.setRealFlow(mc.getRealFlow() - data);
            }
        }
        if (type.equals("通话")) {
            if (mc.getRealTalkTime() < data) {
                System.out.println("正在通话……");
                data -= mc.getRealTalkTime();
                mc.setRealTalkTime(0);
                money = data * price;
                mc.setMoney(mc.getMoney() - (data * price));
                if (mc.getMoney() <= 0) {
                    System.out.println("\033[31m|>> 余额不足，当前余额为：" + mc.getMoney() + "元，请尽快充值。\033[0m");
                }
            }else {
                mc.setRealTalkTime(mc.getRealTalkTime() - data);
            }
        }
        if (type.equals("短信")) {
            if (mc.getRealSMSCount() < data) {
                System.out.println("正在发短信……");
                data -= mc.getRealSMSCount();
                mc.setRealSMSCount(0);
                money = data * price;
                mc.setMoney(mc.getMoney() - data * price);
                if (mc.getMoney() <= 0) {
                    System.out.println("\033[31m|>> 余额不足，已停机。当前余额为：" + mc.getMoney() + "元，请尽快充值。\033[0m");

                }
            }else {
                mc.setRealSMSCount(mc.getRealSMSCount() - data);
            }
        }
        // 创建消费记录
        ConsumInfo consumInfo = new ConsumInfo(type, data, money);
        InfoPool.getConsums().get(userNumber).add(consumInfo);
        InfoPool.serialize();
        InfoPool.saveLocal();
        System.out.println("消费记录添加成功。");
    }

    // 自定义场景
    public static void customScene() {
        System.out.println("开始自定义使用场景：");
        //1. 使用类型 和 花费
        String type = "";
        double price = 0;
        int s1 = 0;
        do {
            System.out.println("消耗的内容：1.通话时长 2.短信数量 3.流量");
            System.out.print("请选择：");
            s1 = Util.sc.nextInt();
            switch (s1) {
                case 1:
                    type = "通话";
                    price = 0.2;
                    break;
                case 2:
                    type = "短信";
                    price = 0.1;
                    break;
                case 3:
                    type = "流量";
                    price = 0.1;
                    break;
                default:
                    System.out.println("输入有误，请重新输入。");
                    break;
            }
        } while (s1 > 3 || s1 < 1);

        //2. 使用数量
        int data = 0;
        System.out.print("输入使用数量(注意流量的单位是MB)：");
        data = Util.sc.nextInt();
        //3. 对使用场景的描述
        String description = "";
        System.out.print("输对使用场景的描述：");
        description = Util.sc.next();
        //4. 创建使用场景对象，并保存到池中，并将池本地化（反序列化 + 详情文件）。
        //a. 创建使用场景对象
        Scene scene = new Scene(type, data, description, price);
        //b. 保存到池中
        InfoPool.getScenes().add(scene);
        //c. 将池序列化
        InfoPool.serialize();
        //d. 打印保存池信息的详情文件
        InfoPool.saveLocal();
        System.out.println("自定义使用场景成功。");
    }

    // 6. 话费充值 （变更后更新用户信息）
    public static void payThePhoneBill(String userNumber) {
        //a. 获取用户信息对象
        int select = 1;
        System.out.print("\033[36m|>>是否给自己充值？按1代表是，按其他键代表否。\033[0m");
        select = Util.sc.nextInt();
        if (select != 1) {
            System.out.print("\033[36m|>>请输入要充值的号码：\033[0m");
            userNumber = Util.sc.next();
        }
        if (!InfoPool.getUsers().containsKey(userNumber)) {
            System.out.println("\033[31m|>>该用户不存在！\033[0m");
        } else {
            MobileCard mc = InfoPool.getUsers().get(userNumber);
            int money = 0;
            while (true) {
                System.out.print("\033[36m|>>请输入您要充值的金额(必须为50的整数倍)：\033[0m");
                money = Util.sc.nextInt();
                if (money % 50 == 0) {
                    break;
                } else {
                    System.out.println("\033[31m|>金额不符合要求，请重新输入。\033[0m");
                    money = 0;
                }
            }
            mc.setMoney(mc.getMoney() + money);
            System.out.println("\033[36m|>>充值成功！当前余额为:" + mc.getMoney() + "\033[0m");
            // 更新用户池中的信息
            InfoPool.serialize();
        }
    }

    // 7. 联系客服
    public static void contactCustomerService(String userNumber) {
        MobileCard mc = InfoPool.getUsers().get(userNumber);
        System.out.println("\033[36m|>>尊敬的用户：" + mc.getUserName() + "，请稍等，正在为您联系客服……\033[0m");
        //a. 使用socket编程连接服务器
        try {
            Socket client = new Socket("localhost", 9999);

            System.out.println("\033[36m|>>已接通客服，【客服王小明】将为您提供帮助。输入esc退出聊天。\033[0m");
            // 开启线程从服务端接收数据 (使用线程池，我们就可以随时结束聊天）
            ExecutorService es = Executors.newSingleThreadExecutor(); //创建一个单线程池
            es.execute(() -> {
                // 开启输入线程，接收服务端的数据
                try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
                    while (true) {
                        String msg = br.readLine();
                        if(msg != null)
                            System.out.println("\033[36m|>>【客服王小明】:" + msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            // 向服务端写入数据
            client.getOutputStream().write(("成功连接客户【" + mc.getUserName() + "】。\n").getBytes(StandardCharsets.UTF_8));
            while (true) {
                String msg = "";
                msg = Util.sc.next();
                if(msg.equalsIgnoreCase("esc")) {
                    client.getOutputStream().write(("【" + mc.getUserName() + "】：退出聊天。\n" ).getBytes(StandardCharsets.UTF_8));
                    es.shutdownNow(); //关闭接收消息。
                    System.out.println("\033[36m|>>正在退出聊天……\033[0m");
                    return;
                }
                if (msg != null) {
                    client.getOutputStream().write(("【" + mc.getUserName() + "】：" + msg + "\n").getBytes(StandardCharsets.UTF_8));
                }
            }
        } catch (IOException e) {
            System.out.println("抱歉，客服不在线，请于其他时间段联系客服。");
        }

    }

    // 8. 办理退网（变更后更新用户信息）
    public static void signOutNet(String userNumber) {
        System.out.println("\033[36m|>>正在办理退网手续……\033[0m");
        if(InfoPool.getUsers().get(userNumber).getMoney() < 0) {
            System.out.println("\033[31m|>>抱歉，您已欠费，请将欠费还清后再尝试退网!\033[0m");
            return;
        }
        InfoPool.getUsers().remove(userNumber);
        InfoPool.getConsums().remove(userNumber);
        // 更新用户池中的信息
        InfoPool.serialize();
        System.out.println("\033[36m|>>成功退网，期待与您的下一次相遇。\033[0m");
    }
}

