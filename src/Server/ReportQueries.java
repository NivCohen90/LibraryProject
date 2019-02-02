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

public class ReportQueries {
	private static Statement st;
	
	public static ReportData calculateLateReturnReportStatistics(String sqlQuery, reportReference reference) {
		int bookAmount= BookQueries.totalBookAmount();
		int lateReturnsAmount= lateReturnsAmount();
		int loansAmount= LoanQueries.totalLoansAmount();
		
		ArrayList<Integer> generalLoansDuration = new ArrayList<Integer>();
		return reportStatistics(generalLoansDuration, reference);
	}

	public static ReportData calculateLoanReportStatistic(String sqlQuery, reportReference reference) throws SQLException {
		ArrayList<Integer> loanDurationsArray = new ArrayList<Integer>();
		st = mysqlConnection.conn.createStatement();
		ResultSet dataResult = st.executeQuery(sqlQuery);
		
		while (dataResult.next()) {
			LocalDate sDate = dataResult.getDate("StartDate").toLocalDate();
			LocalDate eDate = dataResult.getDate("ReturnDate").toLocalDate();
			Duration diff = Duration.between(sDate.atStartOfDay(), eDate.atStartOfDay());
			loanDurationsArray.add((int) diff.toDays());
		}
		Collections.sort(loanDurationsArray);
		return reportStatistics(loanDurationsArray, reference);
	}
	
	public static ReportData reportStatistics(ArrayList<Integer> dataArray, reportReference reference) {
		
		return new ReportData(calcAvg(dataArray), calcMedian(dataArray.size(), dataArray), calcDistribution(dataArray), reference);
	}
	
	public static double calcAvg(ArrayList<Integer> dataArray) {
		int sum=0;
		for(int i=0; i<dataArray.size(); i++)
			sum+=dataArray.get(i);
		return sum/dataArray.size();
	}
	
	public static double calcMedian(int dataAmount, ArrayList<Integer> dataArray) {
		if (dataAmount%2!=0)
			return dataArray.get((dataAmount+1)/2);
		else return((dataArray.get(dataAmount/2)+dataArray.get((dataAmount/2)+1))/2);
	}
	
	public static ArrayList<Object> calcDistribution(ArrayList<Integer> dataArray){
		ArrayList<Object> distribution= new ArrayList<Object>();
		distribution.add(dataArray);
		int rangeSize=((int)Math.ceil((dataArray.get(dataArray.size())-dataArray.get(0))/10));
		distribution.add(rangeSize);
		int[] valuesAmount= {0,0,0,0,0,0,0,0,0,0};
		for(int i=0; i<dataArray.size(); i++)
			valuesAmount[dataArray.get(i)/rangeSize]++;
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
