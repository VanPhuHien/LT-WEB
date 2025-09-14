package hien.project.configs;

import java.sql.Connection;
import java.sql.DriverManager;

import hien.project.configs.ConnectDB;

public class ConnectDB {

	private final String serverName = "localhost";
	private final String dbName = "VD1";
	private final String portNumber = "1433";
	private final String userID = "sa";
	private final String password = "123456";

	public Connection getConnection() throws Exception {
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		return DriverManager.getConnection(url, userID, password);
	}

	public static void main(String[] args) {
		try {
			System.out.println(new ConnectDB().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
