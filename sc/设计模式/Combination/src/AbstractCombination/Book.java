package AbstractCombination;

//叶子构件
public class Book extends BookCombination{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book (String name) {
        this.name = name;
    }


    public void description() {
        for (int i = 0; i < BookCombination.time; i++) {
            System.out.print("    ");
        }
        System.out.println("【书籍】 ： " + name);
    }
}
