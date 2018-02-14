/**
 * 
 */
package com.d2l2c.salary.management.data.service;

import java.util.List;

import com.d2l2c.salary.management.data.bean.user.User;

/**
 * @author dayanlazare
 *
 */
public interface UserService {

	public User findById(Long id);

	public User findByUsername(String username);

	public void save(User user);

	public void update(User user);

	public void deleteByUsername(String username);

	public List<User> findAll();

	boolean isUsernameUnique(Long id, String username);

}
