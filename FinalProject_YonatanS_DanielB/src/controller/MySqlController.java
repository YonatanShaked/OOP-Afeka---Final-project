package controller;

import java.sql.*;
import java.util.ArrayList;

import model.Player;
import model.SingleLeague;
import model.Team;
import model.TeamLeague;

public class MySqlController {

	private static final String className = "com.mysql.cj.jdbc.Driver";
	private static final String dbUrl = "jdbc:mysql://localhost/final_project_color_game";
	private static final String user = "root";
	private static final String pass = "112234";

	public static void addPlayer(Player p) {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			String fName = p.getFname();
			String lName = p.getLname();
			String mName = p.getMname();
			int score = p.getScore();
			int tid = p.getTeam().getTid();
			Statement stmt = conn.createStatement();
			String s = " INSERT INTO player (Fname,Lname,Mname,Pscore,TID) VALUES ('" + fName + "','" + lName
					+ "','" + mName + "'," + score + "," + tid + ")";
			int numAffectedRows = stmt.executeUpdate(s);
			System.out.println("number of affected rows = " + numAffectedRows);

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void addTeam(Team t) {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			String name = t.getName();
			int score = t.getScore();
			Statement stmt = conn.createStatement();
			String s = " INSERT INTO team (Tname,Tscore) VALUES ('" + name + "'," +  score + ")";
			int numAffectedRows = stmt.executeUpdate(s);
			System.out.println("number of affected rows = " + numAffectedRows);

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void removePlayer(Player p) {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);
			
			Statement stmt = conn.createStatement();
			String s = "DELETE FROM player WHERE PID =" + p.getPid();
			int numAffectedRows = stmt.executeUpdate(s);
			System.out.println("number of affected rows = " + numAffectedRows);

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void removeTeam(Team t) {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);
			
			Statement stmt = conn.createStatement();
			String s = "DELETE FROM team WHERE TID =" + t.getTid();
			int numAffectedRows = stmt.executeUpdate(s);
			System.out.println("number of affected rows = " + numAffectedRows);

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void setPlayerToTeam(Player p, Team t) {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);
			
			int pid = p.getPid();
			int tid = t.getTid();
			Statement stmt = conn.createStatement();
			String s = "UPDATE player set TID = " + tid +" where PID = "+ pid;
			int numAffectedRows = stmt.executeUpdate(s);
			System.out.println("number of affected rows = " + numAffectedRows);

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void addSingleLeague(SingleLeague l) {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			String sDate = l.getSdate().toString();
			String eDate = l.getEdate().toString();
			int lid = l.getLid();
			Statement stmt = conn.createStatement();
			String s = "INSERT INTO league VALUES ('" + sDate + "','" + eDate + "')";
			int numAffectedRows = stmt.executeUpdate(s);
			System.out.println("number of affected rows = " + numAffectedRows);
			
			Statement stmt2 = conn.createStatement();
			String ss = "insert into singleleague values (" + lid +")";
			int numAffectedRowss = stmt2.executeUpdate(ss);
			System.out.println("number of affected rows = " + numAffectedRowss);

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void addTeamLeague(TeamLeague l) {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			String sDate = l.getSdate().toString();
			String eDate = l.getEdate().toString();
			int lid = l.getLid();
			int mvp_id = l.getMvp().getPid();
			Statement stmt = conn.createStatement();
			String s = "INSERT INTO league VALUES ('" + sDate + "','" + eDate + "')";
			int numAffectedRows = stmt.executeUpdate(s);
			System.out.println("number of affected rows = " + numAffectedRows);
			
			Statement stmt2 = conn.createStatement();
			String ss = "insert into teamleague values (" + mvp_id + "," + lid + ")";
			int numAffectedRowss = stmt2.executeUpdate(ss);
			System.out.println("number of affected rows = " + numAffectedRowss);

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void setPlayerScore(Player p) {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);
			
			int pid = p.getPid();
			int score = p.getScore();
			Statement stmt = conn.createStatement();
			String s = "UPDATE player set Pscore = " + score +" where PID = "+ pid;
			int numAffectedRows = stmt.executeUpdate(s);
			System.out.println("number of affected rows = " + numAffectedRows);

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static void setTeamScore(Team t) {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);
			
			int tid = t.getTid();
			Statement stmt = conn.createStatement();
			String s = "UPDATE team set Tscore = (Select avg(Pscore) from player where TID = " + tid + ") WHERE TID = " + tid;
			int numAffectedRows = stmt.executeUpdate(s);
			System.out.println("number of affected rows = " + numAffectedRows);

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public static ArrayList<Player> getTopPlayers() {
		ArrayList<Player> players = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			PreparedStatement p_stmt = conn.prepareStatement("select * from player order by Pscore desc limit 10");
			ResultSet p_rs = p_stmt.executeQuery();

			while (p_rs.next()) {
				Team t = findTeam(p_rs.getInt("TID"));
				Player p = new Player(p_rs.getInt("PID"), p_rs.getString("Fname"), p_rs.getString("Lname"),
						p_rs.getString("Mname"), p_rs.getInt("Pscore"), t);
				players.add(p);
			}

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return players;
	}

	public static int getHighestPid() {
		int pid = 0;
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			PreparedStatement p_stmt = conn.prepareStatement("select * from player order by PID desc limit 1");
			ResultSet p_rs = p_stmt.executeQuery();

			while (p_rs.next()) {
				pid = p_rs.getInt("PID");
			}

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return pid;
	}
	
	public static int getHighestTid() {
		int tid = 0;
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			PreparedStatement p_stmt = conn.prepareStatement("select * from team order by TID desc limit 1");
			ResultSet p_rs = p_stmt.executeQuery();

			while (p_rs.next()) {
				tid = p_rs.getInt("TID");
			}

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return tid;
	}

	public static ArrayList<Team> getTopTeams() {
		ArrayList<Team> teams = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			PreparedStatement p_stmt = conn
					.prepareStatement("select * from team where TID >1 order by Tscore desc limit 10");
			ResultSet p_rs = p_stmt.executeQuery();

			while (p_rs.next()) {
				Team t = new Team(p_rs.getInt("TID"), p_rs.getString("Tname"), p_rs.getInt("Tscore"));
				teams.add(t);
			}

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return teams;
	}

	public static Player findMvp() {
		Player p = null;
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			PreparedStatement p_stmt = conn.prepareStatement("select * from player order by Pscore desc limit 1");
			ResultSet p_rs = p_stmt.executeQuery();

			while (p_rs.next()) {
				Team t = findTeam(p_rs.getInt("TID"));
				p = new Player(p_rs.getInt("PID"), p_rs.getString("Fname"), p_rs.getString("Lname"),
						p_rs.getString("Mname"), p_rs.getInt("Pscore"), t);
			}

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return p;
	}

	public static Player findPlayer(String name) {
		String n = name.replaceAll(" ", "");
		Player p = null;
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			PreparedStatement p_stmt = conn.prepareStatement("select * from player where '" + n
					+ "' like (select CONCAT(Fname, Lname, Mname) AS ConcatenatedString) or '" + n
					+ "' like (select CONCAT(Fname, Lname) AS ConcatenatedString)");
			ResultSet p_rs = p_stmt.executeQuery();

			while (p_rs.next()) {
				Team t = findTeam(p_rs.getInt("TID"));
				p = new Player(p_rs.getInt("PID"), p_rs.getString("Fname"), p_rs.getString("Lname"),
						p_rs.getString("Mname"), p_rs.getInt("Pscore"), t);
			}

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return p;
	}
	
	public static Team findTeam(String name) {
		Team t = null;
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			PreparedStatement p_stmt = conn.prepareStatement("select * from team where Tname = '" + name +"'");
			ResultSet p_rs = p_stmt.executeQuery();

			while (p_rs.next()) {
				t = new Team(p_rs.getInt("TID"), p_rs.getString("Tname"), p_rs.getInt("Tscore"));
			}

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return t;
	}

	public static Team findTeam(int TID) {
		Team t = null;
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			PreparedStatement p_stmt = conn.prepareStatement("select * from team where TID = " + TID);
			ResultSet p_rs = p_stmt.executeQuery();

			while (p_rs.next()) {
				t = new Team(p_rs.getInt("TID"), p_rs.getString("Tname"), p_rs.getInt("Tscore"));
			}

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return t;
	}

	public static void test() {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			PreparedStatement p_stmt = conn.prepareStatement("SELECT * FROM player");
			ResultSet p_rs = p_stmt.executeQuery();

			while (p_rs.next()) {
				System.out.println(p_rs.getInt("PID") + "- " + p_rs.getString("Fname") + " " + p_rs.getString("Lname"));
			}
			
			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}