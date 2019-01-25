package SystemObjects;

public class ReportData {

	private String reference;
	private double avg;
	private double median;
	private double distribution;
	
	/**
	 * constructor for general objects statistics
	 */
	public ReportData(double avg, double median, double distribution, String reference) {
		
		this.reference = reference;
		this.avg = avg;
		this.median = median;
		this.distribution = distribution;
	}

	public double getAvg() {
		return avg;
	}
	
	public void setAvg(double avg) {
		this.avg = avg;
	}
	
	public double getMedian() {
		return median;
	}
	
	public void setMedian(double median) {
		this.median = median;
	}
	
	public double getDistribution() {
		return distribution;
	}
	
	public void setDistribution(double distribution) {
		this.distribution = distribution;
	}
	
	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	
}
