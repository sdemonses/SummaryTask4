package ua.nure.biblyi.SummaryTask4.exception;

/**
 * Created by dmitry on 14.01.17.
 */
public class AppException extends Exception {

    private static final long serialVersionUID = -316235780229571251L;

    public AppException() {


    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}
