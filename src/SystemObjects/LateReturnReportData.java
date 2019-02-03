package SystemObjects;

import java.util.ArrayList;

public class LateReturnReportData {
	ReportData generalAmount;
	ReportData generalDuration;
	ArrayList<LateReturnsReportBookData> bookData;
	public ReportData getGeneralAmount() {
		return generalAmount;
	}
	public void setGeneralAmount(ReportData generalAmount) {
		this.generalAmount = generalAmount;
	}
	public ReportData getGeneralDuration() {
		return generalDuration;
	}
	public void setGeneralDuration(ReportData generalDuration) {
		this.generalDuration = generalDuration;
	}
	public ArrayList<LateReturnsReportBookData> getBookData() {
		return bookData;
	}
	public void setBookData(ArrayList<LateReturnsReportBookData> bookData) {
		this.bookData = bookData;
	}
}
