//package IO;
//
//import java.io.*;
//
//public class TestInputStream {
//    public static void main(String[] args) throws IOException {
//        File file = new File("D:\\1.txt");
//        try(InputStream is = new FileInputStream(file)) {
//            int len = -1;
//            byte[] data = new byte[1024 * 512];
//            while((len = is.read(data)) != -1){
//                String str = new String(data, 0, len);
//                System.out.println(str);
//            }
//        }
//    }
//}
