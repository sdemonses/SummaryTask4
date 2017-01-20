package ua.nure.biblyi.SummaryTask4.db.entity;

/**
 * Created by dmitry on 14.01.17.
 */
public class City extends Entity {

    private static final long serialVersionUID = -6677827354229824522L;

    private String name;

    private Country country;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}
