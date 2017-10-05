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
import com.d2l2c.salary.management.data.bean.Rate;
import com.d2l2c.salary.management.data.dao.CompanyDao;
import com.d2l2c.salary.management.data.dao.PaycheckDao;
import com.d2l2c.salary.management.data.dao.RateDao;
import com.d2l2c.salary.management.data.service.SalaryService;

/**
 * @author dayanlazare
 *
 */
@Service("salaryService")
@Transactional("salaryTransactionManager")
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	private PaycheckDao paycheckDao;

	@Autowired
	private CompanyDao companyDao;

	@Autowired
	private RateDao rateDao;

	@Override
	public List<Company> getCompanies() {
		return companyDao.findAll();
	}

	@Override
	public List<Paycheck> getPaychecksByCompanyCodes(String...companyCodes) {
		return paycheckDao.findByCompanyCodes(companyCodes);
	}

	@Override
	public List<Paycheck> getPaychecksByYears(Integer...years) {
		return paycheckDao.findPaychecksByYears(years);
	}

	@Override
	public List<Paycheck> getPaychecks() {
		return paycheckDao.findAll();
	}

	@Override
	public List<Rate> getRatesByCompanyCodes(String...companyCodes) {
		return rateDao.findByCompanyCodes(companyCodes);
	}

	@Override
	public List<Rate> getRates() {
		return rateDao.findAll();
	}

	@Override
	public Rate getRateById(Long id) {
		return rateDao.findById(id);
	}

	@Override
	public List<Integer> getPaycheckYears() {
		return paycheckDao.getPaycheckYears();
	}

}
