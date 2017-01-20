package ua.nure.biblyi.SummaryTask4.core.filters;

import ua.nure.biblyi.SummaryTask4.db.Type;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Dimasyk on 20.01.2017.
 */
public class TypeFilter implements Filter<Tour> {

    private Type type;

    public TypeFilter(Type type) {
        this.type = type;
    }

    @Override
    public List<Tour> filter(List<Tour> list) {
        Iterator<Tour> it = list.iterator();
        while (it.hasNext()){
            if(it.next().getType() != type){
                it.remove();
            }
        }
        return list;
    }
}
