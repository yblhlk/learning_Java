package Array;

public class Array {
    public static void main(String[] args) {
        Dog[] dogs  = new Dog[2];
        dogs[0] = new Dog("chess", 18);

        Dog dog2 = new Dog("柴郡", 19);
        dogs[1] = dog2;

        for (int i = 0; i < dogs.length; i++) {
            System.out.println(dogs[i]);
        }
    }
}
