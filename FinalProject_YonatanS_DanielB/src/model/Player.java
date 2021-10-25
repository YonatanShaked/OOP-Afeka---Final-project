package model;

public class Player implements Comparable<Player> {

	private String name;
	private int score;

	public Player(String name) {
		this.name = name;
		score = 0;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public int compareTo(Player compareP) {
		return this.score - compareP.getScore();
	}
}