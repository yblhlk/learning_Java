package com.Test;

import java.util.Scanner;

public class T2 {
    public static void main(String[] args) {
        System.out.println("请输入一个字符串");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();

        String str_r = reverse(str);
        System.out.println(str_r);
        return;
    }

    public static String reverse(String s) {
        String sr = "";
        for (int i = s.length()-1; i >= 0 ; i--) {
            sr += s.charAt(i);
        }
        return sr;
    }

}

