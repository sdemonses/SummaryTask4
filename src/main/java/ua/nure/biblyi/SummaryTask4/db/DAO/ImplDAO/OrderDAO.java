package ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.AbstractJDBCDao;
import ua.nure.biblyi.SummaryTask4.db.entity.Order;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation DAO for Order entity.
 *
 * @author D.Biblyi
 *
 */
public class OrderDAO extends AbstractJDBCDao<Order, Long> {
    private final static Logger LOG = Logger.getLogger(OrderDAO.class);

    private static final String SQL_SELECT_FOR_USER = "SELECT * FROM orders WHERE user_id = ?";


    @Override
    public String getSelectQuery() {
        return "SELECT * FROM orders";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO orders (user_id, tour_id, maxValueSale, step, status_id) \n" +
                "VALUES (?,?, ?, ?, ?)";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE orders \n" +
                "SET user_id = ?, tour_id = ?, maxValueSale = ?,step = ?, status_id = ? \n" +
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
            object.setSaleMax(rs.getInt("maxValueSale"));
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
            LOG.error(ErrorMessage.ERR_CANNOT_SET_INFO, e);
            throw new DAOException(ErrorMessage.ERR_CANNOT_SET_INFO, e);
        }
        LOG.debug("OrderDAO.prepareStatementCommon finish");
        return ++k;
    }


    /**
     * Return list of order for user
     * @param id users
     * @return list of founded order
     */
    public List<Order> getOrderList(Long id) throws DAOException {
        LOG.debug("OrderDAO.getTourList start");;
        List<Order> orderList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_FOR_USER);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                orderList.add(parseResultSet(rs));
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
        LOG.debug("OrderDAO.getTourList finish");
        return orderList;
    }
}
