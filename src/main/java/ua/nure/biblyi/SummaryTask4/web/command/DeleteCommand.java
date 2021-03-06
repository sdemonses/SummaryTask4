package ua.nure.biblyi.SummaryTask4.web.command;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.TourDAO;
import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Delete tour Command
 *
 * @author D.Biblyi
 *
 */
public class DeleteCommand extends Command {
    private final static Logger LOG = Logger.getLogger(DeleteCommand.class);
    private static final long serialVersionUID = -2576566213655293100L;

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("DeleteCommand.execute start");
        String result = null;
        if (TypeHttpRequest.GET == type) {
            result = Path.PAGE_ERROR_PAGE;
        } else {
            result = doPost(httpServletRequest, httpServletResponse);
        }
        LOG.debug("DeleteCommand.execute finish");

        return result;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws DAOException {
        LOG.debug("DeleteCommand.doPost start");
        Long id = Long.parseLong(httpServletRequest.getParameter("id"));
        TourDAO tourDAO = new TourDAO();
        tourDAO.delete(id);
        LOG.debug("DeleteCommand.doPost finish");
        return Path.PAGE_TOURS_POST;
    }
}
