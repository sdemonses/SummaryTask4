package ua.nure.biblyi.SummaryTask4.core.validation;

import ua.nure.biblyi.SummaryTask4.exception.ValidationException;

/**
 * Created by dmitry on 17.01.17.
 */
public interface Validation {

    boolean validate(String expr) throws ValidationException;

}
