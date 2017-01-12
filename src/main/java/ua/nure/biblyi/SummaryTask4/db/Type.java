package ua.nure.biblyi.SummaryTask4.db;

import ua.nure.biblyi.SummaryTask4.db.entity.Tour;

/**
 * Created by dmitry on 12.01.17.
 */
public enum Type {
    EXCURSION, SHOPPING, REST;

    public static Type getType(Tour tour) {
        int typeId = tour.getTypeId();
        return Type.values()[typeId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
