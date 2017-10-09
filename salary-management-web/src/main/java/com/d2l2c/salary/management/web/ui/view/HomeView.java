/**
 * 
 */
package com.d2l2c.salary.management.web.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import com.d2l2c.salary.management.data.bean.Company;
import com.d2l2c.salary.management.web.ui.bean.EChartBean;
import com.d2l2c.salary.management.web.ui.bean.PaycheckBean;

/**
 * @author dayanlazare
 *
 */
public class HomeView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Company> companies = new ArrayList<Company>();

	private List<Integer> years = new ArrayList<Integer>();

	TreeMap<Integer, PaycheckBean> yearlyPaycheckMap;
	
	private EChartBean chartBean = new EChartBean();

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
	
	public TreeMap<Integer, PaycheckBean> getYearlyPaycheckMap() {
		return yearlyPaycheckMap;
	}

	public void setYearlyPaycheckMap(TreeMap<Integer, PaycheckBean> yearlyPaycheckMap) {
		this.yearlyPaycheckMap = yearlyPaycheckMap;
	}

	public Collection<PaycheckBean> getYearlyPaychecks() {
		return yearlyPaycheckMap.values();
	}

	public EChartBean getChartBean() {
		chartBean.setYearlyPaycheckMap(yearlyPaycheckMap);
		return chartBean;
	}

}
