package ua.nure.biblyi.SummaryTask4.core;

import ua.nure.biblyi.SummaryTask4.exception.ValidationExceptiom;

/**
 * Created by dmitry on 17.01.17.
 */
public interface Validation {

    boolean validate(String expr) throws ValidationExceptiom;

}
