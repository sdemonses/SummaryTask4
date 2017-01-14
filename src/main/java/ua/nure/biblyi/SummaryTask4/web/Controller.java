package ua.nure.biblyi.SummaryTask4.web;

import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.AbstractJDBCDao;
import ua.nure.biblyi.SummaryTask4.db.DAO.abstrDAO.GenericDao;
import ua.nure.biblyi.SummaryTask4.db.DAO.mySQL.MySqlDaoFactory;
import ua.nure.biblyi.SummaryTask4.db.DAO.mySQL.MySqlUser;
import ua.nure.biblyi.SummaryTask4.exception.DAOException;
import ua.nure.biblyi.SummaryTask4.exception.DBException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dmitry on 02.01.17.
 */
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        process(httpServletRequest,httpServletResponse);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        process(httpServletRequest,httpServletResponse);
    }

    private void process(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            GenericDao genericDao = new MySqlUser(new MySqlDaoFactory().getContext());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }


}
