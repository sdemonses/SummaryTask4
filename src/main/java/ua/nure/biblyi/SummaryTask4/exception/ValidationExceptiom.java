package ua.nure.biblyi.SummaryTask4.exception;

/**
 * Created by dmitry on 18.01.17.
 */
public class ValidationExceptiom extends AppException {

    private static final long serialVersionUID = -6332593358280644231L;

    public ValidationExceptiom() {
    }

    public ValidationExceptiom(String message) {
        super(message);
    }

    public ValidationExceptiom(Exception e) {

    }

    public ValidationExceptiom(String message, Throwable cause) {
        super(message, cause);
    }
}
