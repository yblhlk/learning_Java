package day1.Homework;

public class a {
    public static void main(String[] args) {
        new Thread(()->{
            double H = 100.0;
            double sum = 0;
            int flag = 0;
            while(true){
                sum += H; //下落
                H /= 2.0;
                sum += H; //回弹
                flag++;
                if(flag == 10){
                    System.out.println("第10次回弹时共经过：" + sum + "米");
                    System.out.println("第10次回弹的高度为：" + H + "米");
                    break;
                }
            }
        }).start();
    }
}
