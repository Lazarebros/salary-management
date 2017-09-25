package com.d2l2c.salary.management.web.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.d2l2c.salary.management.data.bean.Company;
import com.d2l2c.salary.management.data.service.SalaryService;
import com.d2l2c.salary.management.web.ui.view.HomeView;

/**
 * @author dayanlazare
 *
 */
@ManagedBean
@RequestScoped
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@ManagedProperty(value = "#{salaryService}")
	SalaryService salaryService;

	@ManagedProperty(value = "#{homeView}")
	private HomeView homeView;

	public void initView() {
		this.initCompanies();
	}

	public void setSalaryService(SalaryService salaryService) {
		this.salaryService = salaryService;
	}
	
	public HomeView getHomeView() {
		return homeView;
	}

	public void setHomeView(HomeView homeView) {
		this.homeView = homeView;
	}

	private void initCompanies() {
		try {
			List<Company> companies = salaryService.getCompanies();
			this.homeView.setCompanies(companies);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Something went wrong..."));
		}
	}

	public String filterCompany(Long id) {
		System.out.println("THIS IS IT!! " + id);
		return null;
	}

}
