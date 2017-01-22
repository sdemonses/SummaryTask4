package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.UserDAO;
import ua.nure.biblyi.SummaryTask4.db.UserStatus;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Ban/ unBan user command
 *
 * @author D.Biblyi
 *
 */
public class UserCommand extends Command {

    private static final Logger LOG = Logger.getLogger(UserCommand.class);
    private static final long serialVersionUID = -1441228465286161639L;

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("UserCommand.execute start");

        String result;
        if (type == TypeHttpRequest.POST) {
            result = doPost(httpServletRequest, httpServletResponse);
        } else {
            result = doGet(httpServletRequest, httpServletResponse);
        }
        LOG.debug("UserCommand.execute finish");
        return result;
    }

    private String doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.debug("UserCommand.doPost start");
        UserDAO userDAO = new UserDAO();
        List<User> userList = null;
        try {
            userList = userDAO.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        LOG.trace("List users --> " + userList
        );
        httpServletRequest.setAttribute("users", userList);
        LOG.debug("UserCommand.doPost finish");
        return Path.PAGE_USERS;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.debug("UserCommand.doPost start");
        Long id = Long.parseLong(httpServletRequest.getParameter("id"));
        UserDAO userDAO = new UserDAO();
        UserStatus userStatus = UserStatus.valueOf(httpServletRequest.getParameter("com").toUpperCase());
        LOG.trace("User id --> " + id + "New userStatus --> " + userStatus);
        User user = null;
        try {
            user = userDAO.getByPK(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        LOG.trace("Updated user" + user);
        user.setUserStatus(userStatus.ordinal());
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        LOG.debug("UserCommand.doPost finish");
        return Path.PAGE_USERS_POST;
    }
}
