package ua.nure.biblyi.SummaryTask4.db;

/**
 * Created by dmitry on 12.01.17.
 */
public enum Status {
    EMPTY, HOT, REGISTER, PAID, CANCELED;


    public static Status getType(int id) {
        return Status.values()[id];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
