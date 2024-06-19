package InterfaceCombination;

public class Animal implements ZOOM {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal(String name) {
        this.name = name;
    }
    public void add(ZOOM zoom){}
    public void remove(ZOOM zoom){}
    @Override
    public void description() {
        for(int i = 0; i < Block.time; ++i)
            System.out.print("    ");
        System.out.println("【Animal】：" + name);
    }
}
