package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.TourDAO;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Manage sale for order
 *
 * @author D.Biblyi
 */
public class SaleCommand extends Command {
    private final static Logger LOG = Logger.getLogger(SaleCommand.class);
    private static final long serialVersionUID = -5212600582754715171L;

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("SaleCommand.execute start");
        String result = null;

        if (TypeHttpRequest.POST == type) {
            result = doPost(httpServletRequest, httpServletResponse);
        } else {
            result = doGet(httpServletRequest, httpServletResponse);
        }
        LOG.debug("SaleCommand.execute finish");
        return result;
    }

    private String doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws DAOException {
        LOG.debug("SaleCommand.doGet start");
        Long id = Long.parseLong(httpServletRequest.getParameter("id"));
        TourDAO tourDAO = new TourDAO();
        Tour tour = null;

        tour = tourDAO.getByPK(id);

        httpServletRequest.setAttribute("tour", tour);
        LOG.debug("SaleCommand.doGet finish");
        return Path.PAGE_SALE;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws DAOException {
        LOG.debug("SaleCommand.doPost start");
        int maxValue = Integer.parseInt(httpServletRequest.getParameter("maxValue"));
        int step = Integer.parseInt(httpServletRequest.getParameter("step"));
        Long id = Long.parseLong(httpServletRequest.getParameter("id"));

        TourDAO tourDAO = new TourDAO();
        Tour tour = null;
        tour = tourDAO.getByPK(id);
        LOG.debug("SaleCommand.doPost finish");
        return "";
    }
}
