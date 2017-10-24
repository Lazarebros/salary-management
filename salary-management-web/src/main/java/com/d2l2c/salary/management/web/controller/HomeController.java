package com.d2l2c.salary.management.web.controller;

import java.util.List;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.d2l2c.salary.management.data.bean.Company;
import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.service.SalaryService;
import com.d2l2c.salary.management.web.ui.bean.PaycheckBean;
import com.d2l2c.salary.management.web.ui.view.HomeView;
import com.d2l2c.salary.management.web.util.SalaryWebUtil;

/**
 * @author dayanlazare
 *
 */
@Controller
public class HomeController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	private static final String HOME_VIEW_ATTR_NAME = "homeView";

	@Autowired
	SalaryService salaryService;

	private HomeView homeView = new HomeView();

	public void setSalaryService(SalaryService salaryService) {
		this.salaryService = salaryService;
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		super.addLoggedInUser(model);
		this.getCompanyList();
		this.getYearList();

		model.addAttribute(HOME_VIEW_ATTR_NAME, homeView);
		return "home";
	}

	private void getCompanyList() {
		try {
			List<Company> companies = salaryService.getCompanies();
			homeView.setCompanies(companies);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private void getYearList() {
		try {
			List<Integer> years = salaryService.getPaycheckYears();
			homeView.setYears(years);

			this.getYearlyPaychecks(years);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private void getYearlyPaychecks(List<Integer> years) {
		List<Paycheck> paychecks = salaryService.getPaychecksByYears(years.toArray(new Integer[0]));
		TreeMap<Integer, PaycheckBean> yearlyPaycheckMap = SalaryWebUtil.groupPaychecksByYear(paychecks);
		homeView.setYearlyPaycheckMap(yearlyPaycheckMap);
	}

}
