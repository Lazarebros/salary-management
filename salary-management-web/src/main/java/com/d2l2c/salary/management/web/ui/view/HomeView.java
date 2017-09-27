/**
 * 
 */
package com.d2l2c.salary.management.web.ui.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.d2l2c.salary.management.data.bean.Company;

/**
 * @author dayanlazare
 *
 */
public class HomeView implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Company> companies = new ArrayList<Company>();

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public void addCompany(Company company) {
		companies.add(company);
	}

}
