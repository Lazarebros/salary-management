/**
 * 
 */
package com.d2l2c.salary.management.web.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.d2l2c.salary.management.data.bean.Company;
import com.d2l2c.salary.management.data.bean.Paycheck;

/**
 * @author dayanlazare
 *
 */
public class HomeView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Company> companies = new ArrayList<Company>();

	private List<Integer> years = new ArrayList<Integer>();

	private List<Paycheck> yearlyPaychecks = new ArrayList<Paycheck>();

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public void addCompany(Company company) {
		companies.add(company);
	}

	public List<Integer> getYears() {
		return years;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}

	public List<Paycheck> getYearlyPaychecks() {
		return yearlyPaychecks;
	}

	public void addYearlyPaycheck(Paycheck paycheck) {
		yearlyPaychecks.add(paycheck);
	}

}
