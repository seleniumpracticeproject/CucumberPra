package mysupport_library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.sql.PreparedStatement;

public class DBConnection {
public static Properties properties = Settings.getInstance();
public Connection AzureDBConnect() {
	//connect to database
	String user = properties.getProperty("username");
	String password = properties.getProperty("password");
	String url = String.format(properties.getProperty("jdbc_url"), user, password);
	Connection connection = null;
	try {
		connection = DriverManager.getConnection(url);
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	return connection;
}
public ResultSet executeQuery(Connection connection, String selectSql) {
	ResultSet resultSet = null;
	try {
		Statement statement = connection.createStatement();
		resultSet = statement.executeQuery(selectSql);
		connection.close();
	}catch (Exception e) {
		e.printStackTrace();
	}
	return resultSet;
}

}
