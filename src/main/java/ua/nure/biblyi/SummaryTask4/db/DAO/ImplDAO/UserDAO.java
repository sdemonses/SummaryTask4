package ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.AbstractJDBCDao;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dmitry on 14.01.17.
 */
public class UserDAO extends AbstractJDBCDao<User, Integer> {

    private static final Logger LOG = Logger.getLogger(UserDAO.class);

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM objects";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO objects (first_name, email, last_name, login, password, role_id) \n" +
                "VALUES (?, ?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE objects \n" +
                "SET first_name = ?, email = ? last_name  = ?, login = ?, password = ?, role_id = ? \n" +
                "WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM objects WHERE id= ?";
    }

    @Override
    protected User parseResultSet(ResultSet rs) throws DAOException {
        LOG.trace("UserDAO.parseResultSet start");
        User object = new User();
        try {
            object.setId(rs.getInt("id"));
            object.setLogin(rs.getString("login"));
            object.setEmail(rs.getString("email"));
            object.setPassword(rs.getString("password"));
            object.setFirstName(rs.getString("first_name"));
            object.setLastName(rs.getString("last_name"));
            object.setRoleId(rs.getInt("role_id"));
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_GET_INFO, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_GET_INFO, e);
        }
        LOG.trace("UserDAO.parseResultSet finish");
        return object;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws DAOException {
        LOG.trace("UserDAO.prepareStatementForInsert start");
        try {
            int k = 0;
            statement.setString(++k, object.getFirstName());
            statement.setString(++k, object.getEmail());
            statement.setString(++k, object.getLastName());
            statement.setString(++k, object.getLogin());
            statement.setString(++k, object.getPassword());
            statement.setInt(++k, object.getRoleId());
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_SET_INFO);
            throw new DAOException(ErrorMessage.ERR_CANNOT_SET_INFO, e);
        }
        LOG.trace("UserDAO.prepareStatementForInsert finish");
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws DAOException {
        LOG.trace("UserDAO.prepareStatementForUpdate start");
        try {
            int k = 0;
            statement.setString(++k, object.getFirstName());
            statement.setString(++k, object.getEmail());
            statement.setString(++k, object.getLastName());
            statement.setString(++k, object.getLogin());
            statement.setString(++k, object.getPassword());
            statement.setInt(++k, object.getRoleId());
            statement.setLong(++k, object.getId());
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_SET_INFO);
            throw new DAOException(ErrorMessage.ERR_CANNOT_SET_INFO, e);
        }
        LOG.trace("UserDAO.prepareStatementForUpdate finish");
    }
}
