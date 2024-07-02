package day6.Work;

//b.通过一个类自定义的类  属性 任意  方法任意编写
//    通过反射获取类的所有属性和方法  然后 将获取的数据
//    写入到result.txt中  内容如下:
//    方法有:
//    属性有:
//    构造方法有:

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class B {
    public String name;
    private int age;

    public B() {
    }

    public B(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void test1(){};
    public int test2(int a){return a;};

    public static void main(String[] args) throws IOException {
        String description = "";
        //1. 创建一个类对象
        Class c = B.class;
        //2. 获取类对象的方法对象数组
        Method[] methods = c.getDeclaredMethods();
        //3. 遍历方法对象数组，获取方法描述
        description += "方法有：\n";
        for (Method m : methods) {
            description += m.getReturnType().getName();
            description += " ";
            description += m.getName();
            description += " ";
            Class[] arg = m.getParameterTypes();
            description += Arrays.toString(arg);
            description += "\n";
        }
        //4. 获取类对象的属性对象数组
        description += "属性有：\n";
        Field[] fields = c.getDeclaredFields();
        //5. 遍历属性对象数组，获取属性描述
        for (Field f : fields) {
            description += f.getType().getName();
            description += " ";
            description += f.getName();
            description += "\n";
        }
        //6. 获取构造方法对象数组
        description += "构造方法有：\n";
        Constructor[] constructors = c.getConstructors();
        //7. 遍历构造方法对象数组，获取构造方法描述
        for (Constructor con : constructors) {
            description += con.getName();
            description += " ";
            Class[] arg = con.getParameterTypes();
            description += Arrays.toString(arg);
            description += "\n";
        }
        System.out.println(description);

        // 创建文件对象
        // 注意相对路径是以项目为起点
        File file = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day6\\Work\\result.txt");
        if(!file.exists())
            file.createNewFile();

        // 其父类实现了Closeable接口，所以可以使用自动关闭
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(description);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
