package SystemObjects;

import java.util.ArrayList;

import SystemObjects.GeneralData.reportReference;

public class ReportData {

	private reportReference reference;
	private double avg;
	private double median;
	private ArrayList<Object> distribution;//distribution(0)=valuesArray, distribution(1)=rangesArray, distribution(2)=valuesAmountArray which indicate how many values are there in each range
	
	/**
	 * constructor for general objects statistics
	 */
	public ReportData(double avg, double median, ArrayList<Object> distribution, reportReference reference) {
		
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
	
	public ArrayList<Object> getDistribution() {
		return distribution;
	}
	
	public void setDistribution(ArrayList<Object> distribution) {
		this.distribution = distribution;
	}
	
	public reportReference getReference() {
		return reference;
	}
	
	public void setReference(reportReference reference) {
		this.reference = reference;
	}
	
	
}
