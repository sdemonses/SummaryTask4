package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.UserDAO;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.DuplicateException;
import ua.nure.biblyi.SummaryTask4.exception.ErrorMessage;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Change password command.
 *
 * @author D.Biblyi
 *
 */
public class PasswordCommand extends Command {
    private final static Logger LOG = Logger.getLogger(PasswordCommand.class);
    private static final long serialVersionUID = 177616074256276634L;

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("PasswordCommand.execute start");
        String result = null;

        if (TypeHttpRequest.POST == type) {
            result = doPost(httpServletRequest, httpServletResponse);
        } else {
            result = "";
        }
        LOG.debug("PasswordCommand.execute finish");
        return result;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AppException {
        LOG.debug("PasswordCommand.doPost start");
        String oldPass = httpServletRequest.getParameter("oldPassword");
        String password = httpServletRequest.getParameter("password");
        String repeatPassword = httpServletRequest.getParameter("repeatPassword");
        if(password.length()<4){
            LOG.error(ErrorMessage.ERR_SMALL_LOGIN);
            httpServletRequest.setAttribute("path", Path.PAGE_PROFILE);
            throw new AppException(ErrorMessage.ERR_SMALL_LOGIN);
        }

        if(!password.equals(repeatPassword)){
            LOG.error(ErrorMessage.ERR_DIFFERENT_PASSWORD);
            httpServletRequest.setAttribute("path", Path.PAGE_PROFILE);
            throw new AppException(ErrorMessage.ERR_DIFFERENT_PASSWORD);
        }
        UserDAO userDAO = new UserDAO();
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        try {
            if(!userDAO.getByPK((Long)user.getId()).getPassword().equals(oldPass)){
                LOG.error(ErrorMessage.ERR_WRONG_PASSWORD);
                httpServletRequest.setAttribute("path", Path.PAGE_PROFILE);
                throw new AppException(ErrorMessage.ERR_WRONG_PASSWORD);
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }
        user.setPassword(password);
        LOG.debug("PasswordCommand.doPost finish");


            userDAO.update(user);

        return Path.PAGE_PROFILE_POST;
    }
}
