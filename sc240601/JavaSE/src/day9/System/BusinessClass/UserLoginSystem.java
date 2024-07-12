package day9.System.BusinessClass;

// 1. 登录 （登录成功后，在本地序列化一个账号信息文件："用户信息缓存"）
// 2. 免登录 （登录成功后，用户可选择是否免登录，选择免登录会在本地生成一个缓存文件。定时删除，退出系统删除。）
// 3. 取消登录返回上一级

import java.io.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserLoginSystem {
    public static void userLogin(){
        System.out.println("\033[34m|>开始进行用户登录\033[0m");
        //0. 判断登录缓存文件是否存在，存在就获取用户信息，生成用户信息缓存，然后进入二级菜单
        File loginCache = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\LoginCache\\loginCache.cache");
        File userInfoCache = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\LoginCache\\userInfoCache.inf");
        String userInfo = "";

        if(loginCache.exists()) {
            System.out.println("\033[34m|>检测到用户设置了免登录，直接进入用户空间。\033[0m");
            // 存在就获取用户信息（反序列化)
            try (ObjectInput oi = new ObjectInputStream(new FileInputStream(loginCache))) {
                userInfo = (String) oi.readObject();
                System.out.println("读取用户信息缓存成功！");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("\033[31m|>未找到登录缓存文件，免登录失败！\033[0m");
                e.printStackTrace();
            }
        } else {
            // 1. 登录缓存文件不存在，进行登录（从池中获取用户信息对象）
            int failCount = 0;
            String number = "", pwd = "";
            // a. 要求用户输入已经存在的账号
            int flag = 0;
            while (true) {
                System.out.print("\033[34m|>请输入账号：\033[0m");
                number = Util.sc.next();
                if(!InfoPool.getUsers().containsKey(number)) {
                    System.out.println("\033[31m|>您输入的账号不存在，请重新输入：\033[0m");
                    flag++;
                } else {
                    break;
                }
                if(flag >= 3) {
                    System.out.println("\033[31m|>您输入的账号不存在，如果忘记账号，请联系客服找回。\033[0m");
                    return;
                }
            }
            do {
                System.out.print("\033[34m|>请输入密码：\033[0m");
                pwd = Util.sc.next();
                if(pwd.equals(InfoPool.getUsers().get(number).getPassWord())) {
                    break;
                } else {
                    failCount++;
                    System.out.println("\033[31m|>密码错误！您还有" + (3 - failCount) + "次输入机会\033[0m");
                }
            } while (failCount < 3);
            //登录失败三次，返回到上一级
            if(failCount >= 3) {
                System.out.println("登录失败三次，返回到上一级");
                return;
            } else {
                userInfo = number;
                // 1.2 实现免登录：登录成功后，用户可选择是否免登录，选择免登录会在本地生成一个登录缓存文件（序列化）。定时删除，退出系统删除。
                System.out.print("\033[34m|>是否保存账号信息，实行免登录？按1确定，按任意键取消。\033[0m");
                int select = Util.sc.nextInt();
                if(select == 1) {
                    try ( ObjectOutput oo = new ObjectOutputStream(new FileOutputStream(loginCache)) ) {
                        oo.writeObject(userInfo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.print("\033[34m|>账号信息保存成功，30秒内免登录。\033[0m");
                    // 使用可周期定长线程池定期清理缓存
                    ScheduledExecutorService ses=
                            new ScheduledThreadPoolExecutor(1);
                            ses.schedule(()->{ Util.deleteLoginCache(); },30, TimeUnit.SECONDS);

                } else {
                    System.out.print("\033[34m|>免登录未开启。\033[0m");
                }
            }
        }

        // 3.用户登录成功，生成用户信息缓存（将用户信息对象序列化到本地文件中去）
        if(!userInfo.equals("")) {
            try ( ObjectOutput oo = new ObjectOutputStream(new FileOutputStream(userInfoCache)) ) {
                oo.writeObject(userInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("用户登录成功!");
        } else {
            System.out.println("登陆失败");
        }
    }
}
