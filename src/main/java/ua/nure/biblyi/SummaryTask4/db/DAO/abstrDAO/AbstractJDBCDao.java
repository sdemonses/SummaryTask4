package ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO;

import ua.nure.biblyi.SummaryTask4.db.entity.Entity;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by dmitry on 14.01.17.
 */
public abstract class AbstractJDBCDao<T extends Entity, PK extends Integer> implements GenericDao<T, PK> {

    private Connection connection;

    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();

    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();

    /**
     * Возвращает sql запрос для обновления записи.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Возвращает sql запрос для удаления записи из базы данных.
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Разбирает ResultSet и возвращает список объектов соответствующих содержимому ResultSet.
     */
    protected abstract List<T> parseResultSet(ResultSet rs) throws DAOException;

    /**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DAOException;

    /**
     * Устанавливает аргументы update запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws DAOException;

    @Override
    public T insert(T object) throws DAOException {
        T persistInstance;
        // Добавляем запись
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DAOException("On insert modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
        // Получаем только что вставленную запись
        sql = getSelectQuery() + " WHERE id = last_insert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new DAOException("Exception on findByPK new insert data.");
            }
            persistInstance = list.iterator().next();
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return persistInstance;
    }

    @Override
    public T getByPK(Integer key) throws DAOException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        if (list == null || list.size() == 0) {
            throw new DAOException("Record with PK = " + key + " not found.");
        }
        if (list.size() > 1) {
            throw new DAOException("Received more than one record.");
        }
        return list.iterator().next();
    }

    @Override
    public void update(T object) throws DAOException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object); // заполнение аргументов запроса оставим на совесть потомков
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DAOException("On update modify more then 1 record: " + count);
            }
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void delete(T object) throws DAOException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new DAOException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DAOException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<T> getAll() throws DAOException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DAOException(e);
        }
        return list;
    }
}
