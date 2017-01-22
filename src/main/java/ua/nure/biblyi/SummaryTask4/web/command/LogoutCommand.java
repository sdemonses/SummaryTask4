package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Logout command.
 *
 * @author D.Biblyi
 *
 */
public class LogoutCommand extends Command {
    private static final Logger LOG = Logger.getLogger(LogoutCommand.class);
    private static final long serialVersionUID = -3150627078103374208L;

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("LogoutCommand.execute start");
        String result = null;
        if (TypeHttpRequest.GET == type) {
            result = doGet(httpServletRequest, httpServletResponse);
        }else{
            result = Path.PAGE_ERROR_PAGE;
        }

        LOG.debug("LogoutCommand.execute finish");
        return result;
    }

    private String doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.debug("LogoutCommand.doPost start");

        HttpSession session = httpServletRequest.getSession();
        if (session != null) {
            session.invalidate();
        }
        LOG.debug("LogoutCommand.doPost finish");
        return Path.PAGE_INDEX;
    }
}
