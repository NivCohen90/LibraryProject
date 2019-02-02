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

	public static ReportData calculateLoanReportStatistic(String sqlQuery) throws SQLException {
		double sum = 0;
		ArrayList<Integer> loanDuration = new ArrayList<Integer>();
		ReportData data = new ReportData(0, 0, loanDuration, reportReference.Demanded);

		Statement st;
		st = mysqlConnection.conn.createStatement();
		ResultSet dataResult = st.executeQuery(sqlQuery);
		int dataSize = dataResult.getFetchSize();
		while (dataResult.next()) {
			LocalDate sDate = dataResult.getDate("StartDate").toLocalDate();
			LocalDate eDate = dataResult.getDate("ReturnDate").toLocalDate();
			Duration diff = Duration.between(sDate.atStartOfDay(), eDate.atStartOfDay());
			sum += diff.toDays();
			loanDuration.add((int) diff.toDays());
		}
		sum = sum / dataSize;
		data.setAvg(sum);
		Collections.sort(loanDuration);
		if (dataSize % 2 != 0)
			sum = loanDuration.get((dataSize + 1) / 2);
		else {
			sum = loanDuration.get(dataSize / 2) + loanDuration.get((dataSize / 2) + 1);
			sum = sum / 2;
		}
		data.setMedian(sum);

		return data;
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
//	
//	public static ReportData calculateLateReturnReportStatistics() {
//		int bookAmount= BookQueries.totalBookAmount();
//		int lateReturnsAmount= lateReturnsAmount();
//		ArrayList<Integer> distributionArray = new ArrayList<Integer>();
//		ReportData rd= new ReportData(0, 0, distributionArray, reportReference.GeneralLatesAmount);
//	}
}
