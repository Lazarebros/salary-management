/**
 * 
 */
package com.d2l2c.salary.management.data.test;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.service.PaycheckService;
import com.d2l2c.salary.management.data.spring.config.SalaryJPAConfig;

/**
 * @author dayanlazare
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SalaryJPAConfig.class })
public class SalaryDataJPATest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SalaryDataJPATest.class);

	@Autowired
	private PaycheckService paycheckService;

	@Transactional("salaryTransactionManager")
	@Test
	public void listUsersTest() {
		Iterable<Paycheck> paychecks;
		try {
			paychecks = paycheckService.getPaychecks();
			assertThat(paychecks, is(not(Optional.empty())));
			paychecks.forEach(paycheck -> {
				try {
					assertNotNull(paycheck.getCompanyName());
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

}
