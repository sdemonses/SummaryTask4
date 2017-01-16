package ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO;

import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.AbstractJDBCDao;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by dmitry on 16.01.17.
 */
public class TourDAO extends AbstractJDBCDao<Tour, Integer> {
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
    protected Tour parseResultSet(ResultSet rs) throws DAOException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Tour object) throws DAOException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Tour object) throws DAOException {

    }
}
