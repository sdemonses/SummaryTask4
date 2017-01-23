package ua.nure.biblyi.SummaryTask4.web.command.profile;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.UserDAO;
import ua.nure.biblyi.SummaryTask4.db.UserStatus;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;
import ua.nure.biblyi.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dmitry on 23.01.17.
 */
public class CodeCommand extends Command {
    private static final long serialVersionUID = 8238396668605369878L;

    private static final Logger LOG = Logger.getLogger(CodeCommand.class);

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("CodeCommand.execute start");
        String result = null;
        if (TypeHttpRequest.POST == type) {
            result = doPost(httpServletRequest, httpServletResponse);
        } else {
            result = Path.PAGE_REDIRECT;
        }
        LOG.debug("CodeCommand.execute finish");
        return result;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AppException {
        LOG.debug("CodeCommand.doPost start");
        UserDAO userDAO = new UserDAO();
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        int code;
        try {
            code = Integer.parseInt(httpServletRequest.getParameter("code"));
        } catch (NumberFormatException e) {
            LOG.error("Invalid data", e);
            httpServletRequest.setAttribute("path", Path.PAGE_TOURS);
            throw new AppException(e.getMessage());
        }
        LOG.trace("Code -->" + code);
        if (code != user.getActivationCode()) {
            httpServletRequest.setAttribute("path", Path.PAGE_REDIRECT_POST);
            throw new AppException("Wrong code");
        }

        httpServletRequest.getSession().removeAttribute("userStatus");
        user.setUserStatus(UserStatus.EMPTY.ordinal());
        userDAO.update(user);

        LOG.debug("CodeCommand.doPost finish");
        return Path.PAGE_PROFILE_POST;
    }
}
