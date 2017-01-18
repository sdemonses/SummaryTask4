package ua.nure.biblyi.SummaryTask4.web.command;

import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Created by dmitry on 16.01.17.
 */
public abstract class Command implements Serializable{
    private static final long serialVersionUID = 5724744267098426897L;

    public abstract String execute(HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse,
                                   TypeHttpRequest type) throws AppException;

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
