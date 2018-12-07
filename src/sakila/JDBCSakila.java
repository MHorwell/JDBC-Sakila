package sakila;

import java.sql.*;

public class JDBCSakila {

	static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/Sakila";
	static final String USER = "root";
	static final String PASS = "";

	public void accessDB() {
		Connection conn = null;
		Statement stmt = null;

		try {

			Class.forName(JDBC_Driver);
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String selectActors = "SELECT actor_id, first_name, last_name FROM Actor";
			ResultSet results = stmt.executeQuery(selectActors);
			while (results.next()) {
				int id = results.getInt("actor_id");
				String name = results.getString("first_name");
				String lastname = results.getString("last_name");
				System.out.println("ID: " + id + ", name: " + name + " " + lastname);
			}
			results.close();

		} catch (SQLException sqle) {
			System.out.println(sqle);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqle) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException sqle) {
				System.out.println(sqle);
			}
			System.out.println("Conenction closed.");
		}

	}
}
