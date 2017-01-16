package ua.nure.biblyi.SummaryTask4.web;

import ua.nure.biblyi.SummaryTask4.db.DAO.ImplDAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dmitry on 02.01.17.
 */
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 2595304647505707130L;

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        process(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        process(httpServletRequest,httpServletResponse);
    }

    private void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        UserDAO userDAO = new UserDAO();

    }


}
