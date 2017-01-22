package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.TourDAO;
import ua.nure.biblyi.SummaryTask4.db.Status;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.DuplicateException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Change status command.
 *
 * @author D.Biblyi
 *
 */
public class StatusCommand extends Command {
    private final static Logger LOG = Logger.getLogger(StatusCommand.class);
    private static final long serialVersionUID = -3809760114807157712L;

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("StatusCommand.execute start");
        String result = null;
        if (TypeHttpRequest.GET == type) {
            result = Path.PAGE_INDEX;
        } else {
            result = doPost(httpServletRequest, httpServletResponse);
        }
        LOG.debug("StatusCommand.execute finish");

        return result;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws DAOException, DuplicateException {
        LOG.debug("StatusCommand.doPost start");
        String command = httpServletRequest.getParameter("com");
        LOG.trace("Command -->" + command);
        Long id = Long.parseLong(httpServletRequest.getParameter("id"));
        TourDAO tourDAO = new TourDAO();
        Tour tour;

        tour = tourDAO.getByPK(id);

        switch (command) {
                case "empty":
                    tour.setStatus(Status.EMPTY.ordinal());
                    break;
                case "hot":
                    tour.setStatus(Status.HOT.ordinal());
                    break;
                case "canceled":
                    tour.setStatus(Status.CANCELED.ordinal());
                    break;
                case "paid":
                    tour.setStatus(Status.PAID.ordinal());
                    break;
                case "register":
                    tour.setStatus(Status.REGISTER.ordinal());
                    break;
            }

        tourDAO.update(tour);
        LOG.debug("StatusCommand.doPost finish");

        String uri = httpServletRequest.getHeader("referer");

        int i = uri.indexOf("controller");
        LOG.debug("LanguageCommand.doPost finish");
        if (i == -1) {
            return Path.PAGE_INDEX;
        } else {
            return uri.substring(i);
        }
    }
}
