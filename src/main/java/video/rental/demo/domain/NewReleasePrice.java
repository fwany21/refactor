package video.rental.demo.domain;

public class NewReleasePrice implements PriceStrategy {

	@Override
	public double computeCharge(int daysRented) {
		return daysRented * 3;
	}

	@Override
	public int getPoint() {
		return 2;
	}

}
