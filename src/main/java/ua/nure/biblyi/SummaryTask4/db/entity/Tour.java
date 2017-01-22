package ua.nure.biblyi.SummaryTask4.db.entity;

import ua.nure.biblyi.SummaryTask4.db.Status;
import ua.nure.biblyi.SummaryTask4.db.Type;

/**
 * @author dmitry
 */
public class Tour extends Entity {

    private static final long serialVersionUID = -1464232798643270435L;

    private String name;

    private int cost;

    private int duration;

    private int person;

    private Hotel hotel;

    private Type type;

    private Status status;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = Status.getType(status);
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPerson() {
        return person;
    }

    public void setPerson(int person) {
        this.person = person;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Type getType() {
        return type;
    }

    public void setType(int typeId) {
        this.type = Type.getType(typeId);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", duration=" + duration +
                ", person=" + person +
                ", hotel=" + hotel +
                ", type=" + type +
                ", status=" + status +
                '}';
    }
}
