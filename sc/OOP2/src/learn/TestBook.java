package learn;

public class TestBook {
    public static void main(String[] arg) {
        ChinessBook cb = new ChinessBook("人教出版社", 13);
        MathBook mb = new MathBook("清华出版社", 33);
        cb.introduce();
        cb.introduce(10);
        mb.introduce();
//        mb.f(10, 10); //不明确，会报错。
    }
}
