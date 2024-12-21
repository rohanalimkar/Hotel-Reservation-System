package Repository;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DBConfig {
	protected static Connection conn;
	protected static PreparedStatement ps;
	protected static ResultSet rs;
	private static DBConfig config;

	private DBConfig() {
		try {
			File f = new File(".");
			String path = f.getAbsolutePath();
			FileInputStream inputStream = new FileInputStream(path + "\\src\\main\\resources\\application.properties");
			Properties p = new Properties();
			p.load(inputStream);
			String driverClassName = p.getProperty("driver");
			String userName = p.getProperty("userName");
			String password = p.getProperty("password");
			String url = p.getProperty("url");

//			System.out.println("Driver Name : " + driverClassName);
//			System.out.println("Username : " + userName);
//			System.out.println("password :" + password);
//			System.out.println("Url :" + url);
//			System.out.println();
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, userName, password);
//			if (conn != null) {
//				System.out.println("Connected successfully");
//			} else {
//				System.out.println("Connected failed !!!");
//			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static DBConfig getInstance() {
		if (config == null) {
			config = new DBConfig();
		}
		return config;
	}

	public static Connection getConnection() {
		return conn;
	}

	public static Connection getConn() {
		return conn;
	}

	public static PreparedStatement getStatement() {

		return ps;
	}

	public static ResultSet getResultSet() {
		return rs;
	}
}
