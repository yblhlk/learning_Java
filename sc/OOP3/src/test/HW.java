package test;

import java.util.Scanner;

public class HW {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个字符串：");
        String s = sc.next();

        for(int i = 0, j = s.length()-1; i < j; i++, j--)
        {
            if(s.charAt(i) != s.charAt(j))
            {
                System.out.println("不是回文字符串。");
                return ;
            }

        }
        System.out.println("是回文字符串");
        return ;
    }
}
