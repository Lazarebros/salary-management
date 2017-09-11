/**
 * 
 */
package com.d2l2c.salary.management.data.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d2l2c.salary.management.data.bean.Company;
import com.d2l2c.salary.management.data.bean.Paycheck;
import com.d2l2c.salary.management.data.dao.CompanyDao;
import com.d2l2c.salary.management.data.dao.PaycheckDao;
import com.d2l2c.salary.management.data.service.SalaryService;

/**
 * @author dayanlazare
 *
 */
@Service("salaryService")
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	private PaycheckDao paycheckDao;

	@Autowired
	private CompanyDao companyDao;

	@Override
	public List<Company> getCompanies() throws Exception {
		return companyDao.getCompanies();
	}

	@Transactional("salaryTransactionManager")
	@Override
	public List<Paycheck> getPaychecks(String companyCode) throws Exception {
		return paycheckDao.getPaychecks(companyCode);
	}
}
