package com.Test;

import java.util.Scanner;

public class T4 {
    public static void main(String[] args) {
        int[] a = {1, 1, 2, 2, 3, 3};
        int index = 0;
        for(int i = 0; i < a.length; i++)
        {
            if(a[index] != a[i])
            {
                index++;
                a[index] = a[i];
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("\n" + (index+1));
    }
}
