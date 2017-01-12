package ua.nure.biblyi.SummaryTask4.db.entity;

import java.io.Serializable;

/**
 * Created by dmitry on 12.01.17.
 */
public abstract class Entity implements Serializable {

    private static final long serialVersionUID = -6418374411770642259L;

    private long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
