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
	private static final String pass = "PASSWORD";

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
			
			String s = " INSERT INTO player (Fname,Lname,Mname,Pscore,TID) VALUES ('" + fName + "','" + lName
					+ "','" + mName + "'," + score + "," + tid + ")";
			PreparedStatement p_stmt = conn.prepareStatement(s);
			int numAffectedRows = p_stmt.executeUpdate();
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
			
			String s = " INSERT INTO team (Tname,Tscore) VALUES ('" + name + "'," +  score + ")";
			PreparedStatement p_stmt = conn.prepareStatement(s);
			int numAffectedRows = p_stmt.executeUpdate();
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
			
			String s = "DELETE FROM player WHERE PID =" + p.getPid();
			PreparedStatement p_stmt = conn.prepareStatement(s);
			int numAffectedRows = p_stmt.executeUpdate();
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
			
			String s = "DELETE FROM team WHERE TID =" + t.getTid();
			PreparedStatement p_stmt = conn.prepareStatement(s);
			int numAffectedRows = p_stmt.executeUpdate();
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
			
			String s = "UPDATE player set TID = " + tid +" where PID = "+ pid;
			PreparedStatement p_stmt = conn.prepareStatement(s);
			int numAffectedRows = p_stmt.executeUpdate();
			System.out.println("number of affected rows = " + numAffectedRows);

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public static SingleLeague getSingleLeague() {
		SingleLeague l = null;
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			int lid = 0;
			Date sDate;
			Date eDate;
			
			PreparedStatement p_stmt = conn.prepareStatement("select * from league order by sdate desc limit 2");
			ResultSet p_rs = p_stmt.executeQuery();

			while (p_rs.next()) {
				lid = p_rs.getInt("LID");
				sDate = p_rs.getDate("Sdate");
				eDate = p_rs.getDate("Edate");
				if (lid % 2 == 1 ) {
					ArrayList<Player> players = getAllPlayers();
					l = new SingleLeague(lid, sDate, eDate, players);
				}
			}

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return l;
	}
	
	public static TeamLeague getTeamLeague() {
		TeamLeague l = null;
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			int lid = 0;
			Date sDate;
			Date eDate;
			
			PreparedStatement p_stmt = conn.prepareStatement("select * from league order by sdate desc limit 2");
			ResultSet p_rs = p_stmt.executeQuery();

			while (p_rs.next()) {
				lid = p_rs.getInt("LID");
				sDate = p_rs.getDate("Sdate");
				eDate = p_rs.getDate("Edate");
				if (lid % 2 == 0 ) {
					ArrayList<Team> teams = getAllTeams();
					Player mvp = findMvp();
					l = new TeamLeague(lid, sDate, eDate, teams, mvp);
				}
			}

			conn.close();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return l;
	}

	public static void addSingleLeague(SingleLeague l) {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			String sDate = l.getSdate().toString();
			String eDate = l.getEdate().toString();
			int lid = l.getLid();
			
			String s = "INSERT INTO league VALUES ('" + sDate + "','" + eDate + "')";
			PreparedStatement p_stmt = conn.prepareStatement(s);
			int numAffectedRows = p_stmt.executeUpdate();
			System.out.println("number of affected rows = " + numAffectedRows);
			
			String ss = "insert into singleleague values (" + lid +")";
			PreparedStatement p_stmt2 = conn.prepareStatement(ss);
			int numAffectedRowss = p_stmt2.executeUpdate();
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
			
			String s = "INSERT INTO league VALUES ('" + sDate + "','" + eDate + "')";
			PreparedStatement p_stmt = conn.prepareStatement(s);
			int numAffectedRows = p_stmt.executeUpdate();
			System.out.println("number of affected rows = " + numAffectedRows);
			
			String ss = "insert into teamleague values (" + mvp_id + "," + lid + ")";
			PreparedStatement p_stmt2 = conn.prepareStatement(ss);
			int numAffectedRowss = p_stmt2.executeUpdate();
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

			String s = "UPDATE player set Pscore = " + score +" where PID = "+ pid;
			PreparedStatement p_stmt = conn.prepareStatement(s);
			int numAffectedRows = p_stmt.executeUpdate();
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

			String s = "UPDATE team set Tscore = (Select avg(Pscore) from player where TID = " + tid + ") WHERE TID = " + tid;
			PreparedStatement p_stmt = conn.prepareStatement(s);
			int numAffectedRows = p_stmt.executeUpdate();
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
	
	public static ArrayList<Player> getAllPlayers() {
		ArrayList<Player> players = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			PreparedStatement p_stmt = conn.prepareStatement("select * from player order by Pscore desc");
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
	
	public static ArrayList<Team> getAllTeams() {
		ArrayList<Team> teams = new ArrayList<>();
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(dbUrl, user, pass);

			PreparedStatement p_stmt = conn
					.prepareStatement("select * from team where TID >1 order by Tscore desc");
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
}