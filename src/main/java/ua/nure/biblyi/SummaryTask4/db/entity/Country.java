package ua.nure.biblyi.SummaryTask4.db.entity;

/**
 * Created by dmitry on 14.01.17.
 */
public class Country extends Entity {

    private static final long serialVersionUID = -6638845323823169556L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
