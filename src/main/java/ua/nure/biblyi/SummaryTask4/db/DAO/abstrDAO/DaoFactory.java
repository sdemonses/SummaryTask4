package ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO;

import ua.nure.biblyi.SummaryTask4.exception.DAOException;

import java.sql.Connection;

/**
 * Created by dmitry on 14.01.17.
 */

/**
 * Фабрика объектов для работы с базой данных
 */
public interface DaoFactory {

    /**
     * Возвращает подключение к базе данных
     */
    public Connection getContext() throws DAOException;

    /**
     * Возвращает объект для управления персистентным состоянием объекта
     */
    public GenericDao getDao(Connection context, Class dtoClass) throws DAOException;

    public interface DaoCreator<Context> {
        public GenericDao create(Context context);
    }
}
