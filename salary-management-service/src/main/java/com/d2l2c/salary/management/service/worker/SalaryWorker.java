/**
 * 
 */
package com.d2l2c.salary.management.service.worker;

import java.util.List;

import com.d2l2c.salary.management.service.bean.CompanyVO;
import com.d2l2c.salary.management.service.bean.PaycheckVO;
import com.d2l2c.salary.management.service.bean.RateVO;

/**
 * @author dayanlazare
 *
 */
public interface SalaryWorker {

	public List<CompanyVO> getCompanies();

	public List<PaycheckVO> getPaychecksByCompanyCodes(String...companyCodes);

	public List<PaycheckVO> getPaychecksByYears(Integer...years);
	
	public List<PaycheckVO> getPaychecks();
	
	public List<RateVO> getRatesByCompanyCodes(String...companyCodes);
	
	public List<RateVO> getRates();
	
	public RateVO getRateById(Long id);
	
	public List<Integer> getPaycheckYears();

}
