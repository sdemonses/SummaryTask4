package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.OrderDAO;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.TourDAO;
import ua.nure.biblyi.SummaryTask4.db.Status;
import ua.nure.biblyi.SummaryTask4.db.entity.Order;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.DuplicateException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dmitry on 22.01.17.
 */
public class OrderCommand extends Command {
    private static final long serialVersionUID = 4340090306153344178L;
    private static final Logger LOG = Logger.getLogger(OrderCommand.class);

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("OrderCommand.execute start");
        String result = null;

        if (TypeHttpRequest.POST == type) {
            result = doPost(httpServletRequest, httpServletResponse);
        } else {
            result = Path.PAGE_ERROR_PAGE;
        }
        LOG.debug("OrderCommand.execute finish");
        return result;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws DAOException, DuplicateException {
        LOG.debug("OrderCommand.doPost start");
        Long id = Long.parseLong(httpServletRequest.getParameter("id"));
        TourDAO tourDAO = new TourDAO();
        Tour tour = tourDAO.getByPK(id);
        LOG.trace("Selected tour -->" + tour);
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        Order order = new Order();
        order.setStatus(Status.REGISTER.ordinal());
        order.setUser(user);
        order.setTour(tour);
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.insert(order);

        return Path.PAGE_TOURS_POST;
    }
}
