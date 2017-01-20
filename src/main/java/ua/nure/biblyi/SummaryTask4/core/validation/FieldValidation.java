package ua.nure.biblyi.SummaryTask4.core.validation;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;
import ua.nure.biblyi.SummaryTask4.exception.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmitry on 18.01.17.
 */
public class FieldValidation implements Validation {

    private static final Logger LOG = Logger.getLogger(FieldValidation.class);

    private final static String REG_EXPR = "[A-Za-z0-9]+";

    private Pattern pattern;


    public FieldValidation() {
        this.pattern = Pattern.compile(REG_EXPR);

    }

    @Override
    public boolean validate(String expr) throws ValidationException {
        LOG.debug("FieldValidation.validate start");

        LOG.trace("Validation object" + expr);

        if (expr.length() < 4) {
            LOG.debug(ErrorMessage.ERR_SMALL_LOGIN + "Length :" + expr.length());
            throw new ValidationException(ErrorMessage.ERR_SMALL_LOGIN + "Length :" + expr.length());
        }
        Matcher matcher = pattern.matcher(expr);

        if (!matcher.matches()) {
            LOG.debug(ErrorMessage.ERR_LOGIN_INVALID + expr);
            throw new ValidationException(ErrorMessage.ERR_LOGIN_INVALID + expr);
        }
        return true;
    }
}
