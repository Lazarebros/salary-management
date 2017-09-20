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

	public List<Company> getCompanies() throws Exception;

	public List<Paycheck> getPaychecks(List<String> companyCodes) throws Exception;
	
	public List<Paycheck> getPaychecks() throws Exception;
	
	public List<Rate> getRates(List<String> companyCodes) throws Exception;
	
	public List<Rate> getRates() throws Exception;
	
	public Rate getRate(Long id) throws Exception;

}
