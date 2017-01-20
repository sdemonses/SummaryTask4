package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.core.filters.Filter;
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
 * Created by Dimasyk on 20.01.2017.
 */
public class FilterCommand extends Command {
    private final static Logger LOG = Logger.getLogger(FilterCommand.class);

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

    private String doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.debug("FilterCommand.doGet start");
        TourDAO tourDAO = new TourDAO();
        List<Tour> tourList = null;
        String fromStr = httpServletRequest.getParameter("costFrom");
        String toStr = httpServletRequest.getParameter("costTo");
        String countPersonStr = httpServletRequest.getParameter("countPerson");
        String starsStr = httpServletRequest.getParameter("stars");
        String typeStr = httpServletRequest.getParameter("type").toUpperCase();

        Type type = (typeStr.equals("ALL"))?null:Type.valueOf(typeStr);
        int from, to, countPerson, stars;
        from = (fromStr.isEmpty())?0:Integer.parseInt(fromStr);
        to = (toStr.isEmpty())?0:Integer.parseInt(toStr);
        countPerson = (countPersonStr.equals("all"))?0:Integer.parseInt(countPersonStr);
        stars = (starsStr.equals("all"))?0:Integer.parseInt(starsStr);


        try {
            tourList = tourDAO.getTours(Status.HOT);
            tourList.addAll(tourDAO.getTours(Status.EMPTY));
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Filter<Tour> filter = new HotelFilter(type, from, to, countPerson, stars);
        LOG.trace(filter.filter(tourList));
        httpServletRequest.setAttribute("tours", tourList);

        LOG.debug("FilterCommand.doGet finish");
        return Path.PAGE_TOURS;
    }
}
