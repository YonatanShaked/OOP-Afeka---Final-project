package model;

import java.util.Date;

public abstract class League {

	private int lid;
	private Date sDate, eDate;
	
	public League(int lid, Date sDate, Date eDate) {
		this.lid = lid;
		this.sDate = sDate;
		this.eDate = eDate;
	}
	
	public int getLid() {
		return lid;
	}
	
	public Date getSdate() {
		return sDate;
	}
	
	public Date getEdate() {
		return eDate;
	}
	
}
