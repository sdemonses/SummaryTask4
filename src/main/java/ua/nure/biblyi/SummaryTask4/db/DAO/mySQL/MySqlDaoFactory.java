package ua.nure.biblyi.SummaryTask4.db.DAO.mySQL;

import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.DaoFactory;
import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.GenericDao;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by dmitry on 14.01.17.
 */
public class MySqlDaoFactory implements DaoFactory {

    private Map<Class, DaoCreator> creators;

    @Override
    public Connection getContext() throws DAOException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("", "", "");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return  connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws DAOException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new DAOException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }


}
