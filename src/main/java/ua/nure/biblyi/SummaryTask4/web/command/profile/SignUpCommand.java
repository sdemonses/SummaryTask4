package ua.nure.biblyi.SummaryTask4.web.command.profile;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.core.validation.EmailValidation;
import ua.nure.biblyi.SummaryTask4.core.validation.FieldValidation;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.UserDAO;
import ua.nure.biblyi.SummaryTask4.db.Role;
import ua.nure.biblyi.SummaryTask4.db.UserStatus;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DuplicateException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;
import ua.nure.biblyi.SummaryTask4.exception.ValidationException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;
import ua.nure.biblyi.SummaryTask4.web.command.Command;
import ua.nure.biblyi.SummaryTask4.web.command.CommandContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Registration command.
 *
 * @author D.Biblyi
 *
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

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AppException {
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
            LOG.error(ErrorMessage.ERR_FIELD_INVALID);
            httpServletRequest.setAttribute("path", Path.PAGE_SIGN_UP);
            throw new AppException(validationException.getMessage());
        }

        String email = httpServletRequest.getParameter("email");

        EmailValidation emailValidation = new EmailValidation();
        try {
            emailValidation.validate(email);
        } catch (ValidationException validationException) {
            LOG.error(ErrorMessage.ERR_EMAIL_INVALID);
            httpServletRequest.setAttribute("path", Path.PAGE_SIGN_UP);
            throw new AppException(validationException.getMessage());
        }

        if(!password.equals(repeatPassword)){
            LOG.error(ErrorMessage.ERR_DIFFERENT_PASSWORD);
            httpServletRequest.setAttribute("path", Path.PAGE_SIGN_UP);
            throw new AppException(ErrorMessage.ERR_DIFFERENT_PASSWORD);
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
        } catch (DuplicateException e) {
            httpServletRequest.setAttribute("path", Path.PAGE_SIGN_UP);
            throw new AppException(ErrorMessage.ERR_LOGIN_NOT_FREE);
        }

        httpSession.setAttribute("user", user);

        Role userRole = user.getRole();
        LOG.trace("userRole --> " + userRole);
        httpSession.setAttribute("userRole", userRole);
        LOG.debug("SignUpCommand.doPost finish");
        return Path.PAGE_INDEX;
    }
}
