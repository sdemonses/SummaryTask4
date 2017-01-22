package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Change language command.
 *
 * @author D.Biblyi
 *
 */
public class LanguageCommand extends Command {
    private static final Logger LOG = Logger.getLogger(LanguageCommand.class);

    private static final long serialVersionUID = 6216541401785929475L;

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("LanguageCommand.execute start");
        String result = null;
        if (TypeHttpRequest.POST == type) {
            result = doPost(httpServletRequest, httpServletResponse);
        } else {
            result = Path.PAGE_SIGN_IN;
        }
        LOG.debug("LanguageCommand.execute finish");
        return result;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        LOG.debug("LanguageCommand.doPost start");

        String language = httpServletRequest.getParameter("language");
        LOG.trace("Change to " + language);

        httpServletRequest.getSession().setAttribute("language", language);

        String uri = httpServletRequest.getHeader("referer");

        int i = uri.indexOf("controller");
        LOG.debug("LanguageCommand.doPost finish");
        if (i == -1) {
            return Path.PAGE_INDEX;
        } else {
            return uri.substring(i);
        }

    }
}
