package ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.AbstractJDBCDao;
import ua.nure.biblyi.SummaryTask4.db.entity.Country;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation DAO for Country entity.
 *
 * @author D.Biblyi
 *
 */
public class CountryDAO extends AbstractJDBCDao<Country, Long> {
    private final static Logger LOG = Logger.getLogger(CountryDAO.class);

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM countries";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO countries (country) \n" +
                "VALUES (?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE countries \n" +
                "SET name = ?\n" +
                "WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM country WHERE id= ?";
    }

    @Override
    protected Country parseResultSet(ResultSet rs) throws DAOException {
        LOG.debug("CountryDAO.parseResultSet start");
        Country object = new Country();
        try {
            object.setId(rs.getInt("id"));
            object.setName(rs.getString("name"));
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_GET_INFO, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_GET_INFO, e);
        }
        LOG.debug("CountryDAO.parseResultSet finish");
        return object;
    }

    @Override
    protected int prepareStatementCommon(PreparedStatement statement, Country object) throws DAOException {
        LOG.debug("CountryDAO.prepareStatementCommon start");
        int k = 0;
        try {
            statement.setString(++k, object.getName());
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_SET_INFO);
            throw new DAOException(ErrorMessage.ERR_CANNOT_SET_INFO, e);
        }
        LOG.debug("CountryDAO.prepareStatementCommon finish");
        return ++k;
    }
}
