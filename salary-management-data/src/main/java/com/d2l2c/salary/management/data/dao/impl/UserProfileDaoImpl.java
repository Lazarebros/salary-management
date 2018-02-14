package com.d2l2c.salary.management.data.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.d2l2c.salary.management.data.bean.user.UserProfile;
import com.d2l2c.salary.management.data.dao.UserProfileDao;

@Repository("userProfileDao")
public class UserProfileDaoImpl extends AbstractDao<Long, UserProfile> implements UserProfileDao {

	@SuppressWarnings("unchecked")
	public UserProfile findByType(String type) {
		UserProfile userProfile = null;

		Query query = entityManager
				.createQuery("Select userProfile From UserProfile userProfile Where userProfile.type = :type");
		query.setParameter("type", type);
		query.setMaxResults(1);

		List<UserProfile> listUserProfiles = query.getResultList();
		if (!listUserProfiles.isEmpty()) {
			userProfile = listUserProfiles.get(0);
		}
		return userProfile;
	}

	@Override
	public UserProfile findById(Long id) {
		return findByKey(id);
	}

}
