package video.rental.demo.domain;

public enum Rating {
	TWELVE(12), FIFTEEN(15), EIGHTEEN(18);
	
	int criteriaAge;

	private Rating(int criteriaAge) {
		this.criteriaAge = criteriaAge;
	}
	
	public boolean isUnderAge(int age) {
		return age < criteriaAge;
	}
}