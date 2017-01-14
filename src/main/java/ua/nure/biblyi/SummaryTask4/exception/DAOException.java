package ua.nure.biblyi.SummaryTask4.exception;

/**
 * Created by dmitry on 14.01.17.
 */
public class DAOException extends AppException {

    private static final long serialVersionUID = 5445054795219074694L;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
