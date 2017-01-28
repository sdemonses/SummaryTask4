package ua.nure.biblyi.SummaryTask4.web.command.profile;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.OrderDAO;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.TourDAO;
import ua.nure.biblyi.SummaryTask4.db.Status;
import ua.nure.biblyi.SummaryTask4.db.entity.Order;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;
import ua.nure.biblyi.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Manage tours command.
 *
 * @author D.Biblyi
 *
 */
public class ManagerCommand extends Command {
    private final static Logger LOG = Logger.getLogger(ManagerCommand.class);
    private static final long serialVersionUID = -6446169660535124408L;

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("ManagerCommand.execute start");
        String result = null;
        if (TypeHttpRequest.POST == type) {
            result = Path.PAGE_ERROR_PAGE;
        } else {
            result = doGet(httpServletRequest, httpServletResponse);
        }
        LOG.debug("ManagerCommand.execute finish");
        return result;
    }

    private String doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws DAOException {
        LOG.debug("ManagerCommand.doGet start");

        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderList = orderDAO.getAll();
        httpServletRequest.setAttribute("orders", orderList);
        LOG.debug("ManagerCommand.doGet finish");

        return Path.PAGE_MANAGER_PROFILE;
    }
}
