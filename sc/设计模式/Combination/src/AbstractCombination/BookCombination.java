package AbstractCombination;

import java.util.ArrayList;
import java.util.List;

// 抽象父类构件
public abstract class BookCombination {
    protected List<BookCombination> list = new ArrayList<>(); //要创建好


    public void add(BookCombination bc) {
        this.list.add(bc);
    }

    public void remove(BookCombination bc) {
        list.remove(bc);
    }

    public void description(){
        for(BookCombination l : list){
            l.description();
        }
    }
}
