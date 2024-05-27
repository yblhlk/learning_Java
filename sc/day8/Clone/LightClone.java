package Code.Clone;

public class LightClone implements Cloneable{
    private String name;
    private int age;

    public LightClone() {
    }

    public LightClone(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "DeepClone{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    protected LightClone clone() throws CloneNotSupportedException {
        return (LightClone) super.clone(); //最终使用的是Object中的本地方法
    }
}
