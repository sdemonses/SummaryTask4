package ua.nure.biblyi.SummaryTask4.web;

import org.apache.log4j.Logger;
import ua.nure.biblyi.SummaryTask4.Path;
import ua.nure.biblyi.SummaryTask4.db.UserStatus;
import ua.nure.biblyi.SummaryTask4.db.entity.User;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by dmitry on 23.01.17.
 */
public class MyTag extends TagSupport {

    private static final long serialVersionUID = -6136849892887623467L;
    private static final Logger LOG = Logger.getLogger(MyTag.class);

    @Override
    public int doStartTag() throws JspException {
        LOG.debug("MyTag.doStartTag start");
        HttpSession session = pageContext.getSession();
        User user = (User) session.getAttribute("user");

        if (user.getUserStatus() == UserStatus.NOCONFIRMED) {
            HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();

            try {
                response.sendRedirect(Path.PAGE_REDIRECT_POST);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        LOG.debug("MyTag.doStartTag finish");
        return super.doStartTag();
    }
}
