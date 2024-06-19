package AbstractCombination;

// 树枝构件
public class Twice extends BookCombination{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Twice(String name) {
        this.name = name;
    }

    public void add(BookCombination bc) {
        if(bc instanceof Once)
            System.out.println("不能在二级目录里收录一级目录。");
        else
            this.list.add(bc);
    }

    @Override
    public void description() {
        for (int i = 0; i < BookCombination.time; i++) {
            System.out.print("    ");
        }
        System.out.println("【二级目录】 ： " + name);
        super.description();
    }
}
