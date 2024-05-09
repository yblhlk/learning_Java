package com.Homework2;

public class Test {
    public static void main(String[] args) {
        PingPongCoach ppc = new PingPongCoach("刘国梁", 50);
        PingPongPlayer ppp = new PingPongPlayer("马龙", 28);
        BadmintonCoach bc = new BadmintonCoach("李志锋", 52);
        BadmintonPlayer bp = new BadmintonPlayer("林丹", 30);

        ppc.eat();
        ppp.learnEnglish();
        bc.teach();
        bp.train();
    }
}
