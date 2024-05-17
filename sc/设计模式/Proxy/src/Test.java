public class Test {
    public static void main(String[] args) {
        Boss b = new Boss();
        BoosWife bw = new BoosWife();

        Proxy pa = new Proxy(b);
        Proxy pb = new Proxy(bw);
        pa.proxyMeeting();
        pb.proxyPayBonuses();
    }
}
