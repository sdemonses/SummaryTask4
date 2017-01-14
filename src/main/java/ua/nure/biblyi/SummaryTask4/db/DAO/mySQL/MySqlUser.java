package ua.nure.biblyi.SummaryTask4.db.DAO.mySQL;

import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.AbstractJDBCDao;
import ua.nure.biblyi.SummaryTask4.db.entity.Entity;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by dmitry on 14.01.17.
 */
public class MySqlUser extends AbstractJDBCDao {

    public MySqlUser(Connection connection) {
        super(connection);
    }

    @Override
    public String getSelectQuery() {
        return null;
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getDeleteQuery() {
        return null;
    }

    @Override
    protected List parseResultSet(ResultSet rs) throws DAOException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Entity object) throws DAOException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Entity object) throws DAOException {

    }

    @Override
    public Entity create() throws DAOException {
        return null;
    }

    @Override
    public Entity getByPK(Serializable key) throws DAOException {
        return null;
    }
}
