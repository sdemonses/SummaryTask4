package ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.AbstractJDBCDao;
import ua.nure.biblyi.SummaryTask4.db.entity.Hotel;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dimasyk on 19.01.2017.
 */
public class HotelDAO extends AbstractJDBCDao<Hotel, Long> {

    private final static Logger LOG = Logger.getLogger(HotelDAO.class);

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM hotels";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO hotels (city_id, name, stars, description) \n" +
                "VALUES (?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE hotels \n" +
                "SET city_id = ?, name = ?, stars = ?,description = ? \n" +
                "WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM hotels WHERE id= ?";
    }

    @Override
    protected Hotel parseResultSet(ResultSet rs) throws DAOException {
        LOG.debug("HotelDAO.parseResultSet start");
        Hotel hotel = new Hotel();
        try {
            hotel.setId(rs.getInt("id"));
            hotel.setCountOfStars(rs.getInt("stars"));
            hotel.setName(rs.getString("name"));
            hotel.setDescription(rs.getString("description"));
            Long id = rs.getLong("city_id");
            hotel.setCity(new CityDAO().getByPK(id));
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_GET_INFO, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_GET_INFO, e);
        }
        LOG.debug("HotelDAO.parseResultSet finish");
        return hotel;
    }

    @Override
    protected int prepareStatementCommon(PreparedStatement statement, Hotel object) throws DAOException {
        LOG.debug("HotelDAO.prepareStatementCommon start");
        int k = 0;
        try {
            statement.setLong(++k, object.getCity().getId());
            statement.setString(++k, object.getName());
            statement.setInt(++k, object.getCountOfStars());
            statement.setString(++k, object.getDescription());
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_SET_INFO);
            throw new DAOException(ErrorMessage.ERR_CANNOT_SET_INFO, e);
        }
        LOG.debug("HotelDAO.prepareStatementCommon finish");
        return ++k;
    }
}
