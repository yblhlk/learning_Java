import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lottery {
    public Users lottery(Users users){
        Users users1 = new Users();
        // 1. 计算概率区间 和 总概率
        ArrayList<Double> lo = new ArrayList<>();
        lo.add(0.0);
        double nowpro = 0.0; // 总概率
        for (int i = 0; i < users.size(); i++) {
            // a. 更新中奖概率，后插入中奖区间集合
            users.getuser(i).setProbability();
            nowpro += users.getuser(i).getProbability();
            lo.add(nowpro);
        }

        // 2. 抽奖
        // a. 要抽取的人数，为总人数的1/3；
        int people_const = users.size() / 3;
        System.out.println(">>>当前一共有" + users.size() + "名用户，所以本次幸运抽奖要抽取" + people_const + "名幸运用户。");
        // b. 抽取幸运用户，不重复。
        Random random = new Random();
        for (int i = 0; i < people_const; i++) {
            System.out.print("第" + (i + 1) + "名幸运用户为：");

            int comfirm = 1;
            do {
                double luck_number = random.nextInt((int)(nowpro*10000))/10000.00;
                for (int i1 = 0; i1 < users.size(); i1++) {
                    if(lo.get(i1+1) > luck_number)
                        for (int i2 = 0; i2 < users1.size(); i2++) {
                            if(users.getuser(i1).getUsername().equals(users1.getuser(i2).getUsername())){
                                comfirm = 1;
                               break;
                            }else{
                                comfirm = 0;
                                break;
                            }
                        }
                }
            } while(comfirm == 1);

        }


        return users1;
    }
}
