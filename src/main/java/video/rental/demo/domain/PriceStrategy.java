package video.rental.demo.domain;

public interface PriceStrategy {
	public double computeCharge(int daysRented);

	public int getPoint();
}
