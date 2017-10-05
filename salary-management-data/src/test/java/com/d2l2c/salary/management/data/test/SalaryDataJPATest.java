/**
 * 
 */
package com.d2l2c.salary.management.data.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.d2l2c.salary.management.data.bean.Company;
import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.bean.Rate;
import com.d2l2c.salary.management.data.service.SalaryService;
import com.d2l2c.salary.management.data.spring.config.SalaryJPAConfig;
import com.d2l2c.salary.management.data.util.PaycheckUtil;

/**
 * @author dayanlazare
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SalaryJPAConfig.class })
@Transactional("salaryTransactionManager")
public class SalaryDataJPATest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SalaryDataJPATest.class);

	@Autowired
	private SalaryService salaryService;

	@Autowired
	private EntityManager em;

	@Before
	public void initializeDatabase() {
		Session session = em.unwrap(Session.class);
		session.doWork(new Work() {
			@Override
			public void execute(Connection connection) throws SQLException {
				try {
					File script = new File(getClass().getResource("/create-db.sql").getFile());
					RunScript.execute(connection, new FileReader(script));

					script = new File(getClass().getResource("/insert-db.sql").getFile());
					RunScript.execute(connection, new FileReader(script));
				} catch (FileNotFoundException e) {
					throw new RuntimeException("could not initialize with script");
				}
			}
		});
	}

	@Test
	public void listCompaniesTest() {
		try {
			List<Company> companies = salaryService.getCompanies();
			assertThat(companies.size(), is(2));
			companies.forEach(company -> {
				if (company.getId() == 1L) {
					assertThat(company.getCode(), is("MMI"));
				} else {
					assertThat(company.getCode(), is("MS3"));
				}
			});
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

	@Test
	public void listPaychecksTest() {
		try {
			List<Paycheck> paychecks = salaryService.getPaychecksByCompanyCodes("MS3", "MMI");
			assertThat(paychecks.size(), is(6));
			paychecks.forEach(paycheck -> {
				if (paycheck.getId() == 1L) {
					assertThat(paycheck.getCompany().getCode(), is("MMI"));
				} else if (paycheck.getId() == 4L) {
					assertThat(paycheck.getCompany().getCode(), is("MS3"));
				}
			});

			paychecks = salaryService.getPaychecksByCompanyCodes("MS3");
			assertThat(paychecks.size(), is(3));
			paychecks.forEach(paycheck -> {
				assertThat(paycheck.getCompany().getCode(), is("MS3"));
			});

			paychecks = salaryService.getPaychecksByCompanyCodes("MMI");
			assertThat(paychecks.size(), is(3));
			paychecks.forEach(paycheck -> {
				assertThat(paycheck.getCompany().getCode(), is("MMI"));
			});
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

	@Test
	public void listRatesTest() {
		try {
			List<Rate> rates = salaryService.getRatesByCompanyCodes("MS3", "MMI");
			assertThat(rates.size(), is(2));
			rates.forEach(rate -> {
				if (rate.getId() == 1L) {
					assertThat(rate.getCompany().getCode(), is("MMI"));
				} else if (rate.getId() == 4L) {
					assertThat(rate.getCompany().getCode(), is("MS3"));
				}
			});

			rates = salaryService.getRatesByCompanyCodes("MS3");
			assertThat(rates.size(), is(1));
			rates.forEach(rate -> {
				assertThat(rate.getCompany().getCode(), is("MS3"));
				assertThat(rate.getHourly(), is(new BigDecimal("100.00")));
			});

			rates = salaryService.getRatesByCompanyCodes("MMI");
			assertThat(rates.size(), is(1));
			rates.forEach(rate -> {
				assertThat(rate.getCompany().getCode(), is("MMI"));
				assertThat(rate.getHourly(), is(new BigDecimal("81.00")));
			});
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

	@Test
	public void getPaycheckYearsTest() {
		try {
			List<Integer> years = salaryService.getPaycheckYears();
			assertThat(years.size(), is(2));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

	@Test
	public void getPaycheckByYearsTest() {
		try {
			List<Paycheck> paychecks = salaryService.getPaychecksByYears(2017, 2016);
			assertThat(paychecks.size(), is(6));
			
			paychecks = salaryService.getPaychecksByYears(2017);
			assertThat(paychecks.size(), is(5));
			
			paychecks = salaryService.getPaychecksByYears(2016);
			assertThat(paychecks.size(), is(1));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

	@Test
	public void groupPaychecksByYearTest() {
		try {
			List<Paycheck> paychecks = salaryService.getPaychecksByYears(2017, 2016);
			assertThat(paychecks.size(), is(6));
			
			Map<Integer, Paycheck> paycheckMap = PaycheckUtil.groupPaychecksByYear(paychecks);
			assertThat(paycheckMap.size(), is(2));
			
			Paycheck paycheck2017 = paycheckMap.get(2017);
			
			assertThat(paycheck2017.getGrossAmount(), is(new BigDecimal("1000.00")));
			assertThat(paycheck2017.getNetPay(), is(new BigDecimal("500.00")));
			assertThat(paycheck2017.getReimbursement(), is(new BigDecimal("50.00")));
			
			Paycheck paycheck2016 = paycheckMap.get(2016);
			assertThat(paycheck2016.getGrossAmount(), is(new BigDecimal("200.00")));
			assertThat(paycheck2016.getNetPay(), is(new BigDecimal("100.00")));
			assertThat(paycheck2016.getReimbursement(), is(new BigDecimal("10.00")));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			fail();
		}
	}

}
