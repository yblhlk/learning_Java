package Test;

import java.util.ArrayList;
import java.util.List;

public abstract class ZOOM {
    protected List<ZOOM> lz = new ArrayList<ZOOM>();

    public void add(ZOOM z){
        lz.add(z);
    }

    public void remove(ZOOM z){
        lz.remove(z);
    }

    public void description(){
        for(ZOOM z : lz){
            z.description();
        }
    }
}
