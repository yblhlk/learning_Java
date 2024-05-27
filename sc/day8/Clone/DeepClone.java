package Code.Clone;

//实现CloneAble接口，进行授权
public class DeepClone implements Cloneable{
    public class InnerClass implements Cloneable {
        public String class_name;
        public int id;

        public InnerClass() {
        }

        public InnerClass(String class_name, int id) {
            this.class_name = class_name;
            this.id = id;
        }

        @Override
        public String toString() {
            return "InnerClass{" +
                    "class_name='" + class_name + '\'' +
                    ", id=" + id +
                    '}';
        }

        protected InnerClass clone() throws CloneNotSupportedException{
            return (InnerClass) super.clone();
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
    private String name;
    private int age;
    public InnerClass ic;

    public DeepClone(String name, int age, InnerClass ic) {
        this.name = name;
        this.age = age;
        this.ic = ic;
    }

    public DeepClone() {
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

    public InnerClass getIc() {
        return ic;
    }

    public void setIc(InnerClass ic) {
        this.ic = ic;
    }

    @Override
    public String toString() {
        return "DeepClone{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", ic=" + ic +
                '}';
    }
    // 深拷贝
    protected DeepClone clone() throws CloneNotSupportedException{
        DeepClone newdc = (DeepClone) super.clone(); //和浅拷贝一样,调用父类的clone函数来新建一个对象
        newdc.ic = this.getIc().clone(); //对新建对象的引用类型成员要调用其clone函数
        return newdc; //返回新建对象
    }
//    //浅拷贝
//    protected DeepClone clone() throws CloneNotSupportedException {
//        return (DeepClone) super.clone(); //最终使用的是Object中的本地方法
//    }
}
