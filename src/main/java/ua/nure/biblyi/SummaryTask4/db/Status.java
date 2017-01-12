package ua.nure.biblyi.SummaryTask4.db;

import ua.nure.biblyi.SummaryTask4.db.entity.Tour;

/**
 * Created by dmitry on 12.01.17.
 */
public enum Status {
    EMPTY, HOT, REGISTER, PAID, CANCELED;


    public static Status getType(Tour tour) {
        int statusId = tour.getStatusId();
        return Status.values()[statusId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
