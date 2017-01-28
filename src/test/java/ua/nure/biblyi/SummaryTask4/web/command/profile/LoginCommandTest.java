package ua.nure.biblyi.SummaryTask4.web.command.profile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Created by dmitry on 23.01.17.
 */
public class LoginCommandTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void execute() throws Exception {
        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/HelloWorld.do")).thenReturn(rd);


        String result = new LoginCommand().execute(request, response, TypeHttpRequest.POST);

        //Verify the session attribute value

        System.out.println("Result: " + result);

        assertEquals("/index.jsp", result);
    }

}