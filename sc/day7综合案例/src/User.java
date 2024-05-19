public class User {
    private String username;
    private String vip_number; //用数字存储有缺陷，开头不能为0
    private int vip_lv;
    private String passwd;
    private int lock = 0;
    private double probability = 0.1;
    private int svip_integral = 0;


    public User(String username, String vip_number, int vip_lv, String passwd) {
        this.username = username;
        this.vip_number = vip_number;
        this.vip_lv = vip_lv;
        this.passwd = passwd;
    }

    public User() {
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability() {
        probability *= Math.pow(2, svip_integral/100);
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVip_number() {
        return vip_number;
    }

    public void setVip_number(String vip_number) {
        this.vip_number = vip_number;
    }

    public int getVip_lv() {
        return vip_lv;
    }

    public void setVip_lv(int vip_lv) {
        this.vip_lv = vip_lv;
    }

    public int getSvip_integral() {
        return svip_integral;
    }

    public void setSvip_integral(int svip_integral) {
        this.svip_integral = svip_integral;
    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }



    @Override
    public String toString() {
        return "User{" +
                "用户名='" + username + '\'' +
                ", 会员号='" + vip_number + '\'' +
                ", 密码='" + passwd + '\'' +
                ", 会员等级=" + (vip_lv == 1 ? "普通会员" : "高级会员") +
                ", 账号锁定=" + (lock == 0 ? "未锁定" : "锁定") +
                '}';
    }
}
