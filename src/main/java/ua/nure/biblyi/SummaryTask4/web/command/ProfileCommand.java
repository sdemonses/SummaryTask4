package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.core.validation.EmailValidation;
import ua.nure.biblyi.SummaryTask4.core.validation.FieldValidation;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.TourDAO;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.UserDAO;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;
import ua.nure.biblyi.SummaryTask4.exception.ValidationException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Dimasyk on 19.01.2017.
 */
public class ProfileCommand extends Command {
    private final static Logger LOG = Logger.getLogger(ProfileCommand.class);

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("ProfileCommand.execute start");

        String result = null;

        if (TypeHttpRequest.POST == type) {
            result = doPost(httpServletRequest, httpServletResponse);
        } else {
            result = doGet(httpServletRequest, httpServletResponse);
        }

        LOG.debug("ProfileCommand.execute finish");
        return result;
    }

    private String doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        TourDAO tourDAO = new TourDAO();
        List<Tour> tourList = null;
        try {
            tourList = tourDAO.getTourForUser(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        httpServletRequest.setAttribute("tours", tourList);

        return Path.PAGE_PROFILE;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.debug("ProfileCommand.doPost start");
        String path = Path.PAGE_ERROR_PAGE;
        String firstName = httpServletRequest.getParameter("firstName");
        String lastName = httpServletRequest.getParameter("lastName");
        String login = httpServletRequest.getParameter("login");
        String email = httpServletRequest.getParameter("email");
        EmailValidation emailValidation = new EmailValidation();
        try {
            emailValidation.validate(email);
        } catch (ValidationException validationException) {
            LOG.error(ErrorMessage.ERR_EMAIL_INVALID);

        }

        FieldValidation fieldValidation = new FieldValidation();

        try {
            fieldValidation.validate(lastName);
            fieldValidation.validate(login);
            fieldValidation.validate(firstName);
        } catch (ValidationException e) {
            //
        }

        UserDAO userDAO = new UserDAO();

        User user = (User) httpServletRequest.getAttribute("user");
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLogin(login);

        try {
            userDAO.update(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        LOG.debug("ProfileCommand.doPost finish");
        return Path.PAGE_PROFILE_POST;
    }

}
