package model;

import java.util.ArrayList;
import java.util.Date;

public class SingleLeague extends League {

	private ArrayList<Player> players;
	
	public SingleLeague(int lid, Date sDate, Date eDate) {
		super(lid, sDate, eDate);
		this.players = new ArrayList<>();
	}
	
	public SingleLeague(int lid, Date sDate, Date eDate, ArrayList<Player> players) {
		super(lid, sDate, eDate);
		this.players = players;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}

}