package model;

import java.util.ArrayList;
import java.util.Date;

public class SingleLeague extends League {

	private ArrayList<Integer> pidList;
	
	public SingleLeague(int lid, Date sDate, Date eDate) {
		super(lid, sDate, eDate);
		pidList = new ArrayList<Integer>();
	}
	
	public SingleLeague(int lid, Date sDate, Date eDate, ArrayList<Integer> pidList) {
		super(lid, sDate, eDate);
		this.pidList = pidList;
	}
	
	public ArrayList<Integer> getPidList() {
		return pidList;
	}

}