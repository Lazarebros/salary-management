/**
 * 
 */
package com.d2l2c.salary.management.data.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.d2l2c.salary.management.data.bean.user.User;
import com.d2l2c.salary.management.data.bean.user.UserProfile;
import com.d2l2c.salary.management.data.service.UserProfileService;
import com.d2l2c.salary.management.data.service.UserService;
import com.d2l2c.salary.management.data.spring.config.SalaryJPAConfig;

/**
 * @author dayanlazare
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SalaryJPAConfig.class })
public class UserJPATest {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserJPATest.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	PersistentTokenRepository tokenRepository;

	@Autowired
	private EntityManager entityManager;

	@Before
	public void initializeDatabase() {
		Session session = entityManager.unwrap(Session.class);
		session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				try {
					File script = new File(getClass().getResource("/create-user-db.sql").getFile());
					RunScript.execute(connection, new FileReader(script));

					script = new File(getClass().getResource("/insert-user-db.sql").getFile());
					RunScript.execute(connection, new FileReader(script));
				} catch (FileNotFoundException e) {
					throw new RuntimeException("could not initialize with script");
				}
			}
		});
	}

	@Transactional("salaryTransactionManager")
	@Test
	public void listUsersTest() {
		List<User> users;
		try {
			users = userService.findAll();
			assertFalse(users.isEmpty());
			users.forEach(user -> {
				try {
					assertNotNull(user.getPassword());
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
					fail();
				}
			});
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

	@Transactional("salaryTransactionManager")
	@Test
	public void findByIdTest() {
		try {
			User user = userService.findById(1L);
			assertNotNull(user);
			assertThat(user.getUsername(), is("Sunil"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

	@Transactional("salaryTransactionManager")
	@Test
	public void findByUsernameTest() {
		try {
			User user = userService.findByUsername("Sunil");
			assertNotNull(user);
			assertThat(user.getId(), is(1L));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

	@Transactional("salaryTransactionManager")
	@Test
	public void listUserProfilesTest() {
		List<UserProfile> userProfiles;
		try {
			userProfiles = userProfileService.findAll();
			assertFalse(userProfiles.isEmpty());
			userProfiles.forEach(userProfile -> {
				try {
					assertNotNull(userProfile.getType());
				} catch (Exception e) {
					LOGGER.error(e.getMessage());
					fail();
				}
			});
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

	@Transactional("salaryTransactionManager")
	@Test
	public void getPersistentLoginTest() {
		PersistentRememberMeToken rememberMeToken;
		try {
			rememberMeToken = tokenRepository.getTokenForSeries("test");
			assertNotNull(rememberMeToken);
			assertThat(rememberMeToken.getUsername(), is("test"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

	@Transactional("salaryTransactionManager")
	@Test
	public void savePersistentLoginTest() {
		PersistentRememberMeToken rememberMeToken = new PersistentRememberMeToken("test1", "test1", "test1", new Date());
		try {
			tokenRepository.createNewToken(rememberMeToken);
			
			PersistentRememberMeToken entity = tokenRepository.getTokenForSeries("test1");
			
			assertNotNull(entity);
			assertThat(entity.getUsername(), is("test1"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

}
