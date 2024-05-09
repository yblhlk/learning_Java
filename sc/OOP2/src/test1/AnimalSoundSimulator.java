package test1;

public class AnimalSoundSimulator {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Cat cat = new Cat();

        dog.simulateSound();   // 重写父类方法
        cat.simulateSound("喵喵喵。"); //重载父类方法
    }
}
