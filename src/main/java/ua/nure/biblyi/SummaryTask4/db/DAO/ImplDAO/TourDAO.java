package ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.AbstractJDBCDao;
import ua.nure.biblyi.SummaryTask4.db.Status;
import ua.nure.biblyi.SummaryTask4.db.Type;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TourDAO extends AbstractJDBCDao<Tour, Long> {

    private static final String SQL_SELECT_FOR_USER = "SELECT * FROM tours WHERE user_id = ?";

    private static final String SQL_SELECT_TOUR_BY_STATUS = "SELECT * FROM tours WHERE status_id = ?";

    private static final Logger LOG = Logger.getLogger(TourDAO.class);

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM tours";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO tours (name, duration,  hotel_id, type_id, status_id, cost, person, user_id) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?,?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE tours \n" +
                "SET name = ?, duration = ?, hotel_id  = ?,  type_id = ?, status_id = ?, cost = ?, person = ?, user_id = ? \n" +
                "WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM tours WHERE id= ?";
    }

    @Override
    protected Tour parseResultSet(ResultSet rs) throws DAOException {
        LOG.trace("TourDAO.parseResultSet start");
        Tour object = new Tour();
        try {
            object.setId(rs.getInt("id"));
            object.setCost(rs.getInt("cost"));

            Long hotelId = rs.getLong("hotel_id");
            object.setHotel(new HotelDAO().getByPK(hotelId));

            Long userId = rs.getLong("user_id");
            if(userId!=0){
                object.setUser(new UserDAO().getByPK(userId));
            }


            object.setDuration(rs.getInt("duration"));
            object.setName(rs.getString("name"));
            object.setPerson(rs.getInt("person"));
            object.setStatus(rs.getInt("status_id"));
            object.setType(rs.getInt("type_id"));
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_GET_INFO, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_GET_INFO, e);
        }
        LOG.trace("TourDAO.parseResultSet finish");
        return object;
    }

    @Override
    protected int prepareStatementCommon(PreparedStatement statement, Tour object) throws DAOException {
        LOG.debug("TourDAO.prepareStatementCommon start");
        int k = 0;
        try {
            statement.setString(++k, object.getName());
            statement.setLong(++k, object.getDuration());
            statement.setLong(++k, object.getHotel().getId());
            statement.setInt(++k, object.getType().ordinal());
            statement.setInt(++k, object.getStatus().ordinal());
            statement.setInt(++k, object.getCost());
            statement.setInt(++k, object.getPerson());
            if (object.getUser() == null) {
                statement.setObject(++k, null);
            } else {
                statement.setLong(++k, object.getUser().getId());
            }
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_SET_INFO);
            throw new DAOException(ErrorMessage.ERR_CANNOT_SET_INFO, e);
        }
        LOG.debug("TourDAO.prepareStatementCommon finish");
        return ++k;
    }


    public List<Tour> getTours(Status status) throws DAOException {
        LOG.debug("TourDAO.getTours start");
        LOG.trace("Status id-- >"+ status.ordinal());
        return getTourList(status.ordinal(), SQL_SELECT_TOUR_BY_STATUS);
    }

    public List<Tour> getTourForUser(User user) throws DAOException {
        return getTourList(user.getId(), SQL_SELECT_FOR_USER);
    }


    private List<Tour> getTourList(long id, String sql) throws DAOException {
        LOG.debug("TourDAO.getTourList start");
        List<Tour> tourList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tourList.add(parseResultSet(rs));
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            LOG.error(ErrorMessage.ERR_CANNOT_OBTAIN_ENTRY, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_OBTAIN_ENTRY, e);
        } finally {
            close(con);
            close(pstmt);
            close(rs);
        }
        LOG.debug("TourDAO.getTourList finish");
        return tourList;
    }

}
