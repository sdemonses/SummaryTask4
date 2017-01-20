package ua.nure.biblyi.SummaryTask4.core.filters;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.Type;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Dimasyk on 20.01.2017.
 */
public class HotelFilter extends PersonFilter {
    private final static Logger LOG = Logger.getLogger(HotelFilter.class);
    private int stars;
    public HotelFilter(Type type, int from, int to, int person, int stars) {
        super(type, from, to, person);
        this.stars = stars;
    }

    @Override
    public List<Tour> filter(List<Tour> list) {
        LOG.debug("HotelFilter.filter start");
        super.filter(list);
        LOG.trace("Count stars :" + stars);
        Iterator<Tour> it = list.iterator();
        while (it.hasNext()){
            if(it.next().getHotel().getCountOfStars() != stars){
                it.remove();
            }
        }
        LOG.debug("HotelFilter.filter finish");
        return list;

    }
}
