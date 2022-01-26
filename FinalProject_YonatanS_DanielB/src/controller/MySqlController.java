package controller;

import java.sql.*;
import java.util.ArrayList;

import model.Player;
import model.SingleLeague;
import model.Team;
import model.TeamLeague;

public class MySqlController {
	
	public static void addPlayer(Player p) {
		
	}
	
	public static void addTeam(Team t) {
		
	}
	
	public static void removeTeam(Team t) {
		
	}
	
	public static void addPlayerToTeam(Player p, Team t) {
		
	}
	
	public static void RemovePlayerFromTeam(Player p, Team t) {
		
	}
	
	public static void addSingleLeague(SingleLeague l) {
		
	}
	
	public static void addTeamLeague(TeamLeague l) {
		
	}
	
	public static void setPlayerScore(Player p) {
		
	}
	
	public static void setTeamScore(Team t) {
		
	}
	
	public static ArrayList<Player> getTopPlayers() {
		ArrayList<Player> players = new ArrayList<>();
		return players;
	}
	
	public static ArrayList<Team> getTopTeams() {
		ArrayList<Team> teams = new ArrayList<>();
		return teams;
	}
	
	public static Player findPlayer(String name) {
		// find with sql
		Player p = new Player(name);
		return p;
	}
	
	public static Team findTeam(String name) {
		// find with sql
		Team t = new Team(0 ,name);
		return t;
	}
	
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