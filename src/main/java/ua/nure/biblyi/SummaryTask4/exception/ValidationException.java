package ua.nure.biblyi.SummaryTask4.exception;

/**
 * Created by dmitry on 18.01.17.
 */
public class ValidationException extends AppException {

    private static final long serialVersionUID = -6332593358280644231L;

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Exception e) {

    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
