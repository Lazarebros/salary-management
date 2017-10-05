/**
 * 
 */
package com.d2l2c.salary.management.data.dao;

import java.util.List;

import com.d2l2c.salary.management.data.bean.Rate;

/**
 * @author dayanlazare
 *
 */
public interface RateDao {
	
	public void save(Rate rate);
	
	public List<Rate> findAll();
	
	public List<Rate> findByCompanyCodes(String...companyCodes);
	
	public Rate findById(Long id);

}
