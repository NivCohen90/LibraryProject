package Server;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import Interfaces.IAlert;
import SystemObjects.ReportData;
import SystemObjects.ServerData;
import SystemObjects.GeneralData.reportReference;
import SystemObjects.LateReturnsReportBookData;

public class ReportQueries {
	private static Statement st;
	
	public static ArrayList<LateReturnsReportBookData> allBooksLateReturnData(){
		ArrayList<String> booksWithLoans=LoanQueries.booksWithLoans();
		ArrayList<LateReturnsReportBookData> booksData= new ArrayList<LateReturnsReportBookData>();
		for(int i=0;i<LoanQueries.numberOfBooksWithLateLoans();i++) 
			booksData.add(specificBookLateReturnReportData(booksWithLoans.get(i)));
		return booksData;
	}
	
	public static LateReturnsReportBookData specificBookLateReturnReportData(String bookCatalogNumber) {
		String bookName=BookQueries.bookName(bookCatalogNumber);

		return new LateReturnsReportBookData(bookAmountLateReturnReportStat(bookCatalogNumber), bookDurationLateReturnReportStat(bookCatalogNumber), bookName);
	}
	
	public static ReportData bookAmountLateReturnReportStat(String bookCatalogNumber) {
		int latesAmount= LoanQueries.specificBookLateLoansAmount(bookCatalogNumber);
		int loansAmount=LoanQueries.totalBookLoansAmount(bookCatalogNumber);
		
		return new ReportData(latesAmount/loansAmount, 0, new ArrayList<Object>(), reportReference.BookLatesAmount);
	}
	
	public static ReportData bookDurationLateReturnReportStat(String bookCatalogNumber) {
		ArrayList<Integer> lateLoanDuration= LoanQueries.specificBookLateLoansDuration(bookCatalogNumber);
		
		return new ReportData(calcAvg(lateLoanDuration), calcMedian(lateLoanDuration.size(), lateLoanDuration), calcDistribution(lateLoanDuration), reportReference.BookLatesDuration);
	}
	
	public static ReportData generalAmountLateReturnsReportStat(reportReference reference) {
		int lateReturnsAmount= lateReturnsAmount();
		int loansAmount= LoanQueries.totalLoansAmount();
		ArrayList<Integer> bookLateCount=LoanQueries.totalBooksLateLoansAmount();
		return new ReportData(lateReturnsAmount/loansAmount, calcMedian(bookLateCount.size(), bookLateCount), calcDistribution(bookLateCount), reference);
	}

	public static ReportData generalDurationLateReturnsReportStat(reportReference reference) {
		ArrayList<Integer> lateReturnsDuration=LoanQueries.totalLateReturnsDuration();
		return new ReportData(calcAvg(lateReturnsDuration), calcMedian(lateReturnsDuration.size(), lateReturnsDuration), calcDistribution(lateReturnsDuration), reference);
	}
	
	public static ReportData calculateLoanReportStatistic(String sqlQuery, reportReference reference) throws SQLException {
		ArrayList<Integer> dataArray = new ArrayList<Integer>();
		st = mysqlConnection.conn.createStatement();
		ResultSet dataResult = st.executeQuery(sqlQuery);

		while (dataResult.next()) {
			LocalDate sDate = dataResult.getDate("StartDate").toLocalDate();
			LocalDate eDate = dataResult.getDate("ReturnDate").toLocalDate();
			Duration diff = Duration.between(sDate.atStartOfDay(), eDate.atStartOfDay());
			dataArray.add((int) diff.toDays());
		}
		if(dataArray.isEmpty())
			return new ReportData(0,0,new ArrayList<Object>(), reference);
		return new ReportData(calcAvg(dataArray), calcMedian(dataArray.size(), dataArray), calcDistribution(dataArray), reference);
	}
	
	public static double calcAvg(ArrayList<Integer> dataArray) {
		int sum=0;
		for(int i=0; i<dataArray.size(); i++)
			sum+=dataArray.get(i);
		return sum/dataArray.size();
	}
	
	public static double calcMedian(int dataAmount, ArrayList<Integer> dataArray) {
		Collections.sort(dataArray);
		if (dataAmount%2!=0)
			return dataArray.get((dataAmount+1)/2);
		else return((dataArray.get(dataAmount/2)+dataArray.get((dataAmount/2)+1))/2);
	}
	
	public static ArrayList<Object> calcDistribution(ArrayList<Integer> dataArray){
		Collections.sort(dataArray);
		ArrayList<Object> distribution= new ArrayList<Object>();
		distribution.add(dataArray);
		int rangeSize=((int)((dataArray.get(dataArray.size()-1)-dataArray.get(0))/10)+1);
		distribution.add(rangeSize);
		int[] valuesAmount= {0,0,0,0,0,0,0,0,0,0};
		for(int i=0; i<dataArray.size(); i++) {
			valuesAmount[dataArray.get(i)%rangeSize]++;}
		distribution.add(valuesAmount);
		return distribution;
	}

	public static String demandedBooksSQL() {
		return String.format(
				"select StartDate,ReturnDate FROM obl.loan l inner join obl.book b on b.CatalogNumber=l.BookCatalogNumber where LoanStatus='Finish' and isWanted=1;");
	}

	public static String regularBookSQL() {
		return String.format(
				"select StartDate,ReturnDate FROM obl.loan l inner join obl.book b on b.CatalogNumber=l.BookCatalogNumber where LoanStatus='Finish' and isWanted=0;");
	}

	public static int lateReturnsAmount() {
		String sqlQuery = String.format("SELECT * obl.loan WHERE LoanStatus='Late'");
		Statement s;
		try {
			s = mysqlConnection.conn.createStatement();
			ResultSet resultSet=s.executeQuery(sqlQuery);
			return resultSet.getFetchSize();
		}
		catch (SQLException e) {
			IAlert.ExceptionAlert(e);
			e.printStackTrace();
		}
		return 0;
	}
}
