package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by dmitry on 21.01.17.
 */
public class BannedCommand extends Command {

    private static final Logger LOG = Logger.getLogger(BannedCommand.class);

    private static final long serialVersionUID = 2696539171901830725L;

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("BannedCommand.execute start");
        String result;
        if (TypeHttpRequest.GET == type) {
            result = Path.PAGE_REDIRECT;
        } else {
            result = Path.PAGE_ERROR_PAGE;
        }
        LOG.debug("BannedCommand.execute finish");
        return result;
    }
}
