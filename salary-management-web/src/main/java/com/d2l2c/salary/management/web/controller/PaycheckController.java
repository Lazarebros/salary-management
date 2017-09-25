package com.d2l2c.salary.management.web.controller;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.service.SalaryService;
import com.d2l2c.salary.management.web.ui.bean.EChartBean;
import com.d2l2c.salary.management.web.ui.view.PaycheckView;

/**
 * @author dayanlazare
 *
 */
@ManagedBean
@RequestScoped
public class PaycheckController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaycheckController.class);

	@ManagedProperty(value = "#{salaryService}")
	SalaryService salaryService;

	@ManagedProperty(value = "#{paycheckView}")
	private PaycheckView paycheckView;
	
	@ManagedProperty(value = "#{eChartBean}")
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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
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
