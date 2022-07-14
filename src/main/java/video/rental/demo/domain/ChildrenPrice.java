package video.rental.demo.domain;

public class ChildrenPrice implements PriceStrategy {

	@Override
	public double computeCharge(int daysRented) {
		double eachCharge = 0;
		eachCharge += 1.5;
		if (daysRented > 3)
			eachCharge += (daysRented - 3) * 1.5;
		return eachCharge;
	}

	@Override
	public int getPoint() {
		return 1;
	}

}
