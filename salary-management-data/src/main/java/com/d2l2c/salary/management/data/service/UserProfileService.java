package com.d2l2c.salary.management.data.service;

import java.util.List;

import com.d2l2c.salary.management.data.bean.user.UserProfile;


public interface UserProfileService {

	UserProfile findById(Long id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
