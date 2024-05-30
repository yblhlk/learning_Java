package Test;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Student> list = new ArrayList<>();
        list.add(new Student("taiyi",10000));
        list.add(new Student("xionger", 12));
        list.add(new Student("zhangsan",18));
        list.add(new Student("lisi",19));
        list.add(new Student("wangwu",22));

        Map<Student, Integer> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i), i);
        }

        //1.序列化和反序列化list
        //要序列化的对象的类和其引用类型成员的类都实现Serializable接口才能序列化。
        File file1 = new File("D:\\JavaCode\\OPP\\day8\\src\\Test\\list.ser");
        serialize(file1, list);
        System.out.println((List<Student>)deserialize(file1));

        //2.序列化和反序列化map
        File file2 = new File("D:\\JavaCode\\OPP\\day8\\src\\Test\\map.ser");
        serialize(file2, map);
        System.out.println((Map<Student, Integer>)deserialize(file2));
    }

    // 序列化函数
    public static void serialize(File file, Object clazz) throws IOException {
        //1. 确认保存序列化后对象信息的文件 -> file
        //2. 创建ObjectOutputStream对象
        try(ObjectOutput os = new ObjectOutputStream(new FileOutputStream(file))){
            //3.使用对象的writeObjet()方法序列化对象并保存到目标文件中
            os.writeObject(clazz);
            //4.关闭输入输出流的链接（AutoCloseable + try())
        }
    }

    // 反序列化函数
    public static Object deserialize(File file) throws IOException, ClassNotFoundException {
        //1.确定要反序列化的目标文件 -> file
        //2.创建ObjectInputStream对象
        try(ObjectInput is = new ObjectInputStream(new FileInputStream(file))){
            //3.使用对象的readObject方法返回反序列化的对象
            Object re = is.readObject(); //要处理ClassNotFoundException
            return re;
            //4.关闭输入输出流的链接（AutoCloseable + try())
        }
    }
}
