package ManagmentSystem;

public class TeacherIF {
    private String id;
    private String name;
    private int age;
    private String course;
    private double pay;


    public TeacherIF() {
    }

    public TeacherIF(String id, String name, int age, String course, double pay) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.pay = pay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    @Override
    public String toString() {
        return "TeacherIF{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", course='" + course + '\'' +
                ", pay=" + pay +
                '}';
    }
}
