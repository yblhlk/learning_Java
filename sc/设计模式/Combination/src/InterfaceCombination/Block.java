package InterfaceCombination;

import java.util.ArrayList;
import java.util.List;

public class Block implements ZOOM{
    private String name;
    private List<ZOOM> zoomList = new ArrayList<>();

    public Block(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void add(ZOOM zoom){
        zoomList.add(zoom);
    }

    public void remove(ZOOM zoom){
        zoomList.remove(zoom);
    }
    static int time = 0;
    @Override
    public void description() {
        for(int i = 0; i < time; ++i)
            System.out.print("    ");
        System.out.println("【Block】：" + name);
        for(ZOOM z : zoomList){
            time++;
            z.description();
            time--;
        }
    }
}
