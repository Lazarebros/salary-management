package com.d2l2c.salary.management.web.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.service.SalaryService;
import com.d2l2c.salary.management.web.ui.bean.EChartBean;
import com.d2l2c.salary.management.web.ui.view.PaycheckView;

/**
 * @author dayanlazare
 *
 */
public class PaycheckController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaycheckController.class);

	@Autowired
	SalaryService salaryService;

	private PaycheckView paycheckView;
	
	private EChartBean eChartBean;

	public void setSalaryService(SalaryService salaryService) {
		this.salaryService = salaryService;
	}

	public void setPaycheckView(PaycheckView paycheckBean) {
		this.paycheckView = paycheckBean;
	}
	
	public EChartBean geteChartBean() {
		return eChartBean;
	}

	public void seteChartBean(EChartBean eChartBean) {
		this.eChartBean = eChartBean;
	}

	public String showPaychecks() {
		String page = null;
		String message = null;
		try {
			this.retreivePaychecks();
			page = "paychecks";
		} catch (Exception e) {
			message = "Something went wrong...";
			LOGGER.error(e.getMessage(), e);
		}
		if(message != null) {
			LOGGER.error(message);
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		}
		return page;
	}
	
	private void retreivePaychecks() throws Exception {
		paycheckView.getPaycheckBeanMap().clear();
		List<String> companyCodes = Arrays.asList("MS3", "MMI");
		List<Paycheck> paycheckList = salaryService.getPaychecks(companyCodes);
		for (Paycheck paycheck : paycheckList) {
			paycheckView.addPaycheck(paycheck);
		}
//		this.chartView.setPaycheckViewMap(paycheckView.getPaycheckViewMap());
	}

}
