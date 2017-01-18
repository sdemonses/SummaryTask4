package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.core.EmailValidation;
import ua.nure.biblyi.SummaryTask4.core.LoginValidation;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.UserDAO;
import ua.nure.biblyi.SummaryTask4.db.Role;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;
import ua.nure.biblyi.SummaryTask4.exception.ValidationExceptiom;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by dmitry on 17.01.17.
 */
public class SignUpCommand extends Command {
    private static final long serialVersionUID = 789638107905236244L;
    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("SignUpCommand.execute start");

        String result = null;

        if (TypeHttpRequest.POST == type) {
            result = doPost(httpServletRequest, httpServletResponse);
        }else{
            result = Path.PAGE_SIGN_UP;
        }
        LOG.debug("SignUpCommand.execute finish");
        return result;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.debug("SignUpCommand.doPost start");
        String result;
        HttpSession httpSession = httpServletRequest.getSession();
        String login = httpServletRequest.getParameter("login");
        LOG.trace("Request parameter: login --> " + login);

        String password = httpServletRequest.getParameter("password");
        String repeatPassword = httpServletRequest.getParameter("repeatPassword");

        LoginValidation loginValidation = new LoginValidation();

        try {
            loginValidation.validate(login);
        } catch (ValidationExceptiom validationExceptiom) {
            validationExceptiom.printStackTrace();
        }

        String email = httpServletRequest.getParameter("email");

        EmailValidation emailValidation = new EmailValidation();
        try {
            emailValidation.validate(email);
        } catch (ValidationExceptiom validationExceptiom) {
            validationExceptiom.printStackTrace();
        }

        if(!password.equals(repeatPassword)){

        }
        UserDAO userDAO = new UserDAO();

        String firstName = httpServletRequest.getParameter("firstName");
        String lastName = httpServletRequest.getParameter("lastName");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setEmail(email);
        user.setRoleId(1);
        user.setPassword(password);

        LOG.trace("Insert in DB: user --> " + user);
        try {
            user = userDAO.insert(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        httpSession.setAttribute("user", user);

        Role userRole = Role.getRole(user);
        LOG.trace("userRole --> " + userRole);
        httpSession.setAttribute("userRole", userRole);
        LOG.debug("SignUpCommand.doPost finish");
        return Path.PAGE_INDEX;
    }
}
