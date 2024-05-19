import java.util.ArrayList;

public class Users {
    private ArrayList<User> users;

    public Users(){
       users  = new ArrayList<>();
    }

    public void adduser(User user) {
        users.add(user);
    }

    public void remove(String vip_number){
        int index = 0;
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getVip_number() == vip_number){
                index = i;
                break;
            }
            if(i == users.size() - 1){
                System.out.println(">>没有找到该用户。");
                return;
            }
        }
        users.remove(index);
    }

    public void printUsers(){
        System.out.println(">>输出所有用户信息：");
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).toString());
        }
    }

    public int size(){
        return users.size();
    }

    public User getuser(int i){
        return users.get(i);
    }
}
