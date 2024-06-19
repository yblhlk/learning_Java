package Test;

public class Block extends ZOOM{
    private String name;

    public Block(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void description() {
        System.out.println("【区域】：" + name);
        super.description();
    }
}
