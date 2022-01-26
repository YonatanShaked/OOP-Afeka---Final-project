package model;

import java.util.Objects;

public class Team implements Comparable<Team>{
	private int tid;
	private String name;
	private int score;
	
	public Team(int tid, String name, int score) {
		this.tid = tid;
		this.name = name;
		this.score = score;
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
	
	public void setScore(int score) {
		this.score = score;
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