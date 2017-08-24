package com.d2l2c.salary.management.data.spring.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.d2l2c.common.util.date.DateUtil;
import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.service.PaycheckService;
import com.d2l2c.salary.management.data.spring.config.PersistenceJPAConfig;

public class SpringHibernateMain {

	public static void main(String[] args) {
		//save();
		display();
	}

	public static void save() {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);) {
			PaycheckService paycheckService = context.getBean(PaycheckService.class);
			
			Paycheck paycheck = new Paycheck();

			Date startDate = DateUtil.stringToDate("08/15/2017", DateUtil.DATE_PATTERN_US_YYYY);
			Date endDate = DateUtil.stringToDate("08/31/2017", DateUtil.DATE_PATTERN_US_YYYY);

			paycheck.setStartDate(startDate);
			paycheck.setEndDate(endDate);
			paycheck.setNumberOfHours(20L);
			paycheck.setHourlyRate(new BigDecimal(100));
			paycheck.setGrossAmount(new BigDecimal(2000));
			paycheck.setNetPay(new BigDecimal(1670));
			paycheck.setReimbursement(new BigDecimal(20));

			paycheckService.save(paycheck);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void display() {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistenceJPAConfig.class);) {
			PaycheckService paycheckService = context.getBean(PaycheckService.class);
			// Get Paychecks
			List<Paycheck> paychecks = paycheckService.getPaychecks();
			for (Paycheck pchk : paychecks) {
				System.out.println(pchk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
