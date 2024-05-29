package Code.Serialization;

import java.io.*;

public class TestSerialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Student s = new Student("wang", 3);
        save1(s);
        System.out.println(read1());
    }

    //1.序列化函数
    public static void save(Student student) {
        //a.要序列化的对象要实现Serializable接口(标记接口)[implements Serializable]
        //b.确定保存序列化后对象的文件.
        File file = new File("./day8/src/Code/Serialization/Student.ser");
        try {
        //c.使用FileOutputStream对象创建ObjectOutputStream对象 (要处理IOException异常)
        // (ObjectOutput\ObjectOutputStream\FileOutputStream)
            ObjectOutput os = new ObjectOutputStream(new FileOutputStream(file));//要抛异常或捕获异常
            //d.使用创建好的ObjectOutputStream对象的writeObject方法来序列化目标对象,并将其写入到目标文件中.
            os.writeObject(student);
            //e.关闭连接
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //简化版序列化函数
    public static void save1(Student student) throws IOException{
        //a.要序列化的对象要实现Serializable接口
        //b.确认保存序列化对象的目标文件
        File file = new File("./day8/src/Code/Serialization/Student.ser");
        //c.创建ObjectOutputStream对象(ObjectOutput \ ObjectOutputStream \ FileOutputStream)
        //ObjectOutput接口继承了AutoCloseable接口,所以ObjectOutputStream对象可以实现自动关闭
        try(ObjectOutput os = new ObjectOutputStream(new FileOutputStream(file));){
            //d.使用ObjectOutput对象的writeObject()方法,序列化目标对象,并保存到目标文件中.
            os.writeObject(student);
            //e.这种写法实现了链接的自动关闭,不需要我们显示写关闭
        }
    }

    // 2.反序列化函数
    public static Student read(){
        //a. 获取包含序列化对象信息的文件对象
        File file = new File("./day8/src/Code/Serialization/Student.ser");
        try {
            //b. 使用FileInputStream对象创建一个ObjectInputStream对象，并用ObjectInput引用来接收
            ObjectInput is = new ObjectInputStream(new FileInputStream(file));//要抛出IOException
            //c. 使用ObjectInputStream对象的readObject()方法进行反序列化，获得一个Object对象，强转后用目标对象来接收。
            try {
                Student s = (Student) is.readObject();
                return s;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //d. 关闭链接 (继承了AutoCloseable接口的方法 + try() 可自动关闭连接)
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //简化版：
    public static Student read1() throws IOException, ClassNotFoundException {
        //a. 创建文件对象
        File file = new File("./day8/src/Code/Serialization/Student.ser");
        //b. 创建ObjectInputStream对象(输入输出流对象要抛异常和关闭连接）
        try(ObjectInput is = new ObjectInputStream(new FileInputStream(file))){
            //c. 使用创建对象的readObject方法反序列化 (要抛出类型未找到异常）
            Student s = (Student) is.readObject();
            return s;
            //d. 关闭链接，已经自动完成
        }
    }
}
