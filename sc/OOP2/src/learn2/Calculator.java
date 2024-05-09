package learn2;

public class Calculator {
    public int Add(int a, int b) {
        System.out.print(a + " + " + b + " = ");
        return a + b;
    }

    public int Sub(int a, int b) {
        System.out.print(a + " - " + b + " = ");
        return a - b;
    }
    public int Mul(int a, int b) {
        System.out.print(a + " * " + b + " = ");
        return a * b;
    }
    public int Div(int a, int b) {
            System.out.print(a + " / " + b + " = ");
            return a / b;
    }

}
