package ua.nure.biblyi.SummaryTask4.db.entity;

/**
 * Created by dmitry on 12.01.17.
 */
public enum Role {
    ADMIN, CLIENT, MANAGER;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
