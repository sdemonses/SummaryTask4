package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.TourDAO;
import ua.nure.biblyi.SummaryTask4.db.Status;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Dimasyk on 19.01.2017.
 */
public class TourCommand extends Command {
    private final static Logger LOG = Logger.getLogger(TourCommand.class);

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("TourCommand.execute start");
        String result;
        if (type == TypeHttpRequest.POST) {
            result = doPost(httpServletRequest, httpServletResponse);
        } else {
            result = doGet(httpServletRequest, httpServletResponse);
        }
        LOG.debug("TourCommand.execute finish");
        return result;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return null;
    }

    private String doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.debug("TourCommand.doGet start");
        TourDAO tourDAO = new TourDAO();
        List<Tour> tourList = null;
        try {
            tourList  = tourDAO.getTours(Status.HOT);
            tourList.addAll(tourDAO.getTours(Status.EMPTY));
        } catch (DAOException e) {
            e.printStackTrace();
        }
        LOG.trace("Tours list" + tourList);
        httpServletRequest.setAttribute("tours", tourList);
        LOG.debug("TourCommand.doGet finish");
        return Path.PAGE_TOURS;
    }
}
