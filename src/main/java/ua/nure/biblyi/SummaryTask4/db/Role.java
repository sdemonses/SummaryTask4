package ua.nure.biblyi.SummaryTask4.db;

/**
 * Created by dmitry on 12.01.17.
 */
public enum Role {
    ADMIN, CLIENT, MANAGER;

    public static Role getRole(int id) {
        return Role.values()[id];
    }

    public String getName() {
        return name().toLowerCase();
    }

    public int getId(){
        return this.ordinal();
    }
}
