package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.core.validation.EmailValidation;
import ua.nure.biblyi.SummaryTask4.core.validation.FieldValidation;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.UserDAO;
import ua.nure.biblyi.SummaryTask4.db.Role;
import ua.nure.biblyi.SummaryTask4.db.UserStatus;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ValidationException;
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
        String firstName = httpServletRequest.getParameter("firstName");
        String lastName = httpServletRequest.getParameter("lastName");

        FieldValidation fieldValidation = new FieldValidation();

        try {
            fieldValidation.validate(login);
            fieldValidation.validate(firstName);
            fieldValidation.validate(lastName);
        } catch (ValidationException validationException) {
            validationException.printStackTrace();
        }

        String email = httpServletRequest.getParameter("email");

        EmailValidation emailValidation = new EmailValidation();
        try {
            emailValidation.validate(email);
        } catch (ValidationException validationException) {
            validationException.printStackTrace();
        }

        if(!password.equals(repeatPassword)){

        }
        UserDAO userDAO = new UserDAO();

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setEmail(email);
        user.setRole(Role.ADMIN.ordinal());
        user.setPassword(password);
        user.setUserStatus(UserStatus.NOCONFIRMED.ordinal());

        LOG.trace("Insert in DB: user --> " + user);
        try {
            user = userDAO.insert(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        httpSession.setAttribute("user", user);

        Role userRole = user.getRole();
        LOG.trace("userRole --> " + userRole);
        httpSession.setAttribute("userRole", userRole);
        LOG.debug("SignUpCommand.doPost finish");
        return Path.PAGE_INDEX;
    }
}
