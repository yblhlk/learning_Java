package day4.Work;

import java.util.Scanner;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T3A {
    public static void main(String[] args) {
        //1. 模拟用户买彩票
        Vector<Integer> userVector = new Vector<Integer>();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 7; i++) {
            // a. 输入6各红球的号码
            if (i != 6) {
                System.out.print("请输入第" + (i + 1) + "个红球的号码(1-33)：");
                Integer integer = sc.nextInt();
                while (userVector.contains(integer) || integer < 1 || integer > 33) {
                    System.out.println("无效输入，请勿输入已经输入的数字或不属于1-33内的数字");
                    System.out.print("请重新输入第" + (i + 1) + "个红球的号码(1-33)：");
                    integer = sc.nextInt();
                }
                userVector.add(integer);
            } else {
                //b. 输入一个蓝球的号码
                System.out.print("请输入蓝球的号码(1-16)：");
                Integer integer = sc.nextInt();
                while (integer < 1 || integer > 16) {
                    System.out.println("无效输入，请勿输入已经输入的数字或不属于1-16内的数字");
                    System.out.print("请重新输入：");
                    integer = sc.nextInt();
                }
                userVector.add(integer);
            }
        }

        // 2. 使用周期性定长线程池模拟开奖
        Vector<Integer> vector = new Vector<>();
        ScheduledExecutorService ex = Executors.newScheduledThreadPool(1);
        System.out.println("5秒后开奖！");
        for (int j = 0; j < 7; j++) {
            int i = j;
            ex.schedule(() -> {
                // a. 输入6各红球的号码j
                if (i != 6) {
                    //随机一个1-33的号码
                    //Math.random(): [0.0, 1.0)
                    int r = (int) (Math.random() * 33) + 1;
                    while (vector.contains(r)) {
                        r = (int) (Math.random() * 33) + 1;
                    }
                    vector.add(r);
                    System.out.println("第" + (i+1) + "个红球为" + r);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    //随机一个1-16的号码
                    int r = (int) (Math.random() * 16) + 1;
                    vector.add(r);
                    System.out.println("蓝球为" + r);
                }
            }, 5, TimeUnit.SECONDS);
        }

        ex.shutdown();


        while (vector.size() != 7) {
        }
        System.out.print("您输入的号码为：");
        for(int num: userVector) {
            System.out.print(num + " ");
        }
        System.out.print("\n中奖号码为：");
        for(int num: vector) {
            System.out.print(num + " ");
        }
        System.out.println();
        // 统计用户是否中奖
        int redCount = 0;
        int blueCount = 0;

        if (userVector.get(6) == vector.get(6)) {
            blueCount++;
            userVector.remove(6);
        }
        for (int i = 0; i < 6; i++) {
            // 要求顺序和号码对上
//            if (userVector.get(i).equals(vector.get(i))) {
//                redCount++;
//            }
            // 只要求号码对上
            if (userVector.contains(vector.get(i))) {
                redCount++;
            }
        }
        System.out.println("恭喜你猜中了" + redCount + "个红球，" + blueCount + "个蓝球。");
    }
}
