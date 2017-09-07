package com.d2l2c.salary.management.web.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.service.PaycheckService;
import com.d2l2c.salary.management.web.ui.bean.ChartView;
import com.d2l2c.salary.management.web.ui.bean.PaycheckBean;

/**
 * @author dayanlazare
 *
 */
@ManagedBean
@RequestScoped
public class PaycheckController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaycheckController.class);

	@ManagedProperty(value = "#{paycheckService}")
	PaycheckService paycheckService;

	@ManagedProperty(value = "#{paycheckBean}")
	private PaycheckBean paycheckBean;
	
	@ManagedProperty(value = "#{chartView}")
	private ChartView chartView;

	public void setPaycheckService(PaycheckService paycheckService) {
		this.paycheckService = paycheckService;
	}

	public void setPaycheckBean(PaycheckBean paycheckBean) {
		this.paycheckBean = paycheckBean;
	}
	
	public void setChartView(ChartView chartView) {
		this.chartView = chartView;
	}

	public String showPaychecks() {
		String page = null;
		String message = null;
		try {
			this.retreivePaychecks();
			if (!paycheckBean.getPaycheckViews().isEmpty()) {
				page = "paychecks";
			} else {
				message = "No Paychecks Found!!";
			}
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
		List<Paycheck> paycheckList = paycheckService.getPaychecks();
		for (Paycheck paycheck : paycheckList) {
			paycheckBean.addPaycheck(paycheck);
		}
		this.chartView.setPaycheckViewMap(paycheckBean.getPaycheckViewMap());
	}

}
