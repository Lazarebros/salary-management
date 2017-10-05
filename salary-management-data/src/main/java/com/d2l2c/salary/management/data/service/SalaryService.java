/**
 * 
 */
package com.d2l2c.salary.management.data.service;

import java.util.List;

import com.d2l2c.salary.management.data.bean.Company;
import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.bean.Rate;

/**
 * @author dayanlazare
 *
 */
public interface SalaryService {

	public List<Company> getCompanies();

	public List<Paycheck> getPaychecksByCompanyCodes(String...companyCodes);

	public List<Paycheck> getPaychecksByYears(Integer...years);
	
	public List<Paycheck> getPaychecks();
	
	public List<Rate> getRatesByCompanyCodes(String...companyCodes);
	
	public List<Rate> getRates();
	
	public Rate getRateById(Long id);
	
	public List<Integer> getPaycheckYears();

}
