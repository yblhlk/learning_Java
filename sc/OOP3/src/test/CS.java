package test;

import java.util.Scanner;

public class CS {
    public static void main(String[] args) {
        int[] hash = new int[500];
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String s = sc.next();

        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i)]++;
        }
        for (int i = 0; i < 500; i++) {
            if(hash[i] > 0)
            {
                char c = (char)i;
                System.out.println("字符" + c + "，出现了" + hash[i] + "次");
            }

        }
    }
}
