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
        System.out.println("    【书籍】 ： " + name);
    }
}
