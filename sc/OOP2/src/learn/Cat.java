package learn;

public class Cat {
    public static final String color = "black";
    public static int age;

    public static void eat(){
        System.out.println("吃猫粮。");
    }

    public void shout() {
        System.out.println("喵喵喵。");
    }

    static {
        System.out.println("喵");
    }

}
