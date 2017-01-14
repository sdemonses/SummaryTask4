package ua.nure.biblyi.SummaryTask4.db.entity;

/**
 * Created by dmitry on 14.01.17.
 */
public class City {

    private String name;

    private int countryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
