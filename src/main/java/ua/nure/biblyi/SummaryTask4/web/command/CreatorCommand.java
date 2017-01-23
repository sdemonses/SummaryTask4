package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.HotelDAO;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.TourDAO;
import ua.nure.biblyi.SummaryTask4.db.Status;
import ua.nure.biblyi.SummaryTask4.db.Type;
import ua.nure.biblyi.SummaryTask4.db.entity.Hotel;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.DuplicateException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * create / edit tour command
 *
 * @author D.Biblyi
 */
public class CreatorCommand extends Command {
    private final static Logger LOG = Logger.getLogger(CreatorCommand.class);
    private static final long serialVersionUID = -7500041442348340185L;

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

    private String doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws DAOException {
        LOG.debug("CreatorCommand.doGet start");
        HotelDAO hotelDAO = new HotelDAO();
        String id = httpServletRequest.getParameter("id");

        LOG.trace("Tour id --> " + id);
        List<Hotel> hotelList = null;
        hotelList = hotelDAO.getAll();

        if (id != null) {
            TourDAO tourDAO = new TourDAO();
            Long idL = Long.parseLong(id);
            httpServletRequest.setAttribute("tour", tourDAO.getByPK(idL));

        }
        httpServletRequest.setAttribute("hotels", hotelList);
        httpServletRequest.setAttribute("types", Type.values());
        LOG.debug("CreatorCommand.doGet finish");
        return Path.PAGE_CREATOR;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AppException {
        LOG.debug("CreatorCommand.doPost start");
        String id = httpServletRequest.getParameter("id");

        LOG.trace("Id variable tour " + id);

        String name = httpServletRequest.getParameter("name");
        String durationStr = httpServletRequest.getParameter("duration");
        String typeStr = httpServletRequest.getParameter("type");
        String costStr = httpServletRequest.getParameter("cost");
        String personStr = httpServletRequest.getParameter("person");
        String hotelStr = httpServletRequest.getParameter("hotel");


        int duration, cost, person;
        Long hotelId;
        try {
            duration = Integer.parseInt(durationStr);
            cost = Integer.parseInt(costStr);
            person = Integer.parseInt(personStr);
            hotelId = Long.parseLong(hotelStr);
        } catch (NumberFormatException e) {
            LOG.error("Invalid data", e);
            httpServletRequest.setAttribute("path", Path.PAGE_CREATOR);
            throw new AppException(e.getMessage());
        }
        HotelDAO hotelDAO = new HotelDAO();
        Hotel hotel = null;

        hotel = hotelDAO.getByPK(hotelId);
        Tour tour = new Tour();
        tour.setStatus(Status.EMPTY.ordinal());
        tour.setCost(cost);
        tour.setDuration(duration);
        tour.setHotel(hotel);
        tour.setName(name);
        tour.setPerson(person);
        tour.setType(Type.valueOf(typeStr.toUpperCase()).ordinal());
        LOG.trace("Tour which inserted" + tour);
        TourDAO tourDAO = new TourDAO();

        if (id.isEmpty()) {
            tourDAO.insert(tour);
        } else {
            tour.setId(Long.parseLong(id));
            tourDAO.update(tour);
        }


        LOG.debug("CreatorCommand.doPost finish");
        return Path.PAGE_TOURS_POST;
    }
}
