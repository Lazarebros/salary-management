package com.d2l2c.salary.management.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2l2c.salary.management.data.bean.user.User;
import com.d2l2c.salary.management.data.dao.UserDao;
import com.d2l2c.salary.management.data.service.UserService;

@Service("userService")
@Transactional("salaryTransactionManager")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User findById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public void save(User user) {
		String encriptedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encriptedPassword);
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		User entity = userDao.findById(user.getId());
		if (entity != null) {
			entity.setUsername(user.getUsername());
			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	@Override
	public void deleteByUsername(String username) {
		User user = this.findByUsername(username);
		if (user != null) {
			userDao.delete(user);
		}
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public boolean isUsernameUnique(Long id, String username) {
		User user = this.findByUsername(username);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

}
