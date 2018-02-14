package com.d2l2c.salary.management.data.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.d2l2c.salary.management.data.bean.user.PersistentLogin;

@Repository("tokenRepositoryDao")
@Transactional("salaryTransactionManager")
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin> implements PersistentTokenRepository {

	static final Logger logger = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		logger.info("Creating Token for user : {}", token.getUsername());
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLast_used(token.getDate());
		save(persistentLogin);

	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		logger.info("Fetch Token if any for seriesId : {}", seriesId);
		try {
			PersistentLogin persistentLogin = findByKey(seriesId);

			return new PersistentRememberMeToken(
					persistentLogin.getUsername(), 
					persistentLogin.getSeries(),
					persistentLogin.getToken(), 
					persistentLogin.getLast_used());
		} catch (Exception e) {
			logger.info("Token not found...");
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeUserTokens(String username) {
		logger.info("Removing Token if any for user : {}", username);
		
		Query query = entityManager.createQuery("Select persistentLogin From PersistentLogin persistentLogin Where persistentLogin.username = :username");
		query.setParameter("username", username);
		query.setMaxResults(1);

		List<PersistentLogin> listPersistentLogins = query.getResultList();
		if (!listPersistentLogins.isEmpty()) {
			PersistentLogin persistentLogin = listPersistentLogins.get(0);
			if (persistentLogin != null) {
				logger.info("rememberMe was selected");
				delete(persistentLogin);
			}
		}
	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		logger.info("Updating Token for seriesId : {}", seriesId);
		PersistentLogin persistentLogin = findByKey(seriesId);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLast_used(lastUsed);
		update(persistentLogin);
	}

}
