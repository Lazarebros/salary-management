package com.d2l2c.salary.management.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.d2l2c.salary.management.data.bean.Company;
import com.d2l2c.salary.management.data.service.SalaryService;
import com.d2l2c.salary.management.web.ui.view.HomeView;

/**
 * @author dayanlazare
 *
 */
@Controller
public class HomeController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	SalaryService salaryService;

	private HomeView homeView;

	public void setSalaryService(SalaryService salaryService) {
		this.salaryService = salaryService;
	}

	@RequestMapping(value = { "/" ,"/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		super.addLoggedInUser(model);
		return "home";
	}

	private void initCompanies() {
		try {
			List<Company> companies = salaryService.getCompanies();
			this.homeView.setCompanies(companies);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

}
