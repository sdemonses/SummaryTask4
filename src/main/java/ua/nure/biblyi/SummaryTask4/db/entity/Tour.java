package ua.nure.biblyi.SummaryTask4.db.entity;

/**
 * Created by dmitry on 12.01.17.
 */
public class Tour extends Entity {

    private static final long serialVersionUID = -1464232798643270435L;

    private String name;

    private int cost;

    private double duration;

    private int person;

    private long hotelId;

    private int typeId;

    private int statusId;

    private int countryId;

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

}
