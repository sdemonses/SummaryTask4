package ua.nure.biblyi.SummaryTask4.web.command.profile;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.core.validation.EmailValidation;
import ua.nure.biblyi.SummaryTask4.core.validation.FieldValidation;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.OrderDAO;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.TourDAO;
import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.UserDAO;
import ua.nure.biblyi.SummaryTask4.db.Role;
import ua.nure.biblyi.SummaryTask4.db.entity.Order;
import ua.nure.biblyi.SummaryTask4.db.entity.Tour;
import ua.nure.biblyi.SummaryTask4.db.entity.User;
import ua.nure.biblyi.SummaryTask4.exception.*;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;
import ua.nure.biblyi.SummaryTask4.web.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Change profile settings command.
 *
 * @author D.Biblyi
 *
 */
public class ProfileCommand extends Command {
    private final static Logger LOG = Logger.getLogger(ProfileCommand.class);
    private static final long serialVersionUID = 7919187954935920165L;

    @Override
    public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, TypeHttpRequest type) throws AppException {
        LOG.debug("ProfileCommand.execute start");

        String result = null;

        if (TypeHttpRequest.POST == type) {
            result = doPost(httpServletRequest, httpServletResponse);
        } else {
            result = doGet(httpServletRequest, httpServletResponse);
        }

        LOG.debug("ProfileCommand.execute finish");
        return result;
    }

    private String doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws DAOException {
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if(user.getRole()!= Role.CLIENT){
            return Path.PAGE_INDEX;
        }
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderList = orderDAO.getOrderList(user.getId());
        for (Order order :
                orderList) {
            order.setSale(orderList.size()/3*order.getSaleStep());
            while(order.getSale()>order.getSaleMax()){
                order.setSale(order.getSale() - order.getSaleStep());
            }
        }
        httpServletRequest.setAttribute("orders", orderList);

        return Path.PAGE_PROFILE;
    }

    private String doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AppException {
        LOG.debug("ProfileCommand.doPost start");
        String path = Path.PAGE_ERROR_PAGE;
        String firstName = httpServletRequest.getParameter("firstName");
        String lastName = httpServletRequest.getParameter("lastName");
        String login = httpServletRequest.getParameter("login");
        String email = httpServletRequest.getParameter("email");
        EmailValidation emailValidation = new EmailValidation();
        try {
            emailValidation.validate(email);
        } catch (ValidationException validationException) {
            LOG.error(ErrorMessage.ERR_EMAIL_INVALID);
            httpServletRequest.setAttribute("path", Path.PAGE_PROFILE);
            throw new AppException(validationException);
        }

        FieldValidation fieldValidation = new FieldValidation();

        try {
            fieldValidation.validate(login);
        } catch (ValidationException e) {
            LOG.error(ErrorMessage.ERR_FIELD_INVALID);
            httpServletRequest.setAttribute("path", Path.PAGE_PROFILE);
            throw new AppException(e.getMessage());
        }

        UserDAO userDAO = new UserDAO();

        User user = (User) httpServletRequest.getSession().getAttribute("user");
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setLogin(login);
        try {
            userDAO.update(user);
        } catch (DuplicateException e) {

            httpServletRequest.setAttribute("path", Path.PAGE_SIGN_UP);
            throw new AppException(ErrorMessage.ERR_LOGIN_NOT_FREE);
        }
        LOG.debug("ProfileCommand.doPost finish");
        return Path.PAGE_PROFILE_POST;
    }

}
