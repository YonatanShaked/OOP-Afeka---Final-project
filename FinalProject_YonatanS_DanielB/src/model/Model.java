package model;

import java.util.ArrayList;


public class Model {
	private Matrix theMatrix;
	private Player player, mvp;
	private ArrayList<Player> leaderboard;
	private ArrayList<Team> leaderboardT;

	public Model() {
		player = new Player("Player");
		//leaderboard = controller.CtrlFileHandler.loadFile();
		leaderboard = controller.MySqlController.getTopPlayers();
		leaderboardT = controller.MySqlController.getTopTeams();
		mvp = controller.MySqlController.findMvp();
		update("Level 1");
	}

	public void setPlayer(Player p) {
		player = p;
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
			theMatrix = new Matrix(1, player, leaderboard, leaderboardT, this);
			break;
		case "Level 2":
			theMatrix = new Matrix(2, player, leaderboard, leaderboardT, this);
			break;
		case "Level 3":
			theMatrix = new Matrix(3, player, leaderboard, leaderboardT, this);
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