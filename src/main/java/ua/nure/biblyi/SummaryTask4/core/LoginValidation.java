package ua.nure.biblyi.SummaryTask4.core;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.UserDAO;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;
import ua.nure.biblyi.SummaryTask4.exception.ValidationExceptiom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmitry on 18.01.17.
 */
public class LoginValidation implements Validation {

    private static final Logger LOG = Logger.getLogger(LoginValidation.class);

    private final static String REG_EXPR = "[A-Za-z0-9]+";

    private Pattern pattern;

    @Override
    public boolean validate(String expr) throws ValidationExceptiom {
        LOG.debug("LoginValidation.validate start");

        LOG.trace("Validation object" + expr);

        if (expr.length() < 4) {
            LOG.debug(ErrorMessage.ERR_SMALL_LOGIN + "Length :" + expr.length());
            throw new ValidationExceptiom(ErrorMessage.ERR_SMALL_LOGIN + "Length :" + expr.length());
        }
        Matcher matcher = pattern.matcher(expr);

        if (!matcher.matches()) {
            LOG.debug(ErrorMessage.ERR_LOGIN_INVALID + expr);
            throw new ValidationExceptiom(ErrorMessage.ERR_LOGIN_INVALID + expr);
        }
        UserDAO userDAO = new UserDAO();
        User user =  null;

        try {
            user = userDAO.getByLogin(expr);
        } catch (DAOException e) {
            throw new ValidationExceptiom(e);
        }
        if(user == null){
            return true;
        }
        throw new ValidationExceptiom(ErrorMessage.ERR_LOGIN_NOT_FREE);

    }
}
