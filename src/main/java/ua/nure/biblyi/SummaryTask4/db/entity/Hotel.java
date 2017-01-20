package ua.nure.biblyi.SummaryTask4.db.entity;

/**
 * Created by dmitry on 13.01.17.
 */
public class Hotel extends Entity{

    private static final long serialVersionUID = -895125059436545966L;

    private String name;

    private int countOfStars;

    private String description;

    private City city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountOfStars() {
        return countOfStars;
    }

    public void setCountOfStars(int countOfStars) {
        this.countOfStars = countOfStars;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", countOfStars=" + countOfStars +
                ", description='" + description + '\'' +
                ", city=" + city +
                '}';
    }
}
