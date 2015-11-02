
public class Rating {
	private int reviewerId;
	private int resturantId;
	private int rate;
	private String rateDate;  // "mm-dd-yyyy"
	
	public int getReviewerId() {
		return reviewerId;
	}
	public void setReviewerId(int reviewerId) {
		this.reviewerId = reviewerId;
	}
	public int getResturantId() {
		return resturantId;
	}
	public void setResturantId(int resturantId) {
		this.resturantId = resturantId;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getRateDate() {
		return rateDate;
	}
	public void setRateDate(String rateDate) {
		this.rateDate = rateDate;
	}

}
