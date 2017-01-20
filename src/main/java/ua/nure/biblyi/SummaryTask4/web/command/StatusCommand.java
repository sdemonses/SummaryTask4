package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.TourDAO;
import ua.nure.biblyi.SummaryTask4.db.Role;
import ua.nure.biblyi.SummaryTask4.db.Status;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Dimasyk on 20.01.2017.
 */
public class StatusCommand extends Command {
    private final static Logger LOG = Logger.getLogger(StatusCommand.class);

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

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.debug("StatusCommand.doPost start");
        String command = httpServletRequest.getParameter("com");
        LOG.trace("Command -->" + command);
        Long id = Long.parseLong(httpServletRequest.getParameter("id"));
        TourDAO tourDAO = new TourDAO();
        Tour tour;
        try {
            tour = tourDAO.getByPK(id);
        } catch (DAOException e) {
            throw new IllegalArgumentException();
        }
        Role role = (Role) httpServletRequest.getSession().getAttribute("userRole");
        LOG.trace("User role --> " + role);
        if (role == Role.CLIENT && command.equals("register")) {
            tour.setStatus(Status.REGISTER.ordinal());
            tour.setUser((User) httpServletRequest.getSession().getAttribute("user"));
        } else if(role == Role.ADMIN||role == Role.MANAGER){
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
            }
        }
        try {
            tourDAO.update(tour);
        } catch (DAOException e) {
            e.printStackTrace();
        }


        LOG.debug("StatusCommand.doPost finish");

        return "controller?command=tour";
    }
}
