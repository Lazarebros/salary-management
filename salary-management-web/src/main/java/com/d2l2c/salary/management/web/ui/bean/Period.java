package com.d2l2c.salary.management.web.ui.bean;

import java.io.Serializable;

public enum Period implements Serializable{
	MONTHLY("MONTHLY"),
	YEARLY("YEARLY");
	
	String period;
	
	private Period(String period){
		this.period = period;
	}
	
	public String getUserProfileType(){
		return period;
	}
	
}
