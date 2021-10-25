package model;

import java.util.ArrayList;

import controller.CtrlFileHandler;

public class Model {
	private Matrix theMatrix;
	private Player player;
	private ArrayList<Player> leaderboard;

	public Model() {
		player = new Player("Player");
		leaderboard = CtrlFileHandler.loadFile();
		update("Level 1");
	}

	public void setPlayer(Player p) {
		player = p;
	}

	public ArrayList<Player> getLeaderboard() {
		return leaderboard;
	}

	public void update(String kind) {
		switch (kind) {
		case "Level 1":
			theMatrix = new Matrix(1, player, leaderboard);
			break;
		case "Level 2":
			theMatrix = new Matrix(2, player, leaderboard);
			break;
		case "Level 3":
			theMatrix = new Matrix(3, player, leaderboard);
			break;
		}
	}

	public Matrix getMatrix() {
		return theMatrix;
	}

	public Player getPlayer() {
		return player;
	}

	public void changeElementStatus(int row, int col) {
		theMatrix.changeElementStatus(row, col);
	}
}