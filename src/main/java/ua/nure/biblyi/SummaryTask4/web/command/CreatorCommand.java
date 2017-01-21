package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.HotelDAO;
import ua.nure.biblyi.SummaryTask4.db.Status;
import ua.nure.biblyi.SummaryTask4.db.Type;
import ua.nure.biblyi.SummaryTask4.db.entity.Hotel;
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
public class CreatorCommand extends Command {
    private final static Logger LOG = Logger.getLogger(CreatorCommand.class);

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("CreatorCommand.execute start");
        String result;
        if (TypeHttpRequest.GET == type) {
            result = doGet(httpServletRequest, httpServletResponse);
        } else {
            result = doPost(httpServletRequest, httpServletResponse);
        }
        LOG.debug("CreatorCommand.execute finish");
        return result;

    }

    private String doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.debug("CreatorCommand.doGet start");
        HotelDAO hotelDAO = new HotelDAO();
        List<Hotel> hotelList = null;
        try {
            hotelList = hotelDAO.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        httpServletRequest.setAttribute("hotels", hotelList);
        LOG.debug("CreatorCommand.doGet finish");
        return Path.PAGE_CREATOR;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.debug("CreatorCommand.doPost start");
        String name = httpServletRequest.getParameter("name");
        String durationStr = httpServletRequest.getParameter("duration");
        String typeStr = httpServletRequest.getParameter("type");
        String costStr = httpServletRequest.getParameter("cost");
        String personStr = httpServletRequest.getParameter("person");
        String hotelStr = httpServletRequest.getParameter("hotel");
        int duration, cost, person;
        Long hotelId;

        duration = Integer.parseInt(durationStr);
        cost = Integer.parseInt(costStr);
        person = Integer.parseInt(personStr);
        hotelId = Long.parseLong(hotelStr);
        HotelDAO hotelDAO = new HotelDAO();
        Hotel hotel = null;
        try {
            hotel = hotelDAO.getByPK(hotelId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Tour tour = new Tour();
        tour.setStatus(Status.EMPTY.ordinal());
        tour.setCost(cost);
        tour.setDuration(duration);
        tour.setHotel(hotel);
        tour.setName(name);
        tour.setPerson(person);
        tour.setType(Type.valueOf(typeStr.toUpperCase()).ordinal());
        LOG.debug("CreatorCommand.doPost finish");
        return Path.PAGE_MANAGER_PROFILE_POST;
    }
}
