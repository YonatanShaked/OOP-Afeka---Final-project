package model;

import java.util.Objects;

public class Player implements Comparable<Player> {

	private int pid;
	private Team team;
	private String name;
	private String fname, lname, mname;
	private int score;
	
	public Player(int pid, String fname, String lname, String mname, int score, Team team) {
		this.pid = pid;
		this.fname = fname;
		this.lname = lname;
		this.mname = mname;
		this.score = score;
		this.team = team;
		
		if (this.mname == null)
			this.mname = "";
		
		if (this.lname == null)
			this.lname = "";
	}

	public Player(String name) {
		this.name = name;
		score = 0;
	}
	
	public int getPid()	{
		return pid;
	}
	
	public Team getTeam()	{
		return team;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public String getFname() {
		return fname;
	}
	
	public String getLname() {
		return lname;
	}
	
	public String getMname() {
		return mname;
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
	
	@Override
	public int hashCode() {
		return Objects.hash(pid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return pid == other.pid;
	}
}