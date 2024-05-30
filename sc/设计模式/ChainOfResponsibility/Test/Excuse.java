package Test;
// 假条类
public class Excuse {
    private String name;
    private String cause;
    private int leave_days;

    public Excuse() {
    }

    public Excuse(String name, String cause, int leave_days) {
        this.name = name;
        this.cause = cause;
        this.leave_days = leave_days;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getLeave_days() {
        return leave_days;
    }

    public void setLeave_days(int leave_days) {
        this.leave_days = leave_days;
    }

    @Override
    public String toString() {
        return "Excuse{" +
                "name='" + name + '\'' +
                ", cause='" + cause + '\'' +
                ", leave_days='" + leave_days + '\'' +
                '}';
    }
}
