package ua.nure.biblyi.SummaryTask4.web;

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

    }


}
