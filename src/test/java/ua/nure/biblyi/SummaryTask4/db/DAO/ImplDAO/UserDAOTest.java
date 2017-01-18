package ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;

/**
 * Created by dmitry on 16.01.17.
 */
public class UserDAOTest {
     static UserDAO userDAO;
    @Before
     static void initialize(){
        userDAO = new UserDAO();
    }

    @Test
    public void insert() throws DAOException {
        User user = new User();
        user.setFirstName("asd");
        user.setLastName("asd");
        user.setRoleId(3);
        user.setEmail("asd");
        user.setLogin("asd");
        User user1 = userDAO.insert(user);
        Assert.assertNotNull(user1);
    }

    @Test
    public void getByPK() {

    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {

    }

    @Test
    public void getAll() {

    }

}