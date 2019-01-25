package SystemObjects;
import SystemObjects.ReportData;

public class LateReturnsReportBookData {
	
	private String BookName;
	private ReportData Amount;
	private ReportData Duration;
	
	public LateReturnsReportBookData(ReportData amount, ReportData duration, String bName) {
		Amount = amount;
		Duration = duration;
		BookName=bName;
	}
	
	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public ReportData getAmount() {
		return Amount;
	}
	
	public void setAmount(ReportData amount) {
		Amount = amount;
	}
	
	public ReportData getDuration() {
		return Duration;
	}
	
	public void setDuration(ReportData duration) {
		Duration = duration;
	}
	
	public double getAmountAvg() {
		return Amount.getAvg();
	}
	
	public double getDurationAvg() {
		return Duration.getAvg();
	}
	
	public double getAmountMedian() {
		return Amount.getMedian();
	}
	
	public double getDurationMedian() {
		return Duration.getMedian();
	}
	
	public double getAmountDistribution() {
		return Amount.getDistribution();
	}
	
	public double getDurationDistribution() {
		return Duration.getDistribution();
	}
	
}
