package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
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
 * Created by Dimasyk on 21.01.2017.
 */
public class ManagerCommand extends Command {
    private final static Logger LOG = Logger.getLogger(ManagerCommand.class);
    private static final long serialVersionUID = -6446169660535124408L;

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("ManagerCommand.execute start");;
        String result = null;
        if (TypeHttpRequest.POST == type) {
            result = "";
        } else {
            result = doGet(httpServletRequest, httpServletResponse);
        }
        LOG.debug("ManagerCommand.execute finish");;
        return result;
    }

    private String doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.debug("ManagerCommand.doGet start");

        List<Tour> tours = null;
        TourDAO tourDAO = new TourDAO();
        try {
            tours = tourDAO.getTours(Status.REGISTER);
            tours.addAll(tourDAO.getTours(Status.PAID));
            tours.addAll(tourDAO.getTours(Status.CANCELED));
        } catch (DAOException e) {
            e.printStackTrace();
        }

        httpServletRequest.setAttribute("tours", tours);
        LOG.debug("ManagerCommand.doGet finish");

        return Path.PAGE_MANAGER_PROFILE;
    }
}
