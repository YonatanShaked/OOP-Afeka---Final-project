package model;

import java.util.ArrayList;
import java.util.Date;

public class TeamLeague extends League {
	
	private Player mvp;
	private ArrayList<Team> teams;

	public TeamLeague(int lid, Date sDate, Date eDate, Player mvp) {
		super(lid, sDate, eDate);
		this.teams = new ArrayList<>();
		mvp = null;
	}
	
	public TeamLeague(int lid, Date sDate, Date eDate, ArrayList<Team> teams, Player mvp) {
		super(lid, sDate, eDate);
		this.teams = teams;
		this.mvp = mvp;
	}
	
	public Player getMvp() {
		return mvp;
	}
	
	public void setMvp(Player mvp) {
		this.mvp = mvp;
	}
	
	public ArrayList<Team> getTeams() {
		return teams;
	}

}
