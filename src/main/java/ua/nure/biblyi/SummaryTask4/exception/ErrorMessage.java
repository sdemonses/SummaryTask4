package ua.nure.biblyi.SummaryTask4.exception;

/**
 * Created by dmitry on 15.01.17.
 */
public class ErrorMessage {

    private ErrorMessage(){

    }

    //CRUD error operation
    public static final String ERR_CANNOT_INSERT_ENTRY = "Cannot insert entry.";

    public static final String ERR_CANNOT_OBTAIN_ENTRY = "Cannot obtain entry.";
    public static final String ERR_CANNOT_UPDATE_ENTRY = "Cannot update entry.";

    public static final String ERR_CANNOT_DELETE_ENTRY = "Cannot delete entry.";

    public static final String ERR_CANNOT_FIND_ENTRY = "Cannot find entry.";

    public static final String ERR_CANNOT_GET_INFO = "Cannot get information from result set.";

    public static final String ERR_CANNOT_SET_INFO = "Cannot set information in statement.";


    public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source.";

    public static final String EMPTY_RESULT_SET = "Result set is empty.";

    public static final String COUNT_CHANGE_LINE = " Count of changed line: ";

    public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool.";

    //close connection in db and rollback
    public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a ";

    public static final String ERR_CANNOT_ROLLBACK_TRANSACTION = "Cannot rollback transaction.";


    /////Registration
    public static final String ERR_DIFFERENT_PASSWORD = "The passwords are different.";

    public static final String ERR_LOGIN_NOT_FREE = "A user with this login already exist.";

    public static final String ERR_EMAIL_INVALID = "Email is not valid.";

    public static final String ERR_FIELD_INVALID = "Field is not valid.";

    public static final String ERR_SMALL_LOGIN = "Username must be longer than 4 characters";

    public static final String ERR_WRONG_PASSWORD = "Wrong password";



}
