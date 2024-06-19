package Test;

public class Animal extends ZOOM{
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void description(){
        System.out.println("    【动物】：" + name);
    }
}
