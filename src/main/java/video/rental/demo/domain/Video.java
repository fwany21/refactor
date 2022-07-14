package video.rental.demo.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "VIDEO", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class Video {
    @Id
    private String title;
    private Rating videoRating;
    private int priceCode;
    private PriceStrategy priceStrategy;

    public static final int REGULAR = 1;
    public static final int NEW_RELEASE = 2;
    public static final int CHILDREN = 3;

    private int videoType;
    public static final int VHS = 1;
    public static final int CD = 2;
    public static final int DVD = 3;

    private LocalDate registeredDate;
    private boolean rented;

    public Video() {
    } // for hibernate

    public Video(String title, int videoType, int priceCode, Rating videoRating, LocalDate registeredDate) {
        this.title = title;
        this.videoType = videoType;
        this.priceCode = priceCode;
        this.videoRating = videoRating;
        this.registeredDate = registeredDate;
        this.rented = false;
    }

    public Video(Video another) {
        this.title = another.title;
        this.videoRating = another.videoRating;
        this.priceCode = another.priceCode;
        this.videoType = another.videoType;
        this.registeredDate = another.registeredDate;
        this.rented = another.rented;
    }

    public int getLateReturnPointPenalty() {
        //@formatter:off
        int penalty = 0;
        switch (videoType) {
            case VHS: penalty = 1;  break;
            case CD : penalty = 2;  break;
            case DVD: penalty = 3;  break;
        }
        //@formatter:on
        return penalty;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int priceCode) {
        this.priceCode = priceCode;
    }

    public String getTitle() {
        return title;
    }

    public Rating getVideoRating() {
        return videoRating;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public int getVideoType() {
        return videoType;
    }

    public boolean rentFor(Customer customer) {
        if (!isUnderAge(customer)) {
            setRented(true);
            Rental rental = new Rental(this);
            customer.addRental(rental);
            return true;
        } else {
            return false;
        }
    }

    public boolean isUnderAge(Customer customer) {
        int age = customer.getAge();

        // determine if customer is under legal age for rating
        return videoRating.isUnderAge(age);
    }

    public void setPriceStrategy(PriceStrategy price) {
        this.priceStrategy = price;
    }

    public double computeCharge(int daysRented) {
        return priceStrategy.computeCharge(daysRented);
    }

    public int getPoint(int daysRented) {
        int eachPoint = priceStrategy.getPoint();

        if (daysRented > getDaysRentedLimit())
            eachPoint -= Math.min(eachPoint, getLateReturnPointPenalty());
        return eachPoint;
    }
}
