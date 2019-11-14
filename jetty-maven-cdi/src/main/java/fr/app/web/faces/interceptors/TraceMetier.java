package fr.app.web.faces.interceptors;

import java.util.Date;

public class TraceMetier {
	
	
	private String action;
	
	private Date dateAction = new Date();
	
	

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	

	public Date getDateAction() {
		return dateAction;
	}

	public void setDateAction(Date dateAction) {
		this.dateAction = dateAction;
	}
	
	
	
	

}
