package ua.nure.biblyi.SummaryTask4.exception;

/**
 * Created by dmitry on 14.01.17.
 */
public class DBException extends AppException {

    private static final long serialVersionUID = -8851477585358948077L;

    public DBException() {
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
