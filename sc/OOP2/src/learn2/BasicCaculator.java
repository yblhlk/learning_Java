package learn2;

public class BasicCaculator extends Calculator{
    public int Div(int a, int b) {
        if (b != 0) {
            System.out.print(a + " / " + b + " = ");
            return a / b;
        }
        System.out.print("除数不能为0, 结果已重置为");
        return 0;
    }
}
