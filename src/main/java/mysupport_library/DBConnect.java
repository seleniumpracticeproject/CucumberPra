package mysupport_library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {
	public static String Getclienthealthinfo(String Username, String RecordDate) {
		//retrive cloud id, record date, account type, account id, steps, calories, weight, energy burned, energy burned goal, excerse time, excercise time goal, stand hours, stand hours goal
		Connection con = null;
		String StudentId = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("write the jdbc url here");
					Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("use select query");
			while(rs.next())
				System.out.println(rs.getString(1));
				StudentId = rs.getString(1);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return StudentId;
	}

}
