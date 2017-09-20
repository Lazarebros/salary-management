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
import com.d2l2c.salary.management.web.ui.bean.ChartView;
import com.d2l2c.salary.management.web.ui.bean.EChartView;
import com.d2l2c.salary.management.web.ui.bean.PaycheckBean;

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

	@ManagedProperty(value = "#{paycheckBean}")
	private PaycheckBean paycheckBean;
	
	@ManagedProperty(value = "#{chartView}")
	private ChartView chartView;
	
	@ManagedProperty(value = "#{eChartView}")
	private EChartView eChartView;

	public void setSalaryService(SalaryService salaryService) {
		this.salaryService = salaryService;
	}

	public void setPaycheckBean(PaycheckBean paycheckBean) {
		this.paycheckBean = paycheckBean;
	}
	
	public void setChartView(ChartView chartView) {
		this.chartView = chartView;
	}
	
	public EChartView geteChartView() {
		return eChartView;
	}

	public void seteChartView(EChartView eChartView) {
		this.eChartView = eChartView;
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
		paycheckBean.getPaycheckViewMap().clear();
		List<String> companyCodes = Arrays.asList("MS3", "MMI");
		List<Paycheck> paycheckList = salaryService.getPaychecks(companyCodes);
		for (Paycheck paycheck : paycheckList) {
			paycheckBean.addPaycheck(paycheck);
		}
		this.chartView.setPaycheckViewMap(paycheckBean.getPaycheckViewMap());
	}

}
