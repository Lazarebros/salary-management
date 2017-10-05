/**
 * 
 */
package com.d2l2c.salary.management.data.dao;

import java.util.List;

import com.d2l2c.salary.management.data.bean.Paycheck;

/**
 * @author dayanlazare
 *
 */
public interface PaycheckDao {
	
	public void save(Paycheck paycheck);
	
	public List<Paycheck> findAll();
	
	public List<Paycheck> findByCompanyCodes(String...companyCodes);

	public List<Paycheck> findPaychecksByYears(Integer...years);
	
	public Paycheck findById(Long id);

	public List<Integer> getPaycheckYears();

}
