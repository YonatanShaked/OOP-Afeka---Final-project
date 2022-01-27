package model;

import java.util.ArrayList;

import controller.MySqlController;


public class Model {
	private Matrix theMatrix;
	private Player player, mvp;
	private ArrayList<Player> leaderboard;
	private ArrayList<Team> leaderboardT;
	private SingleLeague singleLeague;
	private TeamLeague teamLeague;

	public Model() {
		player = new Player("Player");
		leaderboard = controller.MySqlController.getTopPlayers();
		leaderboardT = controller.MySqlController.getTopTeams();
		mvp = controller.MySqlController.findMvp();
		this.singleLeague = MySqlController.getSingleLeague();
		this.teamLeague = MySqlController.getTeamLeague();
		//System.out.println(this.singleLeague.getPlayers().get(0).getFname() + "  " + this.teamLeague.getTeams().get(0).getName());
		update("Level 1");
	}

	public void setPlayer(Player p) {
		player = p;
	}
	
	public SingleLeague getSingleLeague() {
		return this.singleLeague;
	}
	
	public TeamLeague getTeamLeague() {
		return this.teamLeague;
	}

	public ArrayList<Player> getLeaderboard() {
		return leaderboard;
	}
	
	public ArrayList<Team> getLeaderboardT() {
		return leaderboardT;
	}
	
	public void setLeaderBoard(ArrayList<Player> leaderboard) {
		this.leaderboard = leaderboard;
	}
	
	public void setLeaderBoardT(ArrayList<Team> leaderboardT) {
		this.leaderboardT = leaderboardT;
	}
	
	public void setMvp(Player mvp) {
		this.mvp = mvp;
	}

	public void update(String kind) {
		switch (kind) {
		case "Level 1":
			theMatrix = new Matrix(1, player, this);
			break;
		case "Level 2":
			theMatrix = new Matrix(2, player, this);
			break;
		case "Level 3":
			theMatrix = new Matrix(3, player, this);
			break;
		}
	}

	public Matrix getMatrix() {
		return theMatrix;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Player getMvp() {
		return mvp;
	}

	public void changeElementStatus(int row, int col) {
		theMatrix.changeElementStatus(row, col);
	}
}