package test2;

public class GeometryCalculator {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = new String("abc");

        if(s1 == s2)
            System.out.println("s1 = s2");
        if(s3 == s4)
            System.out.println("s3 = s4");
        if(s1 == s3)
            System.out.println("s1 = s3");

        Circle c = new Circle(1);
        Rectangle r = new Rectangle(1, 2);

        System.out.println("The area of the circle is " + c.getArea());
        System.out.println("The perimeter of the circle is " + c.getPerimeter());
        System.out.println("The area of the rectangle is " + r.getArea());
        System.out.println("The perimeter of the rectangle is " + r.getPerimeter());
    }
}
