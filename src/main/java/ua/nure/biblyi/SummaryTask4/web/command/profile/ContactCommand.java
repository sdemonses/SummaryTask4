package ua.nure.biblyi.SummaryTask4.web.command.profile;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.core.Sender;
import ua.nure.biblyi.SummaryTask4.core.validation.EmailValidation;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;
import ua.nure.biblyi.SummaryTask4.exception.ValidationException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;
import ua.nure.biblyi.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dmitry on 23.01.17.
 */
public class ContactCommand extends Command {

    private static final long serialVersionUID = -8012953324035612223L;

    private static final Logger LOG = Logger.getLogger(ContactCommand.class);

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("ContactCommand.execute start");
        String result = null;
        if (TypeHttpRequest.POST == type) {
            result = doPost(httpServletRequest, httpServletResponse);
        } else {
            result = Path.PAGE_CONTACT;
        }
        LOG.debug("ContactCommand.execute finish");
        return result;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AppException {
        LOG.debug("ContactCommand.doPost start");
        String name = httpServletRequest.getParameter("name");
        String email = httpServletRequest.getParameter("email");
        String subject = httpServletRequest.getParameter("subject");
        String message = httpServletRequest.getParameter("message");

        EmailValidation emailValidation = new EmailValidation();
        try {
            emailValidation.validate(email);
        } catch (ValidationException validationException) {
            LOG.error(ErrorMessage.ERR_EMAIL_INVALID);
            httpServletRequest.setAttribute("path", Path.PAGE_CONTACT);
            throw new AppException(validationException.getMessage());
        }

        Sender sender = new Sender();
        sender.send(subject + " from " + name + ", email " + email, message, "epamtour@gmail.com");
        LOG.debug("ContactCommand.doPost finish");
        return Path.PAGE_CONTACT_POST;

    }
}
