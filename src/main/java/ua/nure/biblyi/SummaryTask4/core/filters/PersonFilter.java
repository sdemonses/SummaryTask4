package ua.nure.biblyi.SummaryTask4.core.filters;

import ua.nure.biblyi.SummaryTask4.db.Type;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Dimasyk on 20.01.2017.
 */
public class PersonFilter extends CostFilter {
    private int person;
    public PersonFilter(Type type, int from, int to, int person) {
        super(type, from, to);
        this.person = person;
    }

    @Override
    public List<Tour> filter(List<Tour> list) {
        super.filter(list);
        Iterator<Tour> it = list.iterator();
        while (it.hasNext()){
            if(it.next().getPerson()!= person){
                it.remove();
            }
        }
        return list;

    }

}
