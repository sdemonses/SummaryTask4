package ua.nure.biblyi.SummaryTask4.db.entity;

import ua.nure.biblyi.SummaryTask4.db.Status;

/**
 * Created by Dimasyk on 22.01.2017.
 */
public class Order extends Entity {

    private static final long serialVersionUID = -2193770164751781381L;
    private User user;

    private Tour tour;

    private int saleMax;

    private int saleStep;

    private int sale;

    private Status status;



    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", tour=" + tour +
                ", saleMax=" + saleMax +
                ", saleStep=" + saleStep +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public int getSaleMax() {
        return saleMax;
    }

    public void setSaleMax(int saleMax) {
        this.saleMax = saleMax;
    }

    public int getSaleStep() {
        return saleStep;
    }

    public void setSaleStep(int saleStep) {
        this.saleStep = saleStep;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = Status.getType(status);
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }
}
