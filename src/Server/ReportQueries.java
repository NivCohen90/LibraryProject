package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import SystemObjects.ReportData;
import SystemObjects.GeneralData.reportReference;

public class ReportQueries {

	public static ReportData calculateStatistic(String getDatesSQL) throws SQLException {
		double sum = 0;
		ArrayList<Integer> loanDuration = new ArrayList<Integer>();
		ReportData data = new ReportData(0, 0, loanDuration, reportReference.Demanded);

		Statement st;
			st = mysqlConnection.conn.createStatement();
			ResultSet dataResult = st.executeQuery(getDatesSQL);
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

}
