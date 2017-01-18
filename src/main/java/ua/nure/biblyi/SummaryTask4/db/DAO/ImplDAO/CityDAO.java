package ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.AbstractJDBCDao;
import ua.nure.biblyi.SummaryTask4.db.entity.City;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dmitry on 16.01.17.
 */
public class CityDAO extends AbstractJDBCDao<City, Integer> {

    private static final Logger LOG = Logger.getLogger(CityDAO.class);

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM cities";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO cities (country_id, name_ru, name_en) \n" +
                "VALUES (?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE cities \n" +
                "SET country_id = ?, name_ru = ?, name_en = ?\n" +
                "WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM cities WHERE id= ?";
    }

    @Override
    protected City parseResultSet(ResultSet rs) throws DAOException {
        LOG.trace("CityDAO.parseResultSet start");
        City object = new City();
        try {
            object.setId(rs.getInt("id"));
            object.setName(rs.getString("name"));
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_GET_INFO, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_GET_INFO, e);
        }
        LOG.trace("CityDAO.parseResultSet finish");
        return object;

    }

    @Override
    protected int prepareStatementCommon(PreparedStatement statement, City object) throws DAOException {
        LOG.debug("CityDAO.prepareStatementCommon start");
        int k = 0;
        try {
            statement.setString(++k, object.getName());
            statement.setLong(++k, object.getCountryId());
       } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_SET_INFO);
            throw new DAOException(ErrorMessage.ERR_CANNOT_SET_INFO, e);
        }
        LOG.debug("CityDAO.prepareStatementCommon finish");
        return ++k;
    }

}
