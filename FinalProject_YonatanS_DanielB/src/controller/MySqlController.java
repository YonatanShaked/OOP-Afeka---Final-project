package controller;

import java.sql.*;

public class MySqlController {
	public static void test() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbUrl = "jdbc:mysql://localhost/final_project_color_game";
			conn = DriverManager.getConnection(dbUrl, "root", "112234");

			PreparedStatement p_stmt = conn.prepareStatement("SELECT * FROM player");
			ResultSet p_rs = p_stmt.executeQuery();
			//int id = 0;

			while (p_rs.next()) {
				System.out.println(p_rs.getInt("PID") + "- " + p_rs.getString("Fname") + " "
						+ p_rs.getString("Lname"));
				//id = p_rs.getInt("actor_id");
			}

			//id++;
			//String newFirstName = "Yanti";
			//String newLastName = "Parazi";
			//Statement stmt2 = conn.createStatement();
			//String s = " INSERT INTO actor (actor_id, first_name, last_name) VALUES(" + id + ",'" + newFirstName + "','"
			//		+ newLastName + "') ";
			//int numAffectedRows = stmt2.executeUpdate(s);
			//System.out.println("number of affected rows = " + numAffectedRows);

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}