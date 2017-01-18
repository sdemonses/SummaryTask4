package ua.nure.biblyi.SummaryTask4.core;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;
import ua.nure.biblyi.SummaryTask4.exception.ValidationExceptiom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmitry on 17.01.17.
 */
public class EmailValidation implements Validation {

    private static final Logger LOG = Logger.getLogger(EmailValidation.class);

    private final static String REG_EXPR = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private Pattern pattern;

    public EmailValidation() {
        pattern = Pattern.compile(REG_EXPR);
    }


    @Override
    public boolean validate(String email) throws ValidationExceptiom {
        LOG.debug("EmailValidation.validate start");

        LOG.trace("Validation object" + email);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            LOG.debug(ErrorMessage.ERR_EMAIL_INVALID);
            throw new ValidationExceptiom(ErrorMessage.ERR_EMAIL_INVALID);
        }
        LOG.debug("EmailValidation.validate finish");
        return true;
    }
}
