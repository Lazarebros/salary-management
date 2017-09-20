/**
 * 
 */
package com.d2l2c.salary.management.data.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.d2l2c.salary.management.data.bean.Rate;

/**
 * @author dayanlazare
 *
 */
public interface RateDao extends JpaRepository<Rate, Integer> {
	
	public void saveRate(Rate rate) throws Exception;
	
	public List<Rate> getRates() throws Exception;
	
	public List<Rate> getRates(List<String> companyCodes) throws Exception;
	
	public Rate getRate(Long id) throws Exception;

}
