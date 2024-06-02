package IO;

import java.io.File;
import java.io.IOException;

public class TestFile {
    public static void main(String[] args) throws IOException {
        // 1.创建File对象的四个方法
        File path = new File("D:\\JavaCode" + File.separatorChar +"OPP\\Day9\\src");
        File directory = new File(path, "\\Directory");
        File file = new File(directory, "file.txt");
        File file1 = new File("D:\\JavaCode\\OPP\\Day9\\src\\Directory", "file1.txt");
        // 注意:没有的文件不会和File对象一起创建
        // 为什么是两个反斜杠？ "\\" 字符转义为 "\" , 单个反斜杠是转义。 Windows系统是两个反斜杠。
        // 可以使用File类的separatorChar静态成员，会根据系统来判断是用哪个斜杆。

        // 2.File对象的API
        //getName() : 获取文件名
        System.out.println(file.getName());
        //exists() : 判断文件/目录是否存在
        System.out.println(path.exists());
        System.out.println(directory.exists());
        System.out.println(file.exists());
        //mkdir() : 创建一级目录
        System.out.println(path.mkdir()); //创建已有的目录，返回false
        System.out.println(directory.mkdir());
        //mkdirs() : 创建多级目录 //还没有创建的目录有多级的情况下用mkdirs()
        File directorys = new File(directory, "\\a\\b\\c\\d");
        System.out.println(directorys.mkdir()); //mkdir()无法创建多级目录
        System.out.println(directorys.mkdirs()); //使用mkdirs()创建多级目录
        //idDirectory() : 判断是否为目录 , 只能判断已经创建的目录, 未创建的目录直接返回false
        System.out.println(directory.isDirectory());
        System.out.println(directorys.isDirectory());
        //list() : 获取目录下的所有文件的文件名，返回一个String数组
        System.out.println(directory.list());
        for(String s : directory.list())
            System.out.print(s + " ");
        //listFiles() : 获取目录下的所有文件的File对象，返回一个File对象数组
        System.out.println("\n" + directory.listFiles());
        for(File f : directory.listFiles())
            System.out.println(f);
        //exists() : 判断文件/目录是否存在
        System.out.println(file.exists());
        //isFile() : 判断是否为文件
        System.out.println(file.isFile());
        //createNewFile() : 创建文件
        System.out.println(file.createNewFile()); //要抛出IOException
        //isFile() : 判断是否为文件 , 不存在的文件直接判断为false.
        System.out.println(file.isFile());

        //delete() : 删除文件/单级目录 （无法删除多级目录。)
        System.out.println(file.delete());
        System.out.println(directorys.delete()); //可以删除单级目录。
        System.out.println(directory.delete()); //无法删除多级目录。

        //deleteOnExit() : 并不会立即删除文件或目录，而是在 JVM 正常关闭（无论是由于程序正常结束、异常退出还是系统关闭）时，才会尝试删除该文件或目录。
        directorys.deleteOnExit();

        //getFreeSpace() : 用于获取指定路径所在分区的未分配空间大小，单位为字节。
        System.out.println(directory.getFreeSpace());

        // 在Java中，File类本身并没有直接提供一个方法来递归地删除一个目录及其所有子目录和文件。但是，你可以通过编写一个递归的方法来实现这个功能。

    }
}
