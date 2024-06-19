package InterfaceCombination;

public class Plant implements ZOOM{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Plant(String name) {
        this.name = name;
    }

    @Override
    public void description() {
        for(int i = 0; i < Block.time; ++i)
            System.out.print("    ");
        System.out.println("【plant】：" + name);
    }

    @Override
    public void add(ZOOM zoom) {

    }

    @Override
    public void remove(ZOOM zoom) {

    }
}
