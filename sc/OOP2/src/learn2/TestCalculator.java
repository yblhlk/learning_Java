package learn2;

public class TestCalculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        BasicCaculator basicCaculator = new BasicCaculator();
        AdvancedCalculator advancedCalculator = new AdvancedCalculator();

        System.out.println(calculator.Add(1, 2));
        System.out.println(basicCaculator.Div(2, 0));
        System.out.println(advancedCalculator.Add(1, 2, 3));

        Integer i = 10;
        int j = 10;
        System.out.println(i.equals(j)); // 基本类型自动装箱后和包装类进行比较。
    }
}
