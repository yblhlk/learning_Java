//package IO;
//
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//
//public class TestOutputStream {
//    public static void main(String[] args)  throws IOException {
//        //1. 使用FileOutputStream把内容写入文件
//        File file = new File("D:\\hello.txt");
//        File file1 = new File("D:\\hello1.txt");
//        try(OutputStream os = new FileOutputStream(file)){
//            os.write("阿凡达沙发沙发打赏".getBytes(StandardCharsets.US_ASCII));
//        }
//
//        // 2.把C盘的一个图片文件写入到D盘
////        {
////            File file = new File("C:\\1.PNG"); //没有的文件会报：FileNotFoundException（文件找不到异常）
////            File file2 = new File("D:\\1.PNG");
////            try(InputStream is = new FileInputStream(file);
////                OutputStream os = new FileOutputStream(file2, true)) {
////                int len = -1;
////                byte[] data = new byte[1024 * 1024 * 50];
////                while((len = is.read(data)) != -1){
////                    String str = new String(data, 0, len);
////                    System.out.println(str);
////                }
////                os.write(data);
////            }
////        }
//    }
//}
//
//// 输入输出流中最大的类是 InputStream 和OutputStream 它们是字节流
//// 字符流是 Reader 和 Writer
//// 一个中文字符是3个字节，但其可以用Unicode码表示，一个Unicode码只占2个字节。char保存的就是Unicode码。
//
