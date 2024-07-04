package day1.Homework;

public class c {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            while(true){
                double temperature = Math.random()*20 + 20;
                System.out.println(temperature + "℃");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{
            while (true){
                double illumination = Math.random()*20 + 70;
                System.out.println(illumination + "g/m³");
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        new Thread(()->{
            while(true){
                double humidity = Math.random()*60 + 20;
                System.out.println(humidity + "Lux");
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        t1.start();
        t2.start();

    }
}
