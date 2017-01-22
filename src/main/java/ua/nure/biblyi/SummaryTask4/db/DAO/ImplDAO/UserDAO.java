package ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.AbstractJDBCDao;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation DAO for Tour entity.
 *
 * @author D.Biblyi
 *
 */
public class UserDAO extends AbstractJDBCDao<User, Long> {

    private static final Logger LOG = Logger.getLogger(UserDAO.class);

    private static final String SQL_SELECT_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

    @Override
    public String getSelectQuery() {
        return "SELECT * FROM users";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO users (first_name, email, last_name, login, password, role_id, userStatus_id) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE users \n" +
                "SET first_name = ?, email = ?, last_name  = ?, login = ?, password = ?, role_id = ?, userStatus_id = ? \n" +
                "WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM users WHERE id= ?";
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
            object.setRole(rs.getInt("role_id"));
            object.setUserStatus(rs.getInt("userStatus_id"));
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_GET_INFO, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_GET_INFO, e);
        }
        LOG.trace("UserDAO.parseResultSet finish");
        return object;
    }

    @Override
    protected int prepareStatementCommon(PreparedStatement statement, User object) throws DAOException {
        LOG.debug("UserDAO.prepareStatementCommon start");
        int k = 0;
        try {
            statement.setString(++k, object.getFirstName());
            statement.setString(++k, object.getEmail());
            statement.setString(++k, object.getLastName());
            statement.setString(++k, object.getLogin());
            statement.setString(++k, object.getPassword());
            statement.setInt(++k, object.getRole().ordinal());
            statement.setInt(++k, object.getUserStatus().ordinal());
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_SET_INFO);
            throw new DAOException(ErrorMessage.ERR_CANNOT_SET_INFO, e);
        }
        LOG.debug("UserDAO.prepareStatementCommon finish");
        return ++k;
    }

    /**
     *  Return record from database corresponding login
     * @param login finded user with
     * @return object  corresponding to login or null
     */

    public User getByLogin(String login) throws DAOException {
        User object = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                object = parseResultSet(rs);
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
        return object;
    }
}
