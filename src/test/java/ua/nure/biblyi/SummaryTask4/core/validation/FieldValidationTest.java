package ua.nure.biblyi.SummaryTask4.core.validation;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dmitry on 23.01.17.
 */
public class FieldValidationTest {
    @Test
    public void validate() throws Exception {
        FieldValidation fieldValidation = new FieldValidation();
        Assert.assertEquals(true, fieldValidation.validate("asdsfsd"));
        Assert.assertEquals(true, fieldValidation.validate("asdf"));
        Assert.assertEquals(true, fieldValidation.validate("QWERQWE123"));
    }

}