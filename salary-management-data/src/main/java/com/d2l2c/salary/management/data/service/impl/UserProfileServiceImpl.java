package com.d2l2c.salary.management.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2l2c.salary.management.data.bean.user.UserProfile;
import com.d2l2c.salary.management.data.dao.UserProfileDao;
import com.d2l2c.salary.management.data.service.UserProfileService;

@Service("userProfileService")
@Transactional("salaryTransactionManager")
public class UserProfileServiceImpl implements UserProfileService {

	@Autowired
	UserProfileDao dao;

	public UserProfile findById(Long id) {
		return dao.findById(id);
	}

	public UserProfile findByType(String type) {
		return dao.findByType(type);
	}

	public List<UserProfile> findAll() {
		return dao.findAll();
	}

}
