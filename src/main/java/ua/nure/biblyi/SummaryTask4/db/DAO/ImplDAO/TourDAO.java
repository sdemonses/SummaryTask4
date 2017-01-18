package ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.AbstractJDBCDao;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TourDAO extends AbstractJDBCDao<Tour, Integer> {

    private static final Logger LOG = Logger.getLogger(TourDAO.class);

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM tours";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO tours (name, country_id, hotel_id, type_id, status_id, cost, person) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE tours \n" +
                "SET name = ?, country_id = ? hotel_id  = ?, type_id = ?, status_id = ?, cost = ?, person = ? \n" +
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
            object.setCountryId(rs.getInt("country_id"));
            object.setHotelId(rs.getLong("hotel_id"));
            object.setDuration(rs.getInt("duration"));
            object.setName(rs.getString("name"));
            object.setPerson(rs.getInt("person"));
            object.setStatusId(rs.getInt("status_id"));
            object.setTypeId(rs.getInt("type_id"));
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
            statement.setLong(++k, object.getCountryId());
            statement.setLong(++k, object.getHotelId());
            statement.setInt(++k, object.getTypeId());
            statement.setInt(++k, object.getStatusId());
            statement.setInt(++k, object.getCost());
            statement.setInt(++k, object.getPerson());
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_SET_INFO);
            throw new DAOException(ErrorMessage.ERR_CANNOT_SET_INFO, e);
        }
        LOG.debug("TourDAO.prepareStatementCommon finish");
        return ++k;
    }
}
