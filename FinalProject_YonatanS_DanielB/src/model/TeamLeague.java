package model;

import java.util.ArrayList;
import java.util.Date;

public class TeamLeague extends League {
	
	private Player mvp;
	private ArrayList<Integer> tidList;

	public TeamLeague(int lid, Date sDate, Date eDate) {
		super(lid, sDate, eDate);
		tidList = new ArrayList<Integer>();
		mvp = null;
	}
	
	public TeamLeague(int lid, Date sDate, Date eDate, ArrayList<Integer> tidList, Player mvp) {
		super(lid, sDate, eDate);
		this.tidList = tidList;
		this.mvp = mvp;
	}
	
	public Player getMvp() {
		return mvp;
	}
	
	public void setMvp(Player mvp) {
		this.mvp = mvp;
	}
	
	public ArrayList<Integer> getTidList() {
		return tidList;
	}

}
