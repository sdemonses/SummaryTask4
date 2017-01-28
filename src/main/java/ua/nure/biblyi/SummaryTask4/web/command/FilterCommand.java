package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.core.filters.Filter;
import ua.nure.biblyi.SummaryTask4.core.filters.HotFilter;
import ua.nure.biblyi.SummaryTask4.core.filters.HotelFilter;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.TourDAO;
import ua.nure.biblyi.SummaryTask4.db.Status;
import ua.nure.biblyi.SummaryTask4.db.Type;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Filtration tour command
 *
 * @author D.Biblyi
 *
 */
public class FilterCommand extends Command {
    private final static Logger LOG = Logger.getLogger(FilterCommand.class);
    private static final long serialVersionUID = -2278394081728372322L;

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("FilterCommand.execute start");
        String result = null;
        if (TypeHttpRequest.GET == type) {
            result = doGet(httpServletRequest, httpServletResponse);
        } else {
            result = Path.PAGE_SIGN_IN;
        }
        LOG.debug("FilterCommand.execute finish");
        return result;
    }

    private String doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AppException {
        LOG.debug("FilterCommand.doGet start");
        TourDAO tourDAO = new TourDAO();
        List<Tour> tourList = null;
        String fromStr = httpServletRequest.getParameter("costFrom");
        String toStr = httpServletRequest.getParameter("costTo");
        String countPersonStr = httpServletRequest.getParameter("countPerson");
        String starsStr = httpServletRequest.getParameter("stars");
        String typeStr = httpServletRequest.getParameter("type").toUpperCase();
        String hotStr = httpServletRequest.getParameter("hot").toUpperCase();

        Type type = (typeStr.equals("ALL"))?null:Type.valueOf(typeStr);
        int from, to, countPerson, stars;
        Status hot;
        try {
            from = (fromStr.isEmpty())?0:Integer.parseInt(fromStr);
            to = (toStr.isEmpty())?0:Integer.parseInt(toStr);
            countPerson = (countPersonStr.equals("all"))?0:Integer.parseInt(countPersonStr);
            stars = (starsStr.equals("all"))?0:Integer.parseInt(starsStr);
            hot = (hotStr.equals("ALL"))?null:Status.valueOf(hotStr);
        } catch (NumberFormatException e) {
            LOG.error("Invalid data", e);
            httpServletRequest.setAttribute("path", Path.PAGE_TOURS);
            throw new AppException(e.getMessage());
        }


        tourList = tourDAO.getTours(Status.HOT);
            tourList.addAll(tourDAO.getTours(Status.EMPTY));

        Filter<Tour> filter = new HotFilter(type, from, to, countPerson, stars, hot);
        LOG.trace(filter.filter(tourList));
        httpServletRequest.setAttribute("tours", tourList);

        LOG.debug("FilterCommand.doGet finish");
        return Path.PAGE_TOURS;
    }
}
