package video.rental.demo.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	private int code;
	private String name;
	private LocalDate dateOfBirth;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Rental> rentals = new ArrayList<>();

	public Customer() {	// for hibernate
	}

	public Customer(int code, String name, LocalDate dateOfBirth) {
		this.code = code;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
	}

	public Customer(Customer another) {
		this.code = another.code;
		this.name = another.name;
		this.dateOfBirth = another.dateOfBirth;
		this.rentals = new ArrayList<>(another.rentals);
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public List<Rental> getRentals() {
		return rentals;
	}

	public void addRental(Rental rental) {
		if (this.rentals == null) {
			rentals = new ArrayList<>();
		}
		rentals.add(rental);
	}

	public void clearRentals() {
		this.rentals = new ArrayList<Rental>();
	}

	public String getReport() {
		String result = "Customer Report for " + getName() + "\n";

		List<Rental> rentals = getRentals();
		double totalCharge = 0;
		int totalPoint = 0;

		for (Rental each : rentals) {
			double eachCharge = 0;
			int eachPoint = 0;
			int daysRented = each.getDaysRented();

			switch (each.getVideo().getPriceCode()) {
			case Video.REGULAR:
				eachCharge += 2;
				if (daysRented > 2)
					eachCharge += (daysRented - 2) * 1.5;
				break;
			case Video.NEW_RELEASE:
				eachCharge = daysRented * 3;
				break;
			case Video.CHILDREN:
				eachCharge += 1.5;
				if (daysRented > 3)
					eachCharge += (daysRented - 3) * 1.5;
				break;
			}
			
			eachPoint++;
			if ((each.getVideo().getPriceCode() == Video.NEW_RELEASE))
				eachPoint++;

			if (daysRented > each.getDaysRentedLimit())
				eachPoint -= Math.min(eachPoint, each.getVideo().getLateReturnPointPenalty());

			result += "\tTitle: " + each.getVideo().getTitle() + "\tDays rented: " + daysRented + "\tCharge: " + eachCharge
					+ "\tPoint: " + eachPoint + "\n";

			totalCharge += eachCharge;
			totalPoint += eachPoint;
		}
		
		result += "Total charge: " + totalCharge + "\tTotal Point: " + totalPoint + "\n";

		if (totalPoint >= 10) {
			System.out.println("Congratulations! You earned two free coupons");
		} else if (totalPoint >= 5) {
			System.out.println("Congratulations! You earned one free coupon");
		}

		return result;
	}

	int getAge() {
	    // calculate customer's age in years and months
	
	    // parse customer date of birth
	    Calendar calDateOfBirth = Calendar.getInstance();
	    try {
	        calDateOfBirth.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(getDateOfBirth().toString()));
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	
	    // get current date
	    Calendar calNow = Calendar.getInstance();
	    calNow.setTime(new java.util.Date());
	
	    // calculate age different in years and months
	    int ageYr = (calNow.get(Calendar.YEAR) - calDateOfBirth.get(Calendar.YEAR));
	    int ageMo = (calNow.get(Calendar.MONTH) - calDateOfBirth.get(Calendar.MONTH));
	
	    // decrement age in years if month difference is negative
	    if (ageMo < 0) {
	        ageYr--;
	    }
	    int age = ageYr;
	    return age;
	}

}
