package ua.nure.biblyi.SummaryTask4.db;

/**
 * Created by dmitry on 21.01.17.
 */
public enum UserStatus {
    BAN, EMPTY, NOCONFIRMED;

    public static UserStatus getType(int id) {
        return UserStatus.values()[id];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
