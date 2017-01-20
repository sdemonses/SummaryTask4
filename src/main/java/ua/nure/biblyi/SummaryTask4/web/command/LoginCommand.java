package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.UserDAO;
import ua.nure.biblyi.SummaryTask4.db.Role;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dmitry on 16.01.17.
 */
public class LoginCommand extends Command {

    private static final long serialVersionUID = -3577098499579800229L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("LoginCommand.execute start");

        String result = null;
        if (TypeHttpRequest.POST == type) {
            result = doPost(httpServletRequest, httpServletResponse);
        } else {
            result = Path.PAGE_SIGN_IN;
        }
        LOG.debug("LoginCommand.execute finish");
        return result;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AppException {
        LOG.debug("LoginCommand.doPost start");
        HttpSession httpSession = httpServletRequest.getSession();
        UserDAO userDAO = new UserDAO();
        String login = httpServletRequest.getParameter("login");

        LOG.trace("Request parameter: loging --> " + login);
        String password = httpServletRequest.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        User user = userDAO.getByLogin(login);
        LOG.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            throw new AppException("Cannot find user with such login/password");
        }

        Role userRole = user.getRole();
        LOG.trace("userRole --> " + userRole);
        httpSession.setAttribute("user", user);
        httpSession.setAttribute("userRole", userRole);

        LOG.debug("LoginCommand.doPost finish");

        return Path.PAGE_INDEX;
    }
}
