package com.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class T3 {
    public static void main(String[] args) {
        // 使用一个数组来初始化ArrayList
        System.out.println("请输入数组的长度：");
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();

        System.out.println("请输入数组元素，以空格分隔：");
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 0; i < len; i++) {
            int num = sc.nextInt();
            a.add(num);
        }
        System.out.println("请输入目标和：");
        int target = sc.nextInt();

        ArrayList<Integer> answer = getIndex(a, target);
        if(a.isEmpty()){
            System.out.println("没有找到");
        }else {
            System.out.println("[" + answer.get(0) + ", " + answer.get(1) + "]");
        }
    }

    public static ArrayList<Integer> getIndex(ArrayList<Integer> arr, int t){
        ArrayList<Integer> answer = new ArrayList<>();
        ArrayList<Integer> a = new ArrayList<>(arr);
        a.sort(Comparator.naturalOrder());
        for (int i = 0, j = a.size()-1; i < j; ) {
            if(a.get(i) + a.get(j) == t){
                for (int ii = 0, jj = 0; ii < arr.size(); ii++, jj++){
                    if(arr.get(ii).equals(a.get(i)))
                        answer.add(ii);
                    if(arr.get(ii).equals(a.get(j)))
                        answer.add(jj);
                }
                break;
            } else if(a.get(i) + a.get(j) < t) {
                i++;
            }else {
                j--;
            }
        }

        return answer;
    }
}
