package ua.nure.biblyi.SummaryTask4.core.filters;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.Status;
import ua.nure.biblyi.SummaryTask4.db.Type;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dmitry on 23.01.17.
 */
public class HotFilter extends HotelFilter {

    private static final Logger LOG = Logger.getLogger(HotFilter.class);
    private Status status;
    public HotFilter(Type type, int from, int to, int person, int stars, Status status) {
        super(type, from, to, person, stars);
        this.status = status;
    }

    @Override
    public List<Tour> filter(List<Tour> list) {
        LOG.debug("HotFilter.filter start");
        if (status!=null) {
            Iterator<Tour> it = list.iterator();
            while (it.hasNext()){
                if(it.next().getStatus() != status){
                    it.remove();
                }
            }
        }
        LOG.debug("HotFilter.filter finish");
        return list;
    }
}
