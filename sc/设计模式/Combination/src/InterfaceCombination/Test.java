package InterfaceCombination;

public class Test {
    public static void main(String[] args) {
        Block A = new Block("A");
        Block B = new Block("B");
        Block C = new Block("B");
        A.add(B);
        A.add(new Animal("Tiger"));
        A.add(new Animal("Lion"));
        C.add(new Animal("Snake"));
        C.add(new Animal("Eagle"));
        A.add(new Plant("blue_flower"));
        A.add(new Plant("red_flower"));
        B.add(new Plant("yellow_flower"));

        A.description();
        C.description();
    }
}
