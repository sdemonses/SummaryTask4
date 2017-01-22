package ua.nure.biblyi.SummaryTask4.exception;

/**
 * Created by Dimasyk on 22.01.2017.
 */
public class DuplicateException extends AppException {
    public DuplicateException() {
        super();
    }

    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateException(Throwable cause) {
        super(cause);
    }
}
