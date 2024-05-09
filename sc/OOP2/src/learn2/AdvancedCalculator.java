package learn2;

public class AdvancedCalculator extends Calculator{
    public int Add(int a, int b, int c) {
        int sum = super.Add(a, b);
        System.out.print(sum + "\n" + a + " + " + b + " + " + c + " = ");
        return sum + c;
    }

    public int Sub(int a, int b, int c) {
        int difference = super.Sub(a, b);
        System.out.print(difference + + a + " - " + b + "-" + c + " = ");
        return a - b - c;
    }
}
