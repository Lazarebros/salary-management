package com.d2l2c.salary.management.data.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.d2l2c.salary.management.data.bean.user.User;
import com.d2l2c.salary.management.data.dao.UserDao;

/**
 * @author dayanlazare
 *
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public User findByUsername(String username) {
		User user = null;

		Query query = entityManager.createQuery("Select user From User user Where user.username = :username");
		query.setParameter("username", username);
		query.setMaxResults(1);

		List<User> listUsers = query.getResultList();
		if (!listUsers.isEmpty()) {
			user = listUsers.get(0);
		}
		return user;
	}

	@Override
	public User findById(Long id) {
		return findByKey(id);
	}

}
