/**
 * 
 */
package com.d2l2c.salary.management.data.service;

import java.util.List;

import com.d2l2c.salary.management.data.bean.Company;
import com.d2l2c.salary.management.data.bean.Paycheck;

/**
 * @author dayanlazare
 *
 */
public interface SalaryService {

	public List<Company> getCompanies() throws Exception;

	public List<Paycheck> getPaychecks(String companyCode) throws Exception;

}
