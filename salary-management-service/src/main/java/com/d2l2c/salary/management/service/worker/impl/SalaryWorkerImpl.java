/**
 * 
 */
package com.d2l2c.salary.management.service.worker.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.d2l2c.salary.management.data.bean.Company;
import com.d2l2c.salary.management.data.service.SalaryService;
import com.d2l2c.salary.management.service.bean.CompanyVO;
import com.d2l2c.salary.management.service.bean.PaycheckVO;
import com.d2l2c.salary.management.service.bean.RateVO;
import com.d2l2c.salary.management.service.worker.SalaryWorker;

/**
 * @author dayanlazare
 *
 */
public class SalaryWorkerImpl implements SalaryWorker {

	@Autowired
	private SalaryService salaryService;

	public List<CompanyVO> getCompanies() {
		List<Company> companies = salaryService.getCompanies();
		for(Company company: companies) {
			
		}
		
		return null;
	}

	public List<PaycheckVO> getPaychecksByCompanyCodes(String...companyCodes) {
		return null;
	}

	public List<PaycheckVO> getPaychecksByYears(Integer...years) {
		return null;
	}

	public List<PaycheckVO> getPaychecks() {
		return null;
	}

	public List<RateVO> getRatesByCompanyCodes(String...companyCodes) {
		return null;
	}

	public List<RateVO> getRates() {
		return null;
	}

	public RateVO getRateById(Long id) {
		return null;
	}

	public List<Integer> getPaycheckYears() {
		return null;
	}

}
