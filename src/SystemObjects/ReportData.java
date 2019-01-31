package SystemObjects;

import java.util.ArrayList;

import SystemObjects.GeneralData.reportReference;

public class ReportData {

	private reportReference reference;
	private double avg;
	private double median;
	private ArrayList<Integer> distributionValues;
	
	/**
	 * constructor for general objects statistics
	 */
	public ReportData(double avg, double median, ArrayList<Integer> distribution, reportReference reference) {
		
		this.reference = reference;
		this.avg = avg;
		this.median = median;
		this.distributionValues = distribution;
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
	
	public ArrayList<Integer> getDistribution() {
		return distributionValues;
	}
	
	public void setDistribution(ArrayList<Integer> distribution) {
		this.distributionValues = distribution;
	}
	
	public reportReference getReference() {
		return reference;
	}
	
	public void setReference(reportReference reference) {
		this.reference = reference;
	}
	
	
}
