package ua.nure.biblyi.SummaryTask4.web.command;

import ua.nure.biblyi.SummaryTask4.exception.AppException;
import ua.nure.biblyi.SummaryTask4.web.TypeHttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * Main interface for the Command pattern implementation.
 *
 * @author D.Biblyi
 *
 */
public abstract class Command implements Serializable{
    private static final long serialVersionUID = 5724744267098426897L;


    /**
     * Define method type and execution method for command.
     *
     * @return Address to go once the command is executed.
     */
    public abstract String execute(HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse,
                                   TypeHttpRequest type) throws AppException;

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
