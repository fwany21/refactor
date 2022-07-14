package video.rental.demo.domain;

public class RegularPrice implements PriceStrategy {

	@Override
	public double computeCharge(int daysRented) {
		double eachCharge = 0;
		eachCharge += 2;
		if (daysRented > 2)
			eachCharge += (daysRented - 2) * 1.5;
		return eachCharge;
	}

	@Override
	public int getPoint() {
		return 1;
	}

}
