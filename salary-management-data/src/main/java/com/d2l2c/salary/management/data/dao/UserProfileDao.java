package com.d2l2c.salary.management.data.dao;

import java.util.List;

import com.d2l2c.salary.management.data.bean.user.UserProfile;

public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(Long id);

}
