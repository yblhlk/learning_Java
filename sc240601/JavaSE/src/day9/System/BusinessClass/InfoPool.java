package day9.System.BusinessClass;

import day9.System.EntityClass.ConsumInfo;
import day9.System.EntityClass.MobileCard;
import day9.System.EntityClass.Scene;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

// 用户池（模拟数据库）
public class InfoPool {
    // 每次启动系统就从本地缓存中反序列化所有用户信息文件，获取用户集合 和 消费记录集合。
    // 每次关闭系统就将当前用户集合 和 消费记录集合缓存到本地。
    // 因为要序列化，涉及到的所有类都一定要实现Serializable接口
    // 因为序列化方法是静态的所以下面都是静态的
    private static Map<String, MobileCard> users = new Hashtable<>(); //保存用户信息
    private static Map<String, List<ConsumInfo>> consums = new HashMap<>(); //保存消费记录
    private static List<Scene> scenes = new ArrayList<>(); // 保存使用场景

    public static Map<String, MobileCard> getUsers() {
        return users;
    }

    public static Map<String, List<ConsumInfo>> getConsums() {
        return consums;
    }

    public static List<Scene> getScenes() {
        return scenes;
    }

    //1.反序列化从本地获取信息
    public static void deserialize() {
        //a. 获取文件对象
        File file_u = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\InfoPoolCache\\users.ser");//保存用户信息
        File file_c = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\InfoPoolCache\\consums.ser");//保存消费记录
        File file_s = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\InfoPoolCache\\scenes.ser");// 保存使用场景

        //b. 创建对象输入流
        try (ObjectInput oiu = new ObjectInputStream(new FileInputStream(file_u));
             ObjectInput oic = new ObjectInputStream(new FileInputStream(file_c));
             ObjectInput ois = new ObjectInputStream(new FileInputStream(file_s));) {
            // c. 使用对象输入流，本地文件中的对象取出。
            users = (Map<String, MobileCard>) oiu.readObject();
            consums = (Map<String, List<ConsumInfo>>) oic.readObject();
            scenes = (List<Scene>) ois.readObject();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //2.将信息对象序列化保存到本地
    public static void serialize() {
        //a. 获取文件对象
        File file_u = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\InfoPoolCache\\users.ser");//保存用户信息
        File file_c = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\InfoPoolCache\\consums.ser");//保存消费记录
        File file_s = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\InfoPoolCache\\scenes.ser");// 保存使用场景

        //b. 创建对象输出流
        try (ObjectOutput oou = new ObjectOutputStream(new FileOutputStream(file_u));
             ObjectOutput ooc = new ObjectOutputStream(new FileOutputStream(file_c));
             ObjectOutput oos = new ObjectOutputStream(new FileOutputStream(file_s))) {
            // c. 使用对象输出流，将对象保存到本地。
            oou.writeObject(users);
            ooc.writeObject(consums);
            oos.writeObject(scenes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //3.将信息对象保存到本地文件中
    public static void saveLocal() {
        //a. 获取文件对象
        File file_u = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\SaveLocal\\users.txt");//保存用户信息
        File file_c = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\SaveLocal\\consums.txt");//保存消费记录
        File file_s = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day9\\System\\CacheFiles\\SaveLocal\\scenes.txt");// 保存使用场景

        //b. 创建文件输出流
        try (OutputStream oou = new FileOutputStream(file_u);
             OutputStream ooc = new FileOutputStream(file_c);
             OutputStream oos = new FileOutputStream(file_s)) {
            // c. 使用文件输出流，将信息保存到本地。
            // c1. 保存用户信息（姓名 + 账号）
            String ui = "姓名 : 账号\n";
            for (Map.Entry<String, MobileCard> e : users.entrySet()) {
                ui = ui + e.getValue().getUserName() + " : " + e.getValue().getCardNumber() + "\n";
            }
            oou.write(ui.getBytes(StandardCharsets.UTF_8));
            // c2. 保存消费记录（账号 + 记录）
            String ci = "账号 : 记录\n";
            for (Map.Entry<String, List<ConsumInfo>> s : consums.entrySet()) {
                ci = ci + s.getKey() + " : " + s.getValue() + "\n";
            }
            ooc.write(ci.getBytes(StandardCharsets.UTF_8));
            // c3. 保存使用场景
            String si = "使用场景：\n";
            for (Scene scene : scenes) {
                si += scene.toString() + "\n";
            }
            oos.write(si.getBytes(StandardCharsets.UTF_8));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//模拟池和数据库ƪ(˘⌣˘)ʃ优雅