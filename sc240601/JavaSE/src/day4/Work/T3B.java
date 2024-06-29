package day4.Work;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class T3B {
    public static void main(String[] args) throws IOException {
    // 1. 读取文件 （通过字符流、输入流、处理流）
        //字节流 字符流
        //输入流 输出流
        //节点流 处理流（缓存流）
        Vector<String> v = new Vector<>();
        File file = new File("D:\\JavaCode\\sc240601\\JavaSE\\src\\day4\\Work\\english.txt");
        //文件输入流
        FileReader fr = new FileReader(file);
        //处理流
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String str = null;
        while ((str = br.readLine()) != null)
            sb.append(str);
        System.out.println(sb);
        //关闭流省略...

        //2. stringbuilder转换字符串  在通过空格隔开  进行分割
        String[] strs=sb.toString().split(" ");
        //统计 单词（key）  次数(value)
        Map<String,Integer> map=new HashMap<>();
        //思路: 遍历每隔数组每隔单词 查找map中是否存在
        //存在了 次数 +1  不存在 次数=1
        for(String s:strs){
            if(map.containsKey(s)){ //存在了 次数 +1
                map.put(s, map.get(s)+1);
            }else{ //不存在 次数=1
                map.put(s,1);
            }
        }

        //3.如何排序
        //map集合中的key和value  统称可以叫entry
        //map通过entrySet()
        // 可以把map集合每组键值队 都封装成entry对象返回
        Set<Map.Entry<String,Integer>> set=map.entrySet();
        //Set==>list
        List<Map.Entry<String,Integer>> list=new ArrayList<>(set);
        Collections.sort(list,(o1,o2)-> o2.getValue()-o1.getValue());
        System.out.println(list);

//
//        new Thread(()->{
//            String str = null;
//            char[] c = new char[1];
//            int len = -1;
//            while (true) {
//                try {
//                    if (!((str=br.readLine()) != null)) break;
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        fr.close();
//
//        // 使用Map来统计单词数量
//        Map<String, Integer> map = new HashMap<>();
//        for(int i = 0; i < v.size(); i++) {
//            int count = 0;
//            for (int i1 = 0; i1 < v.size(); i1++) {
//                if(v.get(i).equals(v.get(i1))) {
//                    count++;
//                }
//            }
//            map.put(v.get(i), count);
//        }
//
//        // 对Map排序
//        // 使用Java 8的Stream API对Map按值排序
//        List<Map.Entry<String, Integer>> sortedEntries = map.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue())
//                .collect(Collectors.toList());
//
//        // 创建一个新的LinkedHashMap来保持排序顺序
//        Map<String, Integer> sortedMap = new LinkedHashMap<>();
//        for (Map.Entry<String, Integer> entry : sortedEntries) {
//            sortedMap.put(entry.getKey(), entry.getValue());
//        }
//
//        // 打印排序后的Map
//        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }
    }
}
