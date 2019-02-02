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
	
//	public static ReportData calculateLateReturnReportStatistics() {
//		int bookAmount= BookQueries.totalBookAmount();
//		int lateReturnsAmount= lateReturnsAmount();
//		int late
//		ArrayList<Integer> generalLoansDuration = new ArrayList<Integer>();
//		ReportData rd= new ReportData(0, 0, generalLoansDuration, reportReference.GeneralLatesDuration);
//		
//	}

	public static ReportData calculateLoanReportStatistic(String sqlQuery) throws SQLException {
		double sum = 0;
		ArrayList<Integer> loanDurationsArray = new ArrayList<Integer>();
		ArrayList<Object> distribution= new ArrayList<Object>();
		ReportData data = new ReportData(0, 0, distribution, reportReference.Demanded);

		Statement st;
		st = mysqlConnection.conn.createStatement();
		ResultSet dataResult = st.executeQuery(sqlQuery);
		int dataSize = dataResult.getFetchSize();
		while (dataResult.next()) {
			LocalDate sDate = dataResult.getDate("StartDate").toLocalDate();
			LocalDate eDate = dataResult.getDate("ReturnDate").toLocalDate();
			Duration diff = Duration.between(sDate.atStartOfDay(), eDate.atStartOfDay());
			sum += diff.toDays();
			loanDurationsArray.add((int) diff.toDays());
		}
		data.setAvg(calcAvg(sum, dataSize));
		Collections.sort(loanDurationsArray);
		data.setMedian(calcMedian(dataSize, loanDurationsArray));
		
		return data;
	}
	
	public static double calcAvg(double dataSum, int dataAmount) {
		return dataSum/dataAmount;
	}
	
	public static double calcMedian(int dataAmount, ArrayList<Integer> dataArray) {
		if (dataAmount%2!=0)
			return dataArray.get((dataAmount+1)/2);
		else return((dataArray.get(dataAmount/2)+dataArray.get((dataAmount/2)+1))/2);
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
