package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.UserDAO;
import ua.nure.biblyi.SummaryTask4.db.Role;
import ua.nure.biblyi.SummaryTask4.db.UserStatus;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Login command.
 *
 * @author D.Biblyi
 *
 */
public class LoginCommand extends Command {

    private static final long serialVersionUID = -3577098499579800229L;

    private static final Logger LOG = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("LoginCommand.execute start");

        String result = null;
        if (TypeHttpRequest.POST == type) {
            result = doPost(httpServletRequest, httpServletResponse, type);
        } else {
            result = Path.PAGE_SIGN_IN;
        }
        LOG.debug("LoginCommand.execute finish");
        return result;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("LoginCommand.doPost start");
        HttpSession httpSession = httpServletRequest.getSession();
        UserDAO userDAO = new UserDAO();
        String login = httpServletRequest.getParameter("login");

        LOG.trace("Request parameter: loging --> " + login);
        String password = httpServletRequest.getParameter("password");
        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            httpServletRequest.setAttribute("errorMessage" , "Login/password cannot be empty");
            type = TypeHttpRequest.GET;
            return Path.PAGE_SIGN_IN;
        }

        User user = userDAO.getByLogin(login);
        LOG.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            httpServletRequest.setAttribute("errorMessage" , "Cannot find user with such login/password");
            type = TypeHttpRequest.GET;
            return Path.PAGE_SIGN_IN;
        }

        if (user.getUserStatus() == UserStatus.BAN){
            return Path.PAGE_REDIRECT_POST;
        }

        Role userRole = user.getRole();
        LOG.trace("userRole --> " + userRole);
        httpSession.setAttribute("user", user);
        httpSession.setAttribute("userRole", userRole);

        LOG.debug("LoginCommand.doPost finish");

        return Path.PAGE_INDEX;
    }
}
