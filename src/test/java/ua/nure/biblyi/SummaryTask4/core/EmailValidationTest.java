package ua.nure.biblyi.SummaryTask4.core;
import org.junit.Assert;
import org.junit.Test;
import ua.nure.biblyi.SummaryTask4.exception.ValidationExceptiom;

/**
 * Created by dmitry on 17.01.17.
 */
public class EmailValidationTest {
    @Test
    public void validateValid() throws ValidationExceptiom {
        EmailValidation emailValidation = new EmailValidation();
        String[] mass = new String[]{
                "alex@yandex.ru",
                "alex-27@yandex.com",
                "alex.27@yandex.com",
                "alex111@devcolibri.com",
                "alex.100@devcolibri.com.ua",
                "alex@1.com",
                "alex@gmail.com.com",
                "alex+27@gmail.com",
                "alex-27@yandex-test.com"
        };
        for (String email :
                mass) {
            Assert.assertEquals(true, emailValidation.validate(email));
        }
    }

    @Test(expected = ValidationExceptiom.class)
    public void validateInvalid() throws ValidationExceptiom {
        EmailValidation emailValidation = new EmailValidation();
        String[] mass = new String[]{
                "devcolibri",
                "alex@.com.ua",
                "alex123@gmail.a",
                "alex123@.com",
                "alex123@.com.com",
                ".alex@devcolibri.com",
                "alex()*@gmail.com",
                "alex@%*.com",
                "alex..2013@gmail.com",
                "alex.@gmail.com",
                "alex@devcolibri@gmail.com",
                "alex@gmail.com.1ua"
        };
        for (String email :
                mass) {
            emailValidation.validate(email);
        }
    }

}