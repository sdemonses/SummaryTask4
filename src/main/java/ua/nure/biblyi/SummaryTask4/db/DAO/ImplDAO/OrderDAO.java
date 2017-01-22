package ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.AbstractJDBCDao;
import ua.nure.biblyi.SummaryTask4.db.entity.Order;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Dimasyk on 22.01.2017.
 */
public class OrderDAO extends AbstractJDBCDao<Order, Long> {
    private final static Logger LOG = Logger.getLogger(OrderDAO.class);
    @Override
    public String getSelectQuery() {
        return "SELECT * FROM orders";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO orders (user_id, tour_id, maxSale, step, status_id) \n" +
                "VALUES (?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE orders \n" +
                "SET user_id = ?, tour_id = ?, maxSale = ?,step = ?, status_id = ? \n" +
                "WHERE id = ?";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM tours WHERE id= ?";
    }

    @Override
    protected Order parseResultSet(ResultSet rs) throws DAOException {
        LOG.debug("OrderDAO.parseResultSet start");
        Order object = new Order();
        try {
            object.setId(rs.getInt("id"));

            Long userId = rs.getLong("user_id");
            if(userId!=0){
                object.setUser(new UserDAO().getByPK(userId));
            }

            Long toutId = rs.getLong("tour_id");
            if(userId!=0){
                object.setTour(new TourDAO().getByPK(toutId));
            }
            object.setSaleStep(rs.getInt("step"));
            object.setSaleMax(rs.getInt("maxValue"));
            object.setStatus(rs.getInt("status_id"));
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_GET_INFO, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_GET_INFO, e);
        }
        LOG.debug("OrderDAO.parseResultSet finish");
        return object;
    }

    @Override
    protected int prepareStatementCommon(PreparedStatement statement, Order object) throws DAOException {
        LOG.debug("OrderDAO.prepareStatementCommon start");
        int k = 0;
        try {
            statement.setLong(++k, object.getUser().getId());
            statement.setLong(++k, object.getTour().getId());
            statement.setInt(++k, object.getSaleMax());
            statement.setInt(++k, object.getSaleStep());
            statement.setInt(++k, object.getStatus().ordinal());
        } catch (SQLException e) {
            LOG.error(ErrorMessage.ERR_CANNOT_SET_INFO);
            throw new DAOException(ErrorMessage.ERR_CANNOT_SET_INFO, e);
        }
        LOG.debug("OrderDAO.prepareStatementCommon finish");
        return ++k;
    }
}
