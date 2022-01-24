package model;

import java.util.ArrayList;
import java.util.Objects;

public class Team implements Comparable<Team>{
	private int tid;
	private String name;
	private int score;
	private ArrayList<Player> players;
	
	public Team(int tid, String name) {
		this.tid = tid;
		this.name = name;
		this.score = 0;
		this.players = new ArrayList<>();
	}
	
	public int getTid() {
		return tid;
	}
	
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

	@Override
	public int compareTo(Team compareT) {
		return this.score - compareT.getScore();
	}

	@Override
	public int hashCode() {
		return Objects.hash(tid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		return tid == other.tid;
	}

}