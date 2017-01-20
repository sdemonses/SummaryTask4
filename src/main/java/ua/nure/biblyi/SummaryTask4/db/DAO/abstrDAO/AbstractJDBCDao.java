package ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.DBManager;
import ua.nure.biblyi.SummaryTask4.db.entity.Entity;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 14.01.17.
 */
public abstract class AbstractJDBCDao<T extends Entity, PK extends Long> implements GenericDao<T, PK> {

    private static final Logger LOG = Logger.getLogger(AbstractJDBCDao.class);

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
    protected abstract T parseResultSet(ResultSet rs) throws DAOException;

    /**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     */
    protected abstract int prepareStatementCommon(PreparedStatement statement, T object) throws DAOException;


    @Override
    public T insert(T object) throws DAOException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = getCreateQuery();
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            prepareStatementCommon(pstmt, object);
            int count = pstmt.executeUpdate();
            if (count == 1) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    object.setId(rs.getLong(1));
                } else {
                    throw new DAOException(ErrorMessage.EMPTY_RESULT_SET);
                }
            } else {
                throw new DAOException("On persist modify more then 1 record: " + count);
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            LOG.error(ErrorMessage.ERR_CANNOT_INSERT_ENTRY, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_INSERT_ENTRY, e);
        } finally {
            close(con);
            close(pstmt);
            close(rs);
        }
        return object;
    }

    @Override
    public T getByPK(PK key) throws DAOException {
        T object;
        String sql = getSelectQuery();
        sql += " WHERE id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setLong(1, key);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                object = parseResultSet(rs);
            } else {
                throw new DAOException((ErrorMessage.ERR_CANNOT_FIND_ENTRY));
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

    @Override
    public void update(T object) throws DAOException {
        String sql = getUpdateQuery();
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sql);
            int pos = prepareStatementCommon(pstmt, object);
            pstmt.setLong(pos, object.getId());
            int count = pstmt.executeUpdate();
            if (count != 1) {
                LOG.error(ErrorMessage.ERR_CANNOT_UPDATE_ENTRY + ErrorMessage.COUNT_CHANGE_LINE);
                throw new DAOException(ErrorMessage.ERR_CANNOT_UPDATE_ENTRY + ErrorMessage.COUNT_CHANGE_LINE + count);
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            LOG.error(ErrorMessage.ERR_CANNOT_UPDATE_ENTRY, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_UPDATE_ENTRY, e);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    @Override
    public void delete(T object) throws DAOException {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = getDeleteQuery();
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setObject(1, object.getId());
            int count = pstmt.executeUpdate();
            if (count != 1) {
                LOG.error(ErrorMessage.ERR_CANNOT_DELETE_ENTRY + ErrorMessage.COUNT_CHANGE_LINE + count);
                throw new DAOException(ErrorMessage.ERR_CANNOT_DELETE_ENTRY + ErrorMessage.COUNT_CHANGE_LINE + count);
            }
            con.commit();
        } catch (SQLException e) {
            rollback(con);
            LOG.error(ErrorMessage.ERR_CANNOT_DELETE_ENTRY, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_DELETE_ENTRY, e);
        }finally {
            close(con);
            close(pstmt);
        }
    }

    @Override
    public List<T> getAll() throws DAOException {
        List<T> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = getSelectQuery();
        try {
            con = DBManager.getInstance().getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                list.add(parseResultSet(rs));
            }
        } catch (SQLException e) {
            rollback(con);
            LOG.error(ErrorMessage.ERR_CANNOT_OBTAIN_ENTRY, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_OBTAIN_ENTRY, e);
        } finally {
            close(con);
            close(stmt);
            close(rs);
        }
        return list;
    }

    /**
     * Closes a connection.
     *
     * @param closeable AutoCloseable to be closed.
     */
    protected void close(AutoCloseable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                LOG.error(ErrorMessage.ERR_CANNOT_CLOSE_CONNECTION + closeable.getClass().getSimpleName());
            }
        }
    }

    /**
     * Rollbacks a connection.
     *
     * @param con Connection to be rollbacked.
     */
    protected void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error(ErrorMessage.ERR_CANNOT_ROLLBACK_TRANSACTION, ex);
            }
        }
    }
}
