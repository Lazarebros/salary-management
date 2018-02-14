package com.d2l2c.salary.management.data.bean.user;

import java.io.Serializable;

public enum UserProfileType implements Serializable {

	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");

	String userProfileType;

	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType(){
		return userProfileType;
	}

}
