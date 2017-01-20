package ua.nure.biblyi.SummaryTask4.core.filters;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.Type;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Dimasyk on 20.01.2017.
 */
public class CostFilter extends TypeFilter {

    private final static Logger LOG = Logger.getLogger(CostFilter.class);
    private int from;

    private int to;


    CostFilter(Type type, int from, int to) {
        super(type);
        this.from = from;
        this.to = to;
    }

    @Override
    public List<Tour> filter(List<Tour> list) {
        LOG.debug("CostFilter.filter start");
        super.filter(list);
        LOG.trace("Cost from -->" + from + " To -->" + to);
        Iterator<Tour> it = list.iterator();
        Tour tour;
        while (it.hasNext()) {
            tour = it.next();
            if (tour.getCost() > to || tour.getCost() < from) {
                it.remove();
            }
        }
        LOG.debug("CostFilter.filter finish");
        return list;
    }
}
