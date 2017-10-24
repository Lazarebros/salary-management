package com.d2l2c.salary.management.web.controller;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.d2l2c.common.util.chart.ChartConstants;
import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.service.SalaryService;
import com.d2l2c.salary.management.web.ui.bean.PaycheckBean;
import com.d2l2c.salary.management.web.ui.view.PaycheckView;
import com.d2l2c.salary.management.web.util.SalaryWebUtil;

/**
 * @author dayanlazare
 *
 */
@Controller
public class PaycheckController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PaycheckController.class);

	private static final String PAYCHECKS_VIEW_ATTR_NAME = "paychecksView";

	@Autowired
	SalaryService salaryService;

	int year;

	private PaycheckView paycheckView = new PaycheckView(ChartConstants.Serie.BAR_TYPE);

	public void setSalaryService(SalaryService salaryService) {
		this.salaryService = salaryService;
	}

	@RequestMapping(value = {"/paychecks-{year}"}, method = RequestMethod.GET)
	public String paychecksPage(@PathVariable int year, ModelMap model) {
		super.addLoggedInUser(model);
		try {
			paycheckView.setYear(year);
			this.getYearlyPaychecks(year);
			model.addAttribute(PAYCHECKS_VIEW_ATTR_NAME, paycheckView);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return "paychecks";
	}

	private void getYearlyPaychecks(int year) {
		List<Integer> years = Arrays.asList(year);
		List<Paycheck> paychecks = salaryService.getPaychecksByYears(years.toArray(new Integer[0]));
		TreeMap<Integer, PaycheckBean> monthlypaycheckMap = SalaryWebUtil.groupPaychecksByMonth(paychecks);
		paycheckView.setMonthlyPaycheckMap(monthlypaycheckMap);

		TreeMap<Integer, PaycheckBean> yearlyPaycheckMap = SalaryWebUtil.groupPaychecksByYear(paychecks);
		paycheckView.setYearlyPaycheckMap(yearlyPaycheckMap);
	}

}
