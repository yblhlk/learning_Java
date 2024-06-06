package AbstractCombination;

// 树枝构件
public class Once extends BookCombination{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Once(String name) {
        this.name = name;
    }

    @Override
    public void description() {
        System.out.println("【一级目录】 ： " + name);
        super.description();
    }

}
