package ua.nure.biblyi.SummaryTask4.core.filters;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.Type;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Dimasyk on 20.01.2017.
 */
public class TypeFilter implements Filter<Tour> {

    private static final Logger LOG = Logger.getLogger(TypeFilter.class);

    private Type type;

    public TypeFilter(Type type) {
        this.type = type;
    }

    @Override
    public List<Tour> filter(List<Tour> list) {
        LOG.debug("TypeFilter.filter start");
        LOG.trace("Type --> " + type);
        if (type != null) {
            Iterator<Tour> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().getType() != type) {
                    it.remove();
                }
            }
        }
        LOG.debug("TypeFilter.filter finish");
        return list;
    }
}
