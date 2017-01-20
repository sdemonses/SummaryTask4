package ua.nure.biblyi.SummaryTask4.db;

/**
 * Created by dmitry on 12.01.17.
 */
public enum Type {
    EXCURSION, SHOPPING, REST;

    public static Type getType(int id) {
        return Type.values()[id];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
