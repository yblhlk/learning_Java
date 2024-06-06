package AbstractCombination;

public class Test {
    public static void main(String[] args) {
        BookCombination once1 = new Once("科学");
        BookCombination  twice1_1 = new Twice("物理");
        BookCombination   book1_1_1 = new Book("相对论");
        BookCombination   book1_1_2 = new Book("果壳中的宇宙");
        BookCombination  twice1_2 = new Twice("化学");
        BookCombination   book1_2_1 = new Book("无机化学");
        BookCombination   book1_2_2 = new Book("有机化学");
        BookCombination once2 = new Once("哲学");
        BookCombination  twice2_1 = new Twice("Deep");
        BookCombination   book2_1_1 = new Book("Dark");

        once1.add(twice1_1);
         twice1_1.add(book1_1_1);
         twice1_1.add(book1_1_2);
        once1.add(twice1_2);
         twice1_2.add(book1_2_1);
         twice1_2.add(book1_2_2);
       once1.add(once2);
        once2.add(twice2_1);
        twice2_1.add(once1);
        once2.add(book2_1_1);
        once1.description();
    }
}
